package com.example.Caramelca.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String logo;

    private int cost;

    private int duration;

    @ManyToMany
    private Collection<Employee> employees;

    public Procedure(String title, String description, String logo, int cost, int duration) {
        this.title = title;
        this.description = description;
        this.logo = logo;
        this.cost = cost;
        this.duration = duration;
    }

    public Procedure() {

    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
