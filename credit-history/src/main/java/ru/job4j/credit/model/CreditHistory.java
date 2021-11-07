package ru.job4j.credit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Credit> credits;

    @ManyToOne
    @JsonIgnore
    private Passport passport;

    public CreditHistory(List<Credit> credits, Passport passport) {
        this.credits = credits;
        this.passport = passport;
    }

    /* Constructors, getters and setters */
}