package com.andreika47.ctf.alienreg.controller;

import com.andreika47.ctf.alienreg.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("admin")
    public String admin(Model model) {
        model.addAttribute("registrations", registrationRepository.findAll());
        return "admin";
    }
}