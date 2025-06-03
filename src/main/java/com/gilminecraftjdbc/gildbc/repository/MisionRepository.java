package com.gilminecraftjdbc.gildbc.repository;

import com.gilminecraftjdbc.gildbc.model.Mision;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface MisionRepository extends CrudRepository<Mision, Long> {
    // Buscar misión por ID de jugador (relación 1:1)
    Optional<Mision> findByIdJugador(Long idJugador);
}