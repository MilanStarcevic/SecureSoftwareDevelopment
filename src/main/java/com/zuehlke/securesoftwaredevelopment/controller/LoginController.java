package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model) {
        boolean validCredentials = userRepository.validCredentials(loginForm.getUsername(), loginForm.getPassword());
        if (validCredentials) {
            return "home";
        }
        model.addAttribute("error", "No user");
        return "login";
    }
}
