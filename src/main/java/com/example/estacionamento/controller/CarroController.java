package com.example.estacionamento.controller;

import com.example.estacionamento.model.Carro;
import com.example.estacionamento.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/listarTodos")
    public List<Carro> getAllCarros(){
        return carroService.getAllCarros();
    }

    @PostMapping("/criar")
    public Carro createCarro(@RequestBody Carro carro){
        return carroService.createCarro(carro);
    }

    @PutMapping("/atualizar/{id}")
    public Carro updateCarro(@PathVariable Integer id, @RequestBody Carro carro){
        return carroService.updateCarro(id, carro);
    }

    @DeleteMapping("/deletar/{id}")
    public void deleteCarro(@PathVariable Integer id){
        carroService.deleteCarro(id);
    }

}
