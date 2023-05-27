package com.example.Caramelca.models.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class ProcedureImg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Procedure procedure;
    private String src;

    public ProcedureImg(Procedure procedure, String src) {
        this.procedure = procedure;
        this.src = src;
    }
}
