package com.example.estacionamento.controller;

import com.example.estacionamento.model.Carro;
import com.example.estacionamento.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/listarTodos")
    @Operation(summary = "Listar todos os Carros", description = "Retorna todos os carros cadastrados.")
    public List<Carro> getAllCarros(){
        return carroService.getAllCarros();
    }

    @PostMapping("/criar")
    @Operation(summary = "Cadastro de carro", description = "Criar o cadastro de carro.")
    public Carro createCarro(@RequestBody Carro carro){
        return carroService.createCarro(carro);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar carro", description = "Atualizar informacoes de carro po id.")
    public Carro updateCarro(@PathVariable Integer id, @RequestBody Carro carro){
        return carroService.updateCarro(id, carro);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar carro", description = "Deletar cadastri de carro por id;")
    public void deleteCarro(@PathVariable Integer id){
        carroService.deleteCarro(id);
    }

}
