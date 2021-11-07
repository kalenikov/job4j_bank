package ru.job4j.bank.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.bank.model.Passport;

import java.util.Optional;

@Service
public class PassportServiceImpl implements PassportService {

    private final RestTemplate restTemplate;

    public PassportServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Passport> findBySeriesAndNumber(int series, int number) {
        String url = "http://localhost:8080/checkValid";
        String result = restTemplate.postForObject(
                url, new Passport(series, number), String.class
        );
        return "true".equals(result)
                ? Optional.of(new Passport(series, number))
                : Optional.empty();
    }

}