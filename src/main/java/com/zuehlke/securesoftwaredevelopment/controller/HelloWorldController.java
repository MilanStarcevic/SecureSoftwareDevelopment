package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Person;
import com.zuehlke.securesoftwaredevelopment.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloWorldController {

    private final PersonRepository personRepository;

    public HelloWorldController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/hello-world")
    public String helloWorld(Model model) {
        List<Person> personList = personRepository.getAll();
        model.addAttribute("personList", personList);
        return "hello-world";
    }
}
