package com.gilminecraftjdbc.gildbc.model;
import com.gilminecraftjdbc.gildbc.model.enums.TipoJugador;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("jugador")
public class Jugador {
    @Id
    @Column("idJugador")
    private Long idJugador;
    @Column("nombre")
    private String nombre;
    @Column("nivel")
    private Integer nivel;
    @Column("experiencia")
    private Float experiencia;
    @Column("tipoJugador")
    private TipoJugador tipoJugador; // Enum
}
