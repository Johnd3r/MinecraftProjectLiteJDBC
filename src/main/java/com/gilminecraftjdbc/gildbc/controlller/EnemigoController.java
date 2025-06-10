package com.gilminecraftjdbc.gildbc.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gilminecraftjdbc.gildbc.repository.EnemigoRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/enemigos")
@RequiredArgsConstructor
public class EnemigoController {

    private final EnemigoRepository enemigoRepo;

    @GetMapping("/{idEnemigo}/misiones")
    public String verMisionesDeEnemigo(@PathVariable Long idEnemigo, Model model) {
        model.addAttribute("misiones", enemigoRepo.findMisionesByEnemigoId(idEnemigo));
        return "enemigos/misiones";
    }
}
