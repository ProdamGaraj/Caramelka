package com.example.Caramelca.models;

import jakarta.persistence.*;

@Entity
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Procedure service;

    private int experience;

    public Profession(Employee employee, Procedure service) {
        this.employee = employee;
        this.service = service;
    }


    public Profession() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Procedure getService() {
        return service;
    }

    public void setService(Procedure service) {
        this.service = service;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
