package com.example.authorinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorInfoController {

    @GetMapping("/author")
    public String getAuthorInfo(Model model) {
        model.addAttribute("name", "Власова Марина");
        model.addAttribute("group", "ИП-217");
        return "author"; // название HTML-шаблона без расширения
    }
}
