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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private Collection<Procedure> procedures = new LinkedHashSet<>();

    public Category(String name) {
        this.name = name;
    }
}
