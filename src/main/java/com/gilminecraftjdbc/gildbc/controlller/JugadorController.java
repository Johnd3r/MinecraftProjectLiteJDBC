package com.gilminecraftjdbc.gildbc.controlller;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
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

// JugadorController - Versión corregida
@Controller
@RequestMapping("/jugadores")
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorRepository jugadorRepo;
    private final MisionRepository misionRepo;
    private final LogroRepository logroRepo;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("mision", new Mision());
        return "jugadores/nuevo";
    }

    @PostMapping
    public String guardarJugadorYMision(@ModelAttribute Jugador jugador, @ModelAttribute Mision mision) {
        jugadorRepo.save(jugador);
        mision.setIdJugador(AggregateReference.to(jugador.getIdJugador()));
        misionRepo.save(mision);
        return "redirect:/jugadores";
    }

    @GetMapping
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorRepo.findAll());
        return "jugadores/lista";
    }

    @GetMapping("/{id}/mision")
    public String verMisionDeJugador(@PathVariable Long id, Model model) {
        Mision mision = misionRepo.findByIdJugador(id).orElse(null);
        model.addAttribute("mision", mision);
        return "jugadores/mision";
    }

    @GetMapping("/{id}/logros/nuevo")
    public String formNuevoLogro(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepo.findById(id).orElseThrow();
        model.addAttribute("jugador", jugador);
        model.addAttribute("logro", new Logro());
        return "jugadores/logros/nuevo";
    }

    @PostMapping("/{id}/logros/guardar")
    public String guardarLogro(@PathVariable Long id, @ModelAttribute Logro logro) {
        logro.setIdJugador(AggregateReference.to(id));
        logroRepo.save(logro);
        return "redirect:/jugadores/" + id + "/logros";
    }

    @GetMapping("/{id}/logros")
    public String verLogrosDeJugador(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepo.findById(id).orElseThrow();
        model.addAttribute("jugador", jugador);
        model.addAttribute("logros", logroRepo.findByIdJugador(id));
        return "jugadores/logros/lista";
    }
}
