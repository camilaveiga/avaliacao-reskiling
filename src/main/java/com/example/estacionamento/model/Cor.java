package com.example.estacionamento.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;


@Entity
@Table(schema = "aulas1", name = "cor")
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcor")
    private Integer id;

    @Column(name = "nomecor", nullable = false)
    private String nomeCor;

    @ManyToMany(mappedBy = "cores")
    @JsonBackReference
    private List<Carro> carros;

    //Getters and Setters

    public Integer id(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNomeCor(){
        return nomeCor;
    }

    public void setNomeCor(String nomeCor){
        this.nomeCor = nomeCor;

    }

    public List<Carro> getCarros(){
        return carros;

    }
    public void setCarros(List<Carro> carros){
        this.carros = carros;
    }
}
