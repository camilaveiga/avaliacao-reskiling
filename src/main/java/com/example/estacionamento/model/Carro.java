package com.example.estacionamento.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "carro", schema = "aulas1")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarro")
    private Integer id;

    @Column(name = "nomecarro", nullable = false, length = 45)
    private String nomeCarro;

    @Column(name = "anofabricacaocarro", nullable = false)
    private int anoFabricacao;

    @Column(name = "anomodelocarro", nullable = false)
    private int anoModelo;

    @Column(name = "modelocarro", length = 25)
    private String modeloCarro;

    @ManyToOne
    @JoinColumn(name = "marca_idmarca", nullable = false, referencedColumnName = "idmarca", foreignKey = @ForeignKey(name = "fk_carro_marca"))
    private Marca marca;

    @ManyToMany
    @JoinTable(
            name = "carro_cor",
            schema = "aulas1",
            joinColumns = @JoinColumn(name = "carro_idcarro"),
            inverseJoinColumns = @JoinColumn(name = "cor_idcor")
    )

    private List<Cor> cores;

    public List<Cor> getCores(){
        return cores;
    }

    //getters and setters

    public void setId(Integer id){
        this.id = id;
    }

    public void setNomeCarro(String nomeCarro){
        this.nomeCarro = nomeCarro;
    }

    public void setAnoFabricacao(int anoFabricacao){
        this.anoFabricacao = anoFabricacao;
    }

    public void setAnoModelo(int anoModelo){
        this.anoModelo = anoModelo;
    }

    public void setModeloCarro(String modeloCarro){
        this.modeloCarro = modeloCarro;
    }

    public Marca getMarca(){
        return marca;
    }

    public void setMarca(Marca marca){
        this.marca = marca;
    }


    public void setCores(List<Cor> cores){
        this.cores = cores;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public Integer getId() {
        return id;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }
}
