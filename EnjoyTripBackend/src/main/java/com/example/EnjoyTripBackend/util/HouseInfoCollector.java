package com.example.EnjoyTripBackend.util;

import com.example.EnjoyTripBackend.dto.house.HouseDto;
import com.example.EnjoyTripBackend.dto.house.HouseItem;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.service.HouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class HouseInfoCollector {

    @Value("${house.url}")
    private String url;

    private final HouseService houseService;

    @Scheduled(cron = "0 0 0 1 * ?") // 매월 1일 자정에 실행
    public void houseInfoCollector() throws URISyntaxException {

        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HouseDto responseObject = objectMapper.readValue(response.getBody(), HouseDto.class);

            int size = responseObject.getResponse().getBody().getItems().getItem().size();

            for(int i = 0; i < size ; i++) {
                HouseItem item = responseObject.getResponse().getBody().getItems().getItem().get(i);
                houseService.save(item);
            }
        } catch (Exception e) {
            throw new EnjoyTripException(ErrorCode.FAIL_INSERT_GOLF_DATA, e.getMessage());
        }
    }
}