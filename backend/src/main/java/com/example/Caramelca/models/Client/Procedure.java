package com.example.Caramelca.models.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Category category;
    private String title;
    private String description;
    private int price;
    private int duration;
    @OneToMany(mappedBy = "procedure", cascade = CascadeType.REMOVE)
    private Collection<Qualification> qualification = new LinkedHashSet<>();
    @OneToMany(mappedBy = "procedure", cascade = CascadeType.REMOVE)
    private Collection<ProcedureImg> procedureImgs = new LinkedHashSet<>();

    public Procedure(String title, String description, int price, int duration) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }
}
