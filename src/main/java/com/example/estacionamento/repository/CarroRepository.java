package com.example.estacionamento.repository;

import com.example.estacionamento.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Integer> {
    Optional<Carro> findById(Integer id);
}
