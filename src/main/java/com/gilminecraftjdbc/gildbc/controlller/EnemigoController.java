package com.gilminecraftjdbc.gildbc.controlller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gilminecraftjdbc.gildbc.model.Jugador;
import com.gilminecraftjdbc.gildbc.model.Mision;
import com.gilminecraftjdbc.gildbc.model.Wrapper.MisionCantidadWrapper;
import com.gilminecraftjdbc.gildbc.model.projection.MisionConCantidad;
import com.gilminecraftjdbc.gildbc.repository.EnemigoRepository;
import com.gilminecraftjdbc.gildbc.repository.JugadorRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/enemigos")
@RequiredArgsConstructor
public class EnemigoController {

    private final EnemigoRepository enemigoRepo;
    private final JugadorRepository jugadorRepo;

    @GetMapping
    public String listarEnemigos(Model model) {
        model.addAttribute("enemigos", enemigoRepo.findAll());
        return "enemigos/lista";
    }

    /* @GetMapping("/{idEnemigo}/misiones")
    public String verMisionesDeEnemigo(@PathVariable Long idEnemigo, Model model) {
        List<Mision> misiones = enemigoRepo.findMisionesByEnemigoId(idEnemigo);

        Map<Long, String> nombresJugadores = misiones.stream()
                .map(m -> m.getIdJugador().getId())
                .distinct()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> jugadorRepo.findById(id).map(Jugador::getNombre).orElse("Desconocido")));

        model.addAttribute("misiones", misiones);
        model.addAttribute("nombresJugadores", nombresJugadores);
        return "enemigos/misiones"; // Asegúrate de tener este archivo
    } */

    @GetMapping("/{idEnemigo}/misiones")
public String verMisionesConCantidad(@PathVariable Long idEnemigo, Model model) {
    // Obtener las misiones asignadas al enemigo
    List<Mision> misiones = enemigoRepo.findMisionesByEnemigoId(idEnemigo);

    // Obtener cantidad de enemigos para cada misión desde método adicional
    List<MisionCantidadWrapper> relaciones = misiones.stream().map(m -> {
        Integer cantidad = enemigoRepo.findCantidadByMisionAndEnemigo(m.getIdMision(), idEnemigo);
        return new MisionCantidadWrapper(m, cantidad != null ? cantidad : 0);
    }).toList();

    model.addAttribute("relaciones", relaciones);
    return "enemigos/misiones";
}



}
