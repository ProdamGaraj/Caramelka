package com.example.Caramelca.models.Client;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;

    @OneToMany(mappedBy = "qualification", cascade = CascadeType.REMOVE)
    private Collection<Appointment> appointment = new LinkedHashSet<>();

    public Qualification(Employee employee, Procedure procedure) {
        this.employee = employee;
        this.procedure = procedure;
    }
    public Qualification() {}

    public Collection<Appointment> getAppointment() {
        return appointment;
    }
    public void setAppointment(Collection<Appointment> appointments) {
        this.appointment = appointments;
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

    public Procedure getProcedure() {
        return procedure;
    }
    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
}
