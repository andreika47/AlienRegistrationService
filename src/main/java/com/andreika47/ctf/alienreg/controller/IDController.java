package com.andreika47.ctf.alienreg.controller;

import com.andreika47.ctf.alienreg.model.Registration;
import com.andreika47.ctf.alienreg.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IDController {
    @Autowired
    private RegistrationService registrationService;
    private Logger logger = LoggerFactory.getLogger(IDController.class);

    @GetMapping("/id")
    public String id(HttpServletRequest request, Model model) {
        String id = getID(request.getCookies());

        if (id != null) {
            logger.info("AlienID: " + id);
            List<Registration> registrations = registrationService.getAlienByNewID(id);
            logger.info(registrations.toString());

            if (registrations.size() > 0) {
                Registration alien = registrations.get(0);
                model.addAttribute("planet", alien.getPlanet());
                model.addAttribute("newID", alien.getNewID());
            }
            else {
                model.addAttribute("error", "Unknown alien's ID");
                return "redirect:/error";
            }
            return "id";
        }
        else {
            return "redirect:/register";
        }
    }

    private String getID(Cookie[] cookies) {
        String id = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("AlienID")) {
                    id = cookie.getValue();
                    break;
                }
            }
        }
        return id;
    }
}