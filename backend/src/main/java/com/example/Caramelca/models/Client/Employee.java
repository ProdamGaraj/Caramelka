package com.example.Caramelca.models.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Collection<Qualification> qualification = new LinkedHashSet<>();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Collection<EmployeeImg> employeeImgs = new LinkedHashSet<>();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Collection<Schedule> schedules = new LinkedHashSet<>();
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
