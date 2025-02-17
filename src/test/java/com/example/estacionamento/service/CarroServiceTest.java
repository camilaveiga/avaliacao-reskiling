package com.example.estacionamento.service;

import com.example.estacionamento.model.Carro;
import com.example.estacionamento.model.Cor;
import com.example.estacionamento.model.Marca;
import com.example.estacionamento.repository.CarroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CarroServiceTest {

    @Mock
    CarroRepository carroRepository;

    @InjectMocks
    CarroService carroService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Criar carro com sucesso")
    void createCarro() {
        Carro novoCarro = new Carro();

        Marca marca = new Marca();
        marca.setNomeMarca("Chevrolet");

        Cor cor = new Cor();
        cor.setNomeCor("Preto");

        novoCarro.setNomeCarro("celta");
        novoCarro.setModeloCarro("Flex");
        novoCarro.setAnoModelo(2009);
        novoCarro.setAnoFabricacao(2008);
        novoCarro.setMarca(marca);
        novoCarro.setCores(List.of(cor));

        Carro carroSalvo = new Carro();
        carroSalvo.setId(1); // ID gerado ao salvar
        carroSalvo.setNomeCarro(novoCarro.getNomeCarro());
        carroSalvo.setModeloCarro(novoCarro.getModeloCarro());
        carroSalvo.setAnoModelo(novoCarro.getAnoModelo());
        carroSalvo.setAnoFabricacao(novoCarro.getAnoFabricacao());
        carroSalvo.setMarca(novoCarro.getMarca());
        carroSalvo.setCores(novoCarro.getCores());

        when(carroRepository.save(any(Carro.class))).thenReturn(carroSalvo);
        Carro result = carroService.createCarro(novoCarro);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getNomeCarro()).isEqualTo("celta");
    }


    @Test
    @DisplayName("Listar carro quando com sucesso")
    void getAllCarros() {
        Carro novoCarro = new Carro();

        Marca marca = new Marca();
        marca.setNomeMarca("Chevrolet");
        marca.setId(1);

        Cor cor = new Cor();
        cor.setId(1);
        cor.setNomeCor("Preto");

        novoCarro.setId(1);
        novoCarro.setNomeCarro("celta");
        novoCarro.setModeloCarro("Flex");
        novoCarro.setAnoModelo(2009);
        novoCarro.setAnoFabricacao(2008);
        novoCarro.setMarca(marca);
        novoCarro.setCores(List.of(cor));

        when(carroRepository.findAll()).thenReturn(List.of(novoCarro));
        List<Carro> result = carroService.getAllCarros();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Atualizar carro com sucesso")
    void updateCarro() {
        Marca marca = new Marca();
        marca.setNomeMarca("Chevrolet");

        Cor cor = new Cor();
        cor.setNomeCor("Preto");

        Carro carroExistente = new Carro();
        carroExistente.setId(1);
        carroExistente.setNomeCarro("Celta");
        carroExistente.setModeloCarro("Flex");
        carroExistente.setAnoModelo(2009);
        carroExistente.setAnoFabricacao(2008);
        carroExistente.setMarca(marca);
        carroExistente.setCores(List.of(cor));

        Carro carroAtualizado = new Carro();
        carroAtualizado.setId(1);
        carroAtualizado.setNomeCarro("Celta 2.0");
        carroAtualizado.setModeloCarro("Flex");
        carroAtualizado.setAnoModelo(2009);
        carroAtualizado.setAnoFabricacao(2009);
        carroAtualizado.setMarca(marca);
        carroAtualizado.setCores(List.of(cor));

        when(carroRepository.findById(1)).thenReturn(Optional.of(carroExistente));
        when(carroRepository.save(any(Carro.class))).thenReturn(carroAtualizado);

        Carro result = carroService.updateCarro(1, carroAtualizado);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getNomeCarro()).isEqualTo("Celta 2.0");
        assertThat(result.getAnoFabricacao()).isEqualTo(2009);
    }

    @Test
    @DisplayName("Deletar carro com sucesso")
    void deleteCarro() {
        Marca marca = new Marca();
        marca.setNomeMarca("Chevrolet");

        Cor cor = new Cor();
        cor.setNomeCor("Preto");

        Carro carroExistente = new Carro();

        carroExistente.setId(1);
        carroExistente.setNomeCarro("Celta");
        carroExistente.setModeloCarro("Flex");
        carroExistente.setAnoModelo(2009);
        carroExistente.setAnoFabricacao(2008);
        carroExistente.setMarca(marca);
        carroExistente.setCores(List.of(cor));

        when(carroRepository.findById(1)).thenReturn(Optional.of(carroExistente));
        doNothing().when(carroRepository).deleteById(1);

        carroService.deleteCarro(1);
    }
}