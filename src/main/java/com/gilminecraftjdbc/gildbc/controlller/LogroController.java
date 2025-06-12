package com.gilminecraftjdbc.gildbc.controlller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.gilminecraftjdbc.gildbc.repository.LogroRepository;
import com.gilminecraftjdbc.gildbc.repository.JugadorRepository;

@Controller // ← ESTO FALTABA
public class LogroController {

    private final LogroRepository logroRepo;
    private final JugadorRepository jugadorRepo;

    public LogroController(LogroRepository logroRepo, JugadorRepository jugadorRepo) {
        this.logroRepo = logroRepo;
        this.jugadorRepo = jugadorRepo;
    }

    @GetMapping("/logros")
    public String listarTodosLosLogros(Model model) {
        var logros = logroRepo.findAll();

        Map<Long, String> nombresJugadores = new HashMap<>();
        logros.forEach(logro -> {
            Long idJugador = logro.getIdJugador().getId();
            jugadorRepo.findById(idJugador).ifPresent(jugador ->
                nombresJugadores.put(idJugador, jugador.getNombre())
            );
        });

        model.addAttribute("logros", logros);
        model.addAttribute("nombresJugadores", nombresJugadores);
        return "jugadores/logros/lista-global";
    }
}
