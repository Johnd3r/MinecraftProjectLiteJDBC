package com.gilminecraftjdbc.gildbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import com.gilminecraftjdbc.gildbc.model.enums.TipoLogro;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import lombok.Data;
import java.sql.Date;

@Data
@Table("logro")
public class Logro {
    @Id
    @Column("idLogro")
    private Long idLogro;

    @Column("nombre")
    private String nombre;

    @Column("tipo")
    private TipoLogro tipo;

    @Column("fecha")
    private Date fecha;

    @Column("puntos")
    private Integer puntos;

    @Column("idJugador")
    private AggregateReference<Jugador, Long> idJugador; // Relación N:1
}
