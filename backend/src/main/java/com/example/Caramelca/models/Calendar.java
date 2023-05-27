package com.example.Caramelca.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Calendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Column(columnDefinition = "TIME WITHOUT TIME ZONE")
    private LocalTime time;

    public Calendar(Employee employee, LocalDate date, LocalTime time) {
        this.employee = employee;
        this.date = date;
        this.time = time;
    }
}
