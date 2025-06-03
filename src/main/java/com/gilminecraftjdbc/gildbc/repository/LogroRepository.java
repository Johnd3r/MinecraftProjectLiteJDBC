package com.gilminecraftjdbc.gildbc.repository;

import com.gilminecraftjdbc.gildbc.model.Logro;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface LogroRepository extends CrudRepository<Logro, Long> {
    // Buscar logros por ID de jugador (relación 1:N)
    List<Logro> findByIdJugador(Long idJugador);
}