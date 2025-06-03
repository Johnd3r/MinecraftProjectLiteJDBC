package com.gilminecraftjdbc.gildbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("misionEnemigo")
public class MisionEnemigo {
    @Id
    @Column("idMision")
    private Long idMision;

    @Column("idEnemigo")
    private Long idEnemigo;

    @Column("cantidad")
    private Integer cantidad;
}