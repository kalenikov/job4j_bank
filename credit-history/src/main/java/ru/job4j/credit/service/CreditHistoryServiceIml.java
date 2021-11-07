package ru.job4j.credit.service;

import org.springframework.stereotype.Service;
import ru.job4j.credit.model.Credit;
import ru.job4j.credit.model.CreditHistory;
import ru.job4j.credit.model.Passport;
import ru.job4j.credit.repository.CreditHistoryRepository;
import ru.job4j.credit.repository.CreditRepository;
import ru.job4j.credit.repository.PassportRepository;


import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreditHistoryServiceIml implements CreditHistoryService {

    private final PassportRepository passportRepository;
    private final CreditRepository creditRepository;
    private final CreditHistoryRepository creditHistoryRepository;

    public CreditHistoryServiceIml(PassportRepository passportRepository,
                                   CreditRepository creditRepository,
                                   CreditHistoryRepository creditHistoryRepository) {
        this.passportRepository = passportRepository;
        this.creditRepository = creditRepository;
        this.creditHistoryRepository = creditHistoryRepository;
    }

    @PostConstruct
    private void initData() {
        Passport passport1 = passportRepository.save(new Passport(1111, 12345));
        Passport passport2 = passportRepository.save(new Passport(2222, 98765));
        Passport passport3 = passportRepository.save(new Passport(5555, 12859));

        /* Завершенный кредит */
        Credit credit1 = creditRepository.save(new Credit(
                        LocalDateTime.now().minusMonths(10),
                        LocalDateTime.now().minusMonths(1),
                        LocalDateTime.now().minusMonths(2),
                        100_000, (byte) 10, passport1
                )
        );

        /* Имеющейся кредит */
        Credit credit2 = creditRepository.save(new Credit(
                LocalDateTime.now().minusMonths(10),
                LocalDateTime.now().plusMonths(2),
                null,
                100_000, (byte) 10, passport2
        ));

        /* Просроченный кредит */
        Credit credit3 = creditRepository.save(new Credit(
                LocalDateTime.now().minusMonths(10),
                LocalDateTime.now().minusMonths(5),
                LocalDateTime.now().minusMonths(2),
                100_000, (byte) 10, passport3
        ));

        creditHistoryRepository.save(new CreditHistory(List.of(credit1), passport1));
        creditHistoryRepository.save(new CreditHistory(List.of(credit2), passport2));
        creditHistoryRepository.save(new CreditHistory(List.of(credit3), passport3));
    }

    @Override
    public CreditHistory findCreditHistoryByPassport(Passport passport) {
        return creditHistoryRepository.findByPassport(passport).orElse(new CreditHistory());
    }

}