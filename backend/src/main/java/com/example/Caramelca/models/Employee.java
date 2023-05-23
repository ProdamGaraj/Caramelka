package com.example.Caramelca.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Employee_Service> employee_Service = new LinkedHashSet<Employee_Service>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Calendar> calendar = new LinkedHashSet<Calendar>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Appointment> appointment = new LinkedHashSet<Appointment>();

    private String name;

    private String surname;

    private String patronymic;

    private String number;

    private boolean deleted;

    public Employee() {

    }

    public Employee(String name, String surname, String patronymic, String number) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
