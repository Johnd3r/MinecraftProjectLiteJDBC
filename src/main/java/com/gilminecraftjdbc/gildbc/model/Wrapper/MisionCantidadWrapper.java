package com.gilminecraftjdbc.gildbc.model.Wrapper;

import com.gilminecraftjdbc.gildbc.model.Mision;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MisionCantidadWrapper {
    private Mision mision;
    private int cantidad;
}
