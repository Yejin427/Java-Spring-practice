package com.example.practice2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PracController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "yejin");
        return "greetings";
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "yejin");
        return "goodbye";
    }
}
