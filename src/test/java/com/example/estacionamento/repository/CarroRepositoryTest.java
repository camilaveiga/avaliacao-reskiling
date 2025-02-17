package com.example.estacionamento.repository;

import com.example.estacionamento.model.Carro;
import com.example.estacionamento.model.Cor;
import com.example.estacionamento.model.Marca;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CarroRepository carroRepository;

    @Test
    @DisplayName("Criar carro com sucesso")
    void findByIdSuccess() {

        Marca marca = new Marca();
        marca.setNomeMarca("Chevrolet");
        entityManager.persist(marca);

        Cor cor = new Cor();
        cor.setNomeCor("Preto");
        entityManager.persist(cor);

        Carro data = new Carro();
        data.setNomeCarro("celta");
        data.setModeloCarro("Flex");
        data.setAnoModelo(2009);
        data.setAnoFabricacao(2008);
        data.setMarca(marca);
        data.setCores(List.of(cor));

        this.createdCarro(data);

        Integer id = 1;
        Optional<Carro> result = this.carroRepository.findById(id);

        assertThat(result.isPresent()).isTrue();
    }

    private Carro createdCarro(Carro data){
        this.entityManager.merge(data);
        return data;
    }
}
