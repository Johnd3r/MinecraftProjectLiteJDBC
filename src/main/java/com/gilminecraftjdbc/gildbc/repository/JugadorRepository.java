package com.gilminecraftjdbc.gildbc.repository;

import com.gilminecraftjdbc.gildbc.model.Jugador;
import org.springframework.data.repository.CrudRepository;

public interface JugadorRepository extends CrudRepository<Jugador, Long> {
    // Consulta derivada para buscar por nombre
    /* Jugador findByNombre(String nombre); */
}