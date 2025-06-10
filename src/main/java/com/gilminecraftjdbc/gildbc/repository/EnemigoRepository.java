package com.gilminecraftjdbc.gildbc.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.gilminecraftjdbc.gildbc.model.Enemigo;
import com.gilminecraftjdbc.gildbc.model.Mision;

// En EnemigoRepository
public interface EnemigoRepository extends CrudRepository<Enemigo, Long> {
    @Query("SELECT e.* FROM enemigo e JOIN misionEnemigo me ON e.idEnemigo = me.idEnemigo WHERE me.idMision = :idMision")
    List<Enemigo> findByMisionId(@Param("idMision") Long idMision);

    @Query("SELECT m.* FROM mision m JOIN mision_enemigo me ON m.id_mision = me.id_mision WHERE me.id_enemigo = :idEnemigo")
    List<Mision> findMisionesByEnemigoId(@Param("idEnemigo") Long idEnemigo);

    @Modifying
    @Query("INSERT INTO mision_enemigo (id_mision, id_enemigo, cantidad) VALUES (:idMision, :idEnemigo, :cantidad)")
    void asignarAMision(Long idMision, Long idEnemigo, int cantidad);
}