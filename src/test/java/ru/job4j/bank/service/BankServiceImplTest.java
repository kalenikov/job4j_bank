package ru.job4j.bank.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.bank.model.Credit;
import ru.job4j.bank.model.Passport;

import java.time.LocalDateTime;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BankServiceImplTest {

    @Autowired
    BankService bankService;

    @Test
    public void whenSuccessCredit() {
        Passport passport = new Passport(1111, 12345);
        Credit credit = new Credit(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(12),
                200_000,
                (byte) 10
        );
        assertSame(credit, bankService.getCredit(passport, credit).get());
    }

    @Test
    public void whenHasCredit() {
        Passport passport = new Passport(2222, 98765);
        Credit credit = new Credit(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(12),
                200_000, (byte) 10
        );
        assertTrue(bankService.getCredit(passport, credit).isEmpty());
    }

    @Test
    public void whenHasOverdueCredit() {
        Passport passport = new Passport(5555, 12859);
        Credit credit = new Credit(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(12),
                200_000,
                (byte) 10
        );
        assertTrue(bankService.getCredit(passport, credit).isEmpty());
    }

}