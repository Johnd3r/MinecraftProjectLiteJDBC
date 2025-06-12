package com.gilminecraftjdbc.gildbc.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/")
    public String inicio() {
        return "base"; // busca templates/base.html
    }/* 
    @GetMapping("/misiones")
    public String misiones() {
        return ""; // busca templates/base.html
    }
    @GetMapping("/enemigos")
    public String inicio() {
        return "base"; // busca templates/base.html
    } */
   /* @GetMapping("/logros")
    public String inicio() {
        return "base"; // busca templates/base.html
    }  */
}
