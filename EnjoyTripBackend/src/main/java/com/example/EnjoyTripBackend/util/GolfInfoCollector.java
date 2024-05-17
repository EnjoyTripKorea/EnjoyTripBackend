package com.example.EnjoyTripBackend.util;

import com.example.EnjoyTripBackend.dto.golf.GolfClubDto;
import com.example.EnjoyTripBackend.dto.golf.GolfClubListDto;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.service.GolfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GolfInfoCollector {

    @Value("${golf.url}")
    private String url;

    private final GolfService golfService;

    @Scheduled(cron = "0 0 9 1 * ?")
    public void golfInfoCollector(){

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 31; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(formatter);
            final String targetUrl = url+formattedDate;
            RestTemplate rt = new RestTemplate();

            ResponseEntity<String> response = rt.getForEntity(targetUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                GolfClubDto responseObject = objectMapper.readValue(response.getBody(), GolfClubDto.class);

                List<GolfClubListDto> golfclubList = responseObject.getData().getGolfclubList();
                for (GolfClubListDto golfClubListDto : golfclubList){
                    golfService.save(formattedDate,golfClubListDto);
                }
            } catch (Exception e) {
                throw new EnjoyTripException(ErrorCode.FAIL_INSERT_GOLF_DATA, e.getMessage());
            }
        }
    }
}