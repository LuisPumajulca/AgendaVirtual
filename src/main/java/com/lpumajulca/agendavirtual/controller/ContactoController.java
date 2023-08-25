package com.lpumajulca.agendavirtual.controller;

import com.lpumajulca.agendavirtual.model.Contacto;
import com.lpumajulca.agendavirtual.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository repository;

    @GetMapping
    String index(@PageableDefault(size = 5, sort = "fechaRegistro", direction = Sort.Direction.DESC) Pageable pageable,
                 @RequestParam(required = false) String busqueda,
                 Model model) {
        Page<Contacto> contactoPage;

        if (busqueda != null && busqueda.trim().length() > 0) {
            contactoPage = repository.findByNombreContaining(busqueda, pageable);
        } else {
            contactoPage = repository.findAll(pageable);
        }
        model.addAttribute("contactoPage", contactoPage);
        return "index";
    }

    @GetMapping("/nuevo")
    String nuevo(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "form";
    }

    @PostMapping("/nuevo")
    String crear(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes ra, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contacto", contacto);
            return "form";
        }
        contacto.setFechaRegistro(LocalDateTime.now());
        repository.save(contacto);

        ra.addFlashAttribute("msgExito", "El contacto se ha creado correctamente");

        return "redirect:";
    }

    @GetMapping("/{id}/editar")
    String editar(@PathVariable Integer id, Model model) {
        Contacto contacto = repository.getReferenceById(id);
        model.addAttribute("contacto", contacto);
        return "form";
    }

    @PostMapping("/{id}/editar")
    String actualizar(@PathVariable Integer id,
                      @Validated Contacto contacto,
                      BindingResult bindingResult,
                      RedirectAttributes ra,
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contacto", contacto);
            return "form";
        }
        Contacto contactoFromDB = repository.getReferenceById(id);
        contactoFromDB.setNombre(contacto.getNombre());
        contactoFromDB.setCelular(contacto.getCelular());
        contactoFromDB.setEmail(contacto.getEmail());
        contactoFromDB.setFechaNacimiento(contacto.getFechaNacimiento());

        repository.save(contactoFromDB);

        ra.addFlashAttribute("msgExito", "El contacto se ha actualizado correctamente");

        return "redirect:";
    }

    @PostMapping("/{id}/eliminar")
    String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        Contacto contacto = repository.getReferenceById(id);
        repository.delete(contacto);

        ra.addFlashAttribute("msgExito", "El contacto se ha eliminado correctamente");

        return "redirect:";
    }
}
