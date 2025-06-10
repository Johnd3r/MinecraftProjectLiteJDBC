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
import com.gilminecraftjdbc.gildbc.repository.EnemigoRepository;
import com.gilminecraftjdbc.gildbc.repository.JugadorRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/enemigos") // ✅ esto es lo que hace que /enemigos/... funcione
@RequiredArgsConstructor
public class EnemigoController {
    
    private final EnemigoRepository enemigoRepo;
    private final JugadorRepository jugadorRepo; // necesario si mostramos nombre del jugador

    @GetMapping("/{idEnemigo}/misiones")
    public String verMisionesDeEnemigo(@PathVariable Long idEnemigo, Model model) {
        List<Mision> misiones = enemigoRepo.findMisionesByEnemigoId(idEnemigo);
        Map<Long, String> nombresJugadores = misiones.stream()
            .map(m -> m.getIdJugador().getId())
            .distinct()
            .collect(Collectors.toMap(
                id -> id,
                id -> jugadorRepo.findById(id).map(Jugador::getNombre).orElse("Desconocido")
            ));

        model.addAttribute("misiones", misiones);
        model.addAttribute("nombresJugadores", nombresJugadores);
        return "enemigos/misiones"; // ✅ verifica que este HTML esté dentro de templates/enemigos/
    }
}

