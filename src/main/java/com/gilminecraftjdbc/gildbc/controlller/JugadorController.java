package com.gilminecraftjdbc.gildbc.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gilminecraftjdbc.gildbc.model.Jugador;
import com.gilminecraftjdbc.gildbc.model.Logro;
import com.gilminecraftjdbc.gildbc.model.Mision;
import com.gilminecraftjdbc.gildbc.repository.JugadorRepository;
import com.gilminecraftjdbc.gildbc.repository.LogroRepository;
import com.gilminecraftjdbc.gildbc.repository.MisionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/jugadores")
@RequiredArgsConstructor // Lombok: inyección de dependencias
public class JugadorController {

    private final JugadorRepository jugadorRepo;
    private final MisionRepository misionRepo;
    private final LogroRepository logroRepo;

    // --- Punto 1: Formulario para crear Jugador + Misión (1:1) ---
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("mision", new Mision());
        return "jugadores/formulario-nuevo";
    }

    @PostMapping("/guardar")
    public String guardarJugadorYMision(
        @ModelAttribute Jugador jugador,
        @ModelAttribute Mision mision
    ) {
        jugadorRepo.save(jugador);
        mision.setIdJugador(org.springframework.data.jdbc.core.mapping.AggregateReference.to(jugador.getIdJugador())); // Relación 1:1
        misionRepo.save(mision);
        return "redirect:/jugadores";
    }

    // --- Punto 2: Ver Jugadores y su Misión ---
    @GetMapping
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorRepo.findAll());
        return "jugadores/lista";
    }

    @GetMapping("/{id}/mision")
    public String verMisionDeJugador(@PathVariable Long id, Model model) {
        model.addAttribute("mision", misionRepo.findByIdJugador(id));
        return "misiones/detalle";
    }

    // --- Puntos 3-4: Logros (1:N) ---
    @GetMapping("/{id}/logros/nuevo")
    public String formNuevoLogro(@PathVariable Long id, Model model) {
        model.addAttribute("logro", new Logro());
        model.addAttribute("idJugador", id);
        return "logros/formulario-nuevo";
    }

    @PostMapping("/{id}/logros/guardar")
    public String guardarLogro(
        @PathVariable Long id,
        @ModelAttribute Logro logro
    ) {
        logro.setIdJugador(org.springframework.data.jdbc.core.mapping.AggregateReference.to(id));
        logroRepo.save(logro);
        return "redirect:/jugadores/" + id + "/logros";
    }

    @GetMapping("/{id}/logros")
    public String verLogrosDeJugador(@PathVariable Long id, Model model) {
        model.addAttribute("logros", logroRepo.findByIdJugador(id));
        return "logros/lista";
    }
}