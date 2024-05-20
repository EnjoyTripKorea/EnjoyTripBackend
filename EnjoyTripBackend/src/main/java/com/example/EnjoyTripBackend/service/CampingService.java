package com.example.EnjoyTripBackend.service;

import com.example.EnjoyTripBackend.domain.Camping;
import com.example.EnjoyTripBackend.repository.CampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampingService {

    private final CampingRepository campingRepository;

    @Transactional
    public Long save(Camping camping) {
        return campingRepository.save(camping);
    }
}