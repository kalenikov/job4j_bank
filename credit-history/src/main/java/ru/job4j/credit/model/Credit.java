package ru.job4j.credit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime actualEnd;
    private long sum;
    private byte percent;

    @ManyToOne
    @JsonIgnore
    private Passport passport;

    public Credit(LocalDateTime start, LocalDateTime end, LocalDateTime actualEnd, long sum, byte percent, Passport passport) {
        this.start = start;
        this.end = end;
        this.actualEnd = actualEnd;
        this.sum = sum;
        this.percent = percent;
        this.passport = passport;
    }
}