package com.example.Caramelca.models.Client;

import com.example.Caramelca.models.Auth.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Qualification qualification;
    @ManyToOne
    private User user;
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @Column(columnDefinition = "TIME WITHOUT TIME ZONE")
    private LocalTime time;

    public Appointment(Qualification qualification, User user, LocalDate date, LocalTime time) {
        this.qualification = qualification;
        this.user = user;
        this.date = date;
        this.time = time;
    }
}
