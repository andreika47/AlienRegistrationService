package com.andreika47.ctf.alienreg.controller;

import com.andreika47.ctf.alienreg.model.Registration;
import com.andreika47.ctf.alienreg.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        String id = getID(request.getCookies());

        if (id == null) {
            return "redirect:/register";
        }
        else {
            return "redirect:/id";
        }
    }

    @GetMapping("/register")
    public String register(HttpServletRequest request) {
        String id = getID(request.getCookies());

        if (id == null) {
            return "register";
        }
        else {
            return "redirect:/id";
        }
    }

    @PostMapping("/id")
    public String register(String id, HttpServletResponse response, Model model) {
        String planet = null;

        if (id.matches("[A-Z]{5}-P2-[A-Z]{5}-[0-9]{3}")) {
            planet = "P2";
        } else if (id.matches("[A-Z]{5}-P3-[A-Z]{5}-[0-9]{3}")) {
            planet = "P3";
        }else if (id.matches("[A-Z]{5}-P4-[A-Z]{5}-[0-9]{3}")) {
            planet = "P4";
        }else if (id.matches("[A-Z]{5}-P5-[A-Z]{5}-[0-9]{3}")) {
            planet = "P5";
        }

        if (planet == null) {
            return "redirect:/error";
        }
        else {
            try {
                Registration registration = registrationRepository.save(new Registration(id, planet));

                // Store the ID in a cookie
                Cookie cookie = new Cookie("AlienID", id);
                cookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
                response.addCookie(cookie);

                model.addAttribute("planet", planet);
                model.addAttribute("newID", registration.getNewID());
            } catch (Exception e) {
                return "redirect:/error";
            }
            return "redirect:/id";
        }
    }

    @GetMapping("/id")
    public String id(HttpServletRequest request, Model model) {
        String id = getID(request.getCookies());

        if (id == null) {
            return "redirect:/error";
        }
        else {
            List<Registration> registrations = registrationRepository.findByOldID(id);

            if (registrations.size() > 0) {
                model.addAttribute("planet", registrations.get(0).getPlanet());
                model.addAttribute("ids", registrations);
            }
            return "id";
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