package com.andreika47.ctf.alienreg.controller;

import com.andreika47.ctf.alienreg.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("admin")
    public String admin(Model model) {
        model.addAttribute("registrations", registrationService.getAllAliens());
        return "admin";
    }
}