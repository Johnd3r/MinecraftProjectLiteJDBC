package com.gilminecraftjdbc.gildbc.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.gilminecraftjdbc.gildbc.model.Enemigo;
import com.gilminecraftjdbc.gildbc.model.Mision;

public interface EnemigoRepository extends CrudRepository<Enemigo, Long> {
    
    // ---- Consultas para la relación N:M con Mision ----

    // 1. Asignar un enemigo a una misión (INSERT en tabla intermedia)
    @Modifying
    @Query("INSERT INTO misionEnemigo (idMision, idEnemigo, cantidad) VALUES (:idMision, :idEnemigo, :cantidad)")
    void asignarAMision(
        @Param("idMision") Long idMision,
        @Param("idEnemigo") Long idEnemigo,
        @Param("cantidad") int cantidad
    );

    // 2. Obtener todos los enemigos de una misión (JOIN)
    @Query("""
        SELECT e.* FROM enemigo e
        INNER JOIN misionEnemigo me ON e.idEnemigo = me.idEnemigo
        WHERE me.idMision = :idMision
    """)
    List<Enemigo> findByMisionId(@Param("idMision") Long idMision);

    // 3. Obtener todas las misiones de un enemigo (consulta inversa)
    @Query("""
        SELECT m.* FROM mision m
        INNER JOIN misionEnemigo me ON m.idMision = me.idMision
        WHERE me.idEnemigo = :idEnemigo
    """)
    List<Mision> findMisionesByEnemigoId(@Param("idEnemigo") Long idEnemigo);
}