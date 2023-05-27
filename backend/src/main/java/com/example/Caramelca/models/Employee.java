package com.example.Caramelca.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Profession> profession = new LinkedHashSet<Profession>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Calendar> calendar = new LinkedHashSet<Calendar>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Appointment> appointment = new LinkedHashSet<Appointment>();

    private String name;

    private String surname;

    private String patronymic;

    private String number;

    private boolean deleted;

    public Employee(String name, String surname, String patronymic, String number) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.number = number;
    }

}
