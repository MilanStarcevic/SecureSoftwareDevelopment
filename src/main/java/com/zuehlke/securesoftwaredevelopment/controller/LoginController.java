package com.zuehlke.securesoftwaredevelopment.controller;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.zuehlke.securesoftwaredevelopment.domain.HashedUser;
import com.zuehlke.securesoftwaredevelopment.repository.HashedUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final HashedUserRepository repository;

    LoginController(HashedUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register-totp")
    public String showRegisterTotp(Model model, Authentication authentication) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();

        final GoogleAuthenticatorKey key = gAuth.createCredentials(); // TOTP key
        model.addAttribute("totpKey", key.getKey());

        // Used for QR Code
        final HashedUser user = (HashedUser) authentication.getPrincipal();;
        String totpUrl = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("Insecure Car Store", user.getUsername(), key);
        model.addAttribute("totpUrl", totpUrl);

        return "register-totp";
    }

    @PostMapping("/register-totp")
    public String registerTotp(@RequestParam() String totpKey, Model model, Authentication authentication) {
        final HashedUser user = (HashedUser) authentication.getPrincipal();
        repository.saveTotpKey(user.getUsername(), totpKey);
        model.addAttribute("registered", true);
        return "register-totp";
    }
}
