package ru.job4j.credit.service;

import ru.job4j.credit.model.CreditHistory;
import ru.job4j.credit.model.Passport;

public interface CreditHistoryService {
    CreditHistory findCreditHistoryByPassport(Passport passport);
}