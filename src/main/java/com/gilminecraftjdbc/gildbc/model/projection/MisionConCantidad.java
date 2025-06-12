package com.gilminecraftjdbc.gildbc.model.projection;

import com.gilminecraftjdbc.gildbc.model.enums.Dificultad;
import com.gilminecraftjdbc.gildbc.model.enums.EstadoMision;

public interface MisionConCantidad {
    Long getIdMision();
    String getTitulo();
    Dificultad getDificultad();
    Integer getRecompensaXP();
    EstadoMision getEstadoMision();
    Integer getCantidad();
}
