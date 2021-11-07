package ru.job4j.bank.service;

import ru.job4j.bank.model.CreditHistory;
import ru.job4j.bank.model.Passport;

import java.util.Optional;

public interface CreditHistoryService {
    Optional<CreditHistory> getHistory(Passport passport);
}
