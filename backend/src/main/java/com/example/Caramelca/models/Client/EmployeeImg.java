package com.example.Caramelca.models.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class EmployeeImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Employee employee;
    private String src;

    public EmployeeImg(Employee employee, String src) {
        this.employee = employee;
        this.src = src;
    }
}
