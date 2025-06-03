// MisionEnemigoRepository.java
package com.gilminecraftjdbc.gildbc.repository;

import com.gilminecraftjdbc.gildbc.model.MisionEnemigo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MisionEnemigoRepository extends CrudRepository<MisionEnemigo, Long> {
    // Consulta personalizada para buscar por idMision
    List<MisionEnemigo> findByIdMision(Long idMision);
}