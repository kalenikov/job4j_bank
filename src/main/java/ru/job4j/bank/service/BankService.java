package ru.job4j.bank.service;

import ru.job4j.bank.model.Credit;
import ru.job4j.bank.model.Passport;

import java.util.Optional;

public interface BankService {
    Optional<Credit> getCredit(Passport passport, Credit creditData);
}
