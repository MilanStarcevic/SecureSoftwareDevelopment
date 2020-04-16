package com.zuehlke.securesoftwaredevelopment.controller;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    // send totp to DatabaseAuthenticationProvider
    // Check using https://github.com/wstrange/GoogleAuth

    @GetMapping("/register-totp")
    public String showRegisterTotp(Model model) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();

        model.addAttribute("totpKey", key.getKey());
        return "totp";
    }

    @PostMapping("/register-totp")
    public String registerTotp(@RequestParam() String totpKey, Model model) {
        // save totp to user table
        model.addAttribute("registered", true);
        return "totp"; // change url
    }
}
