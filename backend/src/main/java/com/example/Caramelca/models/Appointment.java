package com.example.Caramelca.models;

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
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Column(columnDefinition = "TIME WITHOUT TIME ZONE")
    private LocalTime time;

    public Appointment(Employee employee, Service service, User user, LocalDate date, LocalTime time) {
        this.employee = employee;
        this.service = service;
        this.user = user;
        this.date = date;
        this.time = time;
    }

}
