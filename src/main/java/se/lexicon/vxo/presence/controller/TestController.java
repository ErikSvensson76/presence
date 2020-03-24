package se.lexicon.vxo.presence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/navbar")
    public String getNav(){
        return "navbar";
    }
}
