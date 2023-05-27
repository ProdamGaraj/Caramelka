package com.example.Caramelca.models.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Employee employee;
    @Enumerated(EnumType.STRING)
    private DayType dayType;
    private LocalDate date;
    private LocalTime begin_time;
    private LocalTime end_time;

    public Schedule(Employee employee,
                    DayType dayType,
                    LocalDate date,
                    LocalTime begin_time,
                    LocalTime end_time) {
        this.employee = employee;
        this.dayType = dayType;
        this.date = date;
        this.begin_time = begin_time;
        this.end_time = end_time;
    }
}
