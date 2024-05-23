package com.example.EnjoyTripBackend.util.collector;

import com.example.EnjoyTripBackend.dto.airplane.AirplaneDto;
import com.example.EnjoyTripBackend.dto.airplane.AirplaneItem;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.service.AirplaneService;
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
public class AirplaneInfoCollector {

    @Value("${airplane.url}")
    private String serviceKey;

    private final AirplaneService airplaneService;

    //@Scheduled(cron = "* * * * * *")
    public void fetchFlightInfo() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        List<String> airports = List.of("NAARKJB", "NAARKJJ", "NAARKJK", "NAARKJY", "NAARKNW", "NAARKNY", "NAARKPC", "NAARKPK", "NAARKPS", "NAARKPU", "NAARKSI", "NAARKSS", "NAARKTH", "NAARKTN", "NAARKTU");
        List<String> airlineIds = List.of("AAR", "ABL", "ASV", "ESR", "FGW", "HGG", "JJA", "JNA", "KAL", "TWB");

        String url = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?&_type=json&serviceKey=" + serviceKey;

        for (int i = 0; i < 31; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(formatter);

            for (String depAirportId : airports) {
                for (String arrAirportId : airports) {
                    if (!depAirportId.equals(arrAirportId)) {
                        final String targetUrl = url + "&depAirportId=" + depAirportId + "&arrAirportId=" + arrAirportId + "&depPlandTime=" + formattedDate;
                        RestTemplate rt = new RestTemplate();
                        ResponseEntity<String> response = rt.getForEntity(targetUrl, String.class);
                        ObjectMapper objectMapper = new ObjectMapper();

                        try {
                            AirplaneDto responseObject = objectMapper.readValue(response.getBody(), AirplaneDto.class);

                            int size = responseObject.getResponse().getBody().getItems().getItem().size();

                            for (int n = 0; n < size; n++) {
                                AirplaneItem item = responseObject.getResponse().getBody().getItems().getItem().get(n);
                                airplaneService.save(item);
                                System.out.println(item);
                            }
                        } catch (Exception e) {
                            throw new EnjoyTripException(ErrorCode.FAIL_INSERT_AIRPLANE_DATA, e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
