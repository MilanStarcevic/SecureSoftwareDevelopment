package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

@Controller
public class PersonsController {
    private PersonRepository personRepository;

    public PersonsController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons/{id}")
    public String showLoginForm(@PathVariable int id, Model model) {
        model.addAttribute("person", personRepository.get(id));
        return "person";
    }

    @GetMapping("/persons")
    public String showLoginForm(Model model) {
        model.addAttribute("persons", personRepository.getAll());
        return "persons";
    }
}
