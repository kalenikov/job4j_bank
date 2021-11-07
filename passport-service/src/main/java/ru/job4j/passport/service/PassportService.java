package ru.job4j.passport.service;

public interface PassportService {
    boolean checkValid(int series, int number);
}