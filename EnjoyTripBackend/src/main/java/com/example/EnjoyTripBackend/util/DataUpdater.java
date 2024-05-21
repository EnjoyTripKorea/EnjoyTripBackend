package com.example.EnjoyTripBackend.util;

import com.example.EnjoyTripBackend.util.collector.AirplaneInfoCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataUpdater implements CommandLineRunner {

    private final AirplaneInfoCollector airplaneInfoCollector;

    @Autowired
    public DataUpdater(AirplaneInfoCollector airplaneInfoCollector) {
        this.airplaneInfoCollector = airplaneInfoCollector;
    }

    @Override
    public void run(String... args) throws Exception {
        // 애플리케이션이 시작될 때 실행되는 코드
//        airplaneInfoCollector.fetchFlightInfo();
    }
}
