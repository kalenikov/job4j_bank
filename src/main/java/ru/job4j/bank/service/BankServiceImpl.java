package ru.job4j.bank.service;

import org.springframework.stereotype.Service;
import ru.job4j.bank.model.Credit;
import ru.job4j.bank.model.CreditHistory;
import ru.job4j.bank.model.Passport;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    private PassportService passportService;
    private CreditHistoryService creditHistoryService;

    public BankServiceImpl(PassportService passportService,
                           CreditHistoryService creditHistoryService) {
        this.passportService = passportService;
        this.creditHistoryService = creditHistoryService;
    }

    @Override
    public Optional<Credit> getCredit(Passport passport, Credit creditData) {
        Optional<Passport> passportOptional = passportService.findBySeriesAndNumber(
                passport.getSeries(), passport.getNumber()
        );
        if (passportOptional.isEmpty()) {
            return Optional.empty();
        }
        Optional<CreditHistory> creditHistoryOptional = creditHistoryService.getHistory(passportOptional.get());
        return checkCreditHistory(creditHistoryOptional) ? Optional.of(creditData) : Optional.empty();
    }

    private boolean checkCreditHistory(Optional<CreditHistory> creditHistoryOptional) {
        if (creditHistoryOptional.isEmpty()) {
            return true;
        }
        CreditHistory creditHistory = creditHistoryOptional.get();
        boolean result = true;
        for (Credit credit : creditHistory.getCredits()) {
            LocalDateTime endDate = credit.getEnd();
            LocalDateTime actualEndDate = credit.getActualEnd();
            if (actualEndDate == null || actualEndDate.isAfter(endDate)) {
                result = false;
                break;
            }
        }
        return result;
    }
}