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
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Procedure procedure;
    @OneToMany(mappedBy = "qualification", cascade = CascadeType.REMOVE)
    private Collection<Appointment> appointment = new LinkedHashSet<>();

    public Qualification(Employee employee, Procedure procedure) {
        this.employee = employee;
        this.procedure = procedure;
    }
}
