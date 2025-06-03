package com.gilminecraftjdbc.gildbc.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import com.gilminecraftjdbc.gildbc.model.enums.EstadoEnemigo;
import com.gilminecraftjdbc.gildbc.model.enums.TipoEnemigo;
import lombok.Data;

@Data
@Table("enemigo")
public class Enemigo {
    @Id
    @Column("idEnemigo")
    private Long idEnemigo;

    @Column("nombre")
    private String nombre;

    @Column("tipo")
    private TipoEnemigo tipo;

    @Column("vida")
    private Integer vida;

    @Column("poder")
    private Integer poder;

    @Column("estado")
    private EstadoEnemigo estado;
}