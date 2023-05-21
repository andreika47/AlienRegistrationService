package com.andreika47.ctf.alienreg.controller;

import com.andreika47.ctf.alienreg.exceptions.IncorrectIDException;
import com.andreika47.ctf.alienreg.model.ID;
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
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

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
    public String register(HttpServletRequest request, Model model) {
        String alienID = getID(request.getCookies());
        model.addAttribute("id", new ID());

        if (alienID == null) {
            return "register";
        }
        return "redirect:/id";
    }

    @PostMapping("/register")
    public String submit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ID id, Model model) {
        String alienID = getID(request.getCookies());

        if (alienID != null) {
            return "redirect:/id";
        }

        if (id != null && id.getId() != null) {
            logger.info(id.getId());
            try {
                Registration registration = registrationService.register(id.getId());

                if (registration != null) {
                    Cookie cookie = new Cookie("AlienID", registration.getNewID());
                    cookie.setMaxAge(30 * 24 * 60 * 60); // 30 дней
                    cookie.setSecure(false);    // сервис не использует HTTPS
                    cookie.setHttpOnly(true);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                else {
                    model.addAttribute("error", "Unable to register alien wit ID " + id.getId());
                    return "redirect:/error";
                }

            } catch (IncorrectIDException e) {
                model.addAttribute("error", e.getMessage());
                return "redirect:/error";
            }

            model.addAttribute("id", id);
            return "redirect:/id";
        }
        else {
            model.addAttribute("error", "Missing ID parameter");
            return "redirect:/error";
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