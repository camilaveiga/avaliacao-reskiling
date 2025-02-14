package com.example.estacionamento.service;

import com.example.estacionamento.model.Carro;
import com.example.estacionamento.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroResository;

    public List<Carro> getAllCarros(){
        return carroResository.findAll();
    }

    public Carro createCarro(Carro carro){
        return carroResository.save(carro);
    }

    public Carro updateCarro(Integer id, Carro carro){
        Optional<Carro> existirCarro = carroResository.findById(id);
        if(existirCarro.isEmpty()){
            throw new RuntimeException("Carro não encontrado");
        }
        carro.setId(id);
        return carroResository.save(carro);

    }

    public void deleteCarro(Integer id){
        Optional<Carro> existirCarro = carroResository.findById(id);
        if(existirCarro.isEmpty()){
            throw new RuntimeException("Carro não encontrado");
        }
        carroResository.deleteById(id);
    }
}
