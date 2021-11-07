package ru.job4j.bank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.bank.model.CreditHistory;
import ru.job4j.bank.model.Passport;


import java.util.Optional;

@Service
public class CreditHistoryServiceImpl implements CreditHistoryService {

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    public CreditHistoryServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<CreditHistory> getHistory(Passport passport) {
        String url = "http://localhost:8081/get-history";
        ResponseEntity<CreditHistory> response = restTemplate.getForEntity(
                String.format("%s?seria=%d&number=%d", url, passport.getSeries(), passport.getNumber()),
                CreditHistory.class
        );
        Optional<CreditHistory> result = Optional.empty();
        if (response.getStatusCode() == HttpStatus.OK) {
            result = Optional.of(response.getBody());
        }
        return result;
    }
}