package ru.job4j.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime actualEnd;
    private long sum;
    private byte percent;

    public Credit(LocalDateTime start, LocalDateTime end, long sum, byte percent) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.percent = percent;
    }
}