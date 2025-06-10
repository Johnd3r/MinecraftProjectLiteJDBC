package com.gilminecraftjdbc.gildbc.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/")
    public String inicio() {
        return "base"; // busca templates/base.html
    }
}
