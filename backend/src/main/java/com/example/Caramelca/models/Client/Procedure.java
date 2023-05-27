package com.example.Caramelca.models.Client;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private int cost;

    private int duration;

    @OneToMany(mappedBy = "procedure", cascade = CascadeType.REMOVE)
    private Collection<Qualification> qualification = new LinkedHashSet<>();

    public Procedure(String title, String description, int cost, int duration) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.duration = duration;
    }

    public Procedure() {}

    public Collection<Qualification> getQualification() {
        return qualification;
    }

    public void setQualification(Collection<Qualification> appointments) {
        this.qualification = appointments;
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
