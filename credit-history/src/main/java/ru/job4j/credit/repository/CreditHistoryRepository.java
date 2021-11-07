package ru.job4j.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.credit.model.CreditHistory;
import ru.job4j.credit.model.Passport;

import java.util.Optional;

@Repository
public interface CreditHistoryRepository extends JpaRepository<CreditHistory, Integer> {
    Optional<CreditHistory> findByPassport(Passport passport);
}