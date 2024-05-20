package com.example.EnjoyTripBackend.util;

import com.example.EnjoyTripBackend.domain.Camping;
import com.example.EnjoyTripBackend.dto.camping.CampingResponse;
import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.service.CampingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class CampingInfoCollector {

    @Value("${camping.url}")
    private String targetUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CampingService campingService;

    @Scheduled(cron = "0 0 9 1 * ?")
    public void CampingInfoCollector1() {
        try {
            CampingResponse campingResponse = objectMapper.readValue(XML.toJSONObject(getCampingData()).toString(), CampingResponse.class);
            insertCampingData(campingResponse);
        } catch (Exception e) {
            throw new EnjoyTripException(ErrorCode.FAIL_INSERT_CAMPING_DATA, e.getMessage());
        }
    }

    private void insertCampingData(CampingResponse campingResponse) {
        int totalDataSize = campingResponse.getResponse().getBody().getItems().getItem().size();
        for (int i=0; i< totalDataSize; i++){
            Camping camping = Camping.builder()
                    .name(campingResponse.getResponse().getBody().getItems().getItem().get(i).getFacltNm())
                    .shortIntro(campingResponse.getResponse().getBody().getItems().getItem().get(i).getLineIntro())
                    .Intro(campingResponse.getResponse().getBody().getItems().getItem().get(i).getIntro())
                    .zipcode(campingResponse.getResponse().getBody().getItems().getItem().get(i).getZipcode())
                    .address(campingResponse.getResponse().getBody().getItems().getItem().get(i).getAddr1())
                    .latitude(campingResponse.getResponse().getBody().getItems().getItem().get(i).getMapX())
                    .longitude(campingResponse.getResponse().getBody().getItems().getItem().get(i).getMapY())
                    .imageUrl(campingResponse.getResponse().getBody().getItems().getItem().get(i).getFirstImageUrl())
                    .homepage(campingResponse.getResponse().getBody().getItems().getItem().get(i).getHomepage())
                    .build();
            campingService.save(camping);
        }
    }

    public String getCampingData() {
        String url = UriComponentsBuilder.fromHttpUrl(targetUrl).toUriString();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return responseEntity.getBody();
    }
}