package com.gilminecraftjdbc.gildbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import com.gilminecraftjdbc.gildbc.model.enums.Dificultad;
import com.gilminecraftjdbc.gildbc.model.enums.EstadoMision;
import lombok.Data;

@Data
@Table("mision")
public class Mision {
    @Id
    @Column("idMision")
    private Long idMision;

    @Column("titulo")
    private String titulo;

    @Column("dificultad")
    private Dificultad dificultad;

    @Column("recompensaXP")
    private Integer recompensaXP;

    @Column("estadoMision")
    private EstadoMision estadoMision;

    @Column("idJugador")
    /* private Long idJugador; // FK directa (sin AggregateReference para simplificar) */
    private AggregateReference<Jugador,Long> idJugador;
}