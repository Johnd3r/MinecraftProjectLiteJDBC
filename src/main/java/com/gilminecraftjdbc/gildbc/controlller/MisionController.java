package com.gilminecraftjdbc.gildbc.controlller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gilminecraftjdbc.gildbc.model.Enemigo;
import com.gilminecraftjdbc.gildbc.repository.EnemigoRepository;
import com.gilminecraftjdbc.gildbc.repository.MisionRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/misiones")
@RequiredArgsConstructor
public class MisionController {

    private final EnemigoRepository enemigoRepo;
    private final MisionRepository misionRepo;

    @GetMapping("/enemigos/nuevo")
    public String formNuevoEnemigo(Model model) {
        model.addAttribute("enemigo", new Enemigo());
        return "enemigos/nuevo";
    }

    @PostMapping("/enemigos/guardar")
    public String guardarEnemigo(@ModelAttribute Enemigo enemigo) {
        enemigoRepo.save(enemigo);
        return "redirect:/misiones/enemigos";
    }

    @GetMapping("/enemigos")
    public String listarEnemigos(Model model) {
        model.addAttribute("enemigos", enemigoRepo.findAll());
        return "enemigos/lista";
    }

    @GetMapping("/{idMision}/asignar-enemigos")
    public String formAsignarEnemigos(@PathVariable Long idMision, Model model) {
        model.addAttribute("mision", misionRepo.findById(idMision).orElseThrow());
        model.addAttribute("enemigos", enemigoRepo.findAll());
        return "misiones/asignar-enemigos";
    }

    @PostMapping("/{idMision}/asignar-enemigos")
    public String asignarEnemigos(
        @PathVariable Long idMision,
        @RequestParam(name = "enemigosSeleccionados", required = false) Long[] enemigosSeleccionados,
        @RequestParam Map<String, String> cantidades
    ) {
        if (enemigosSeleccionados != null) {
            for (Long idEnemigo : enemigosSeleccionados) {
                int cantidad = Integer.parseInt(cantidades.get("cantidades[" + idEnemigo + "]"));
                enemigoRepo.asignarAMision(idMision, idEnemigo, cantidad);
            }
        }
        return "redirect:/misiones/" + idMision + "/enemigos";
    }

    @GetMapping("/{idMision}/enemigos")
    public String verEnemigosDeMision(@PathVariable Long idMision, Model model) {
        model.addAttribute("mision", misionRepo.findById(idMision).orElseThrow());
        model.addAttribute("enemigos", enemigoRepo.findByMisionId(idMision));
        return "misiones/enemigos";
    }
}
