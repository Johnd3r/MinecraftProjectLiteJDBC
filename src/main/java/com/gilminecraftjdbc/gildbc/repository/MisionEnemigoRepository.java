// MisionEnemigoRepository.java
package com.gilminecraftjdbc.gildbc.repository;

import com.gilminecraftjdbc.gildbc.model.MisionEnemigo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MisionEnemigoRepository extends CrudRepository<MisionEnemigo, Long> {

    // Buscar enemigos por ID de misión
    List<MisionEnemigo> findByIdMision(Long idMision);
    
    // Buscar misiones por ID de enemigo
    List<MisionEnemigo> findByIdEnemigo(Long idEnemigo);
}