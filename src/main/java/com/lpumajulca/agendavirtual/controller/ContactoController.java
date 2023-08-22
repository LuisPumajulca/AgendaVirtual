package com.lpumajulca.agendavirtual.controller;

import com.lpumajulca.agendavirtual.model.Contacto;
import com.lpumajulca.agendavirtual.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository repository;

    @GetMapping
    String index(Model model) {
        List<Contacto> contatos = repository.findAll();
        model.addAttribute("contactos", contatos);
        return "index";
    }

    @GetMapping("/nuevo")
    String nuevo(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "nuevo";
    }
}
