package com.gilminecraftjdbc.gildbc.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.gilminecraftjdbc.gildbc.model.Enemigo;
import com.gilminecraftjdbc.gildbc.model.Mision;
import com.gilminecraftjdbc.gildbc.model.projection.MisionConCantidad;

// En EnemigoRepository
public interface EnemigoRepository extends CrudRepository<Enemigo, Long> {
    @Query("SELECT e.* FROM enemigo e JOIN misionenemigo me ON e.idEnemigo = me.idEnemigo WHERE me.idMision = :idMision")
    List<Enemigo> findByMisionId(@Param("idMision") Long idMision);

    @Query("SELECT m.* FROM mision m JOIN misionenemigo me ON m.idMision = me.idMision WHERE me.idEnemigo = :idEnemigo")
    List<Mision> findMisionesByEnemigoId(@Param("idEnemigo") Long idEnemigo);

    @Modifying
    @Query("INSERT INTO misionenemigo (idMision, idEnemigo, cantidad) VALUES (:idMision, :idEnemigo, :cantidad)")
    void asignarAMision(Long idMision, Long idEnemigo, int cantidad);

    @Query("SELECT m.idMision, m.titulo, m.dificultad, m.recompensaXP, m.estadoMision, me.cantidad " +
            "FROM mision m JOIN misionenemigo me ON m.idMision = me.idMision " +
            "WHERE me.idEnemigo = :idEnemigo")
    List<MisionConCantidad> findMisionesYCantidadByEnemigoId(@Param("idEnemigo") Long idEnemigo);

    @Query("SELECT cantidad FROM misionenemigo WHERE idMision = :idMision AND idEnemigo = :idEnemigo")
    Integer findCantidadByMisionAndEnemigo(@Param("idMision") Long idMision, @Param("idEnemigo") Long idEnemigo);

}