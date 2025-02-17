package com.example.estacionamento.controller;

import com.example.estacionamento.model.Carro;
import com.example.estacionamento.model.Cor;
import com.example.estacionamento.model.Marca;
import com.example.estacionamento.service.CarroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarroControllerTest {

    @InjectMocks
    CarroController carroController;

    @Mock
    CarroService carroService;

    @Test
    @DisplayName("Listar todos os carros controller")
    void getAllCarros() {
        Carro carro1 = new Carro();
        Carro carro2 = new Carro();

        Marca marca = new Marca();
        marca.setNomeMarca("Chevrolet");
        marca.setId(1);

        Cor cor = new Cor();
        cor.setId(1);
        cor.setNomeCor("Preto");

        carro1.setId(1);
        carro1.setNomeCarro("celta");
        carro1.setModeloCarro("Flex");
        carro1.setAnoModelo(2009);
        carro1.setAnoFabricacao(2008);
        carro1.setMarca(marca);
        carro1.setCores(List.of(cor));

        carro1.setId(2);
        carro1.setNomeCarro("tucson");
        carro1.setModeloCarro("Flex");
        carro1.setAnoModelo(2016);
        carro1.setAnoFabricacao(2017);
        carro1.setMarca(marca);
        carro1.setCores(List.of(cor));

        List<Carro> carros = Arrays.asList(carro1, carro2);
        when(carroService.getAllCarros()).thenReturn(carros);

        List<Carro> result = carroController.getAllCarros();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Criar carros controller")
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

        when(carroService.createCarro(any(Carro.class))).thenReturn(carroSalvo);
        Carro result = carroController.createCarro(novoCarro);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getNomeCarro()).isEqualTo("celta");

    }

    @Test
    @DisplayName("Atualizar carro com sucesso controller")
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

        when(carroService.updateCarro(anyInt(), any(Carro.class))).thenReturn(carroAtualizado);

        Carro result = carroService.updateCarro(1, carroAtualizado);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getNomeCarro()).isEqualTo("Celta 2.0");
        assertThat(result.getAnoFabricacao()).isEqualTo(2009);
    }

    @Test
    @DisplayName("Deletar carro com sucesso controller")
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

        doNothing().when(carroService).deleteCarro(1);
        carroController.deleteCarro(1);

    }
}