package com.example.estacionamento.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(schema = "aulas1", name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarca")
    private Integer id;

    @Column(name = "nomemarca", nullable = false, length = 45)
    private String nomeMarca;

    @OneToMany
    private List<Carro> carros;


    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

}
