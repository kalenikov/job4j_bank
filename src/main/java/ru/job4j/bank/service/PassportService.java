package ru.job4j.bank.service;

import ru.job4j.bank.model.Passport;

import java.util.Optional;

public interface PassportService {
    Optional<Passport> findBySeriesAndNumber(int series, int number);
}