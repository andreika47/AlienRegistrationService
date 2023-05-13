package com.andreika47.ctf.alienreg.service;

import com.andreika47.ctf.alienreg.controller.IDController;
import com.andreika47.ctf.alienreg.exceptions.IncorrectIDException;
import com.andreika47.ctf.alienreg.model.Registration;
import com.andreika47.ctf.alienreg.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    private Logger logger = LoggerFactory.getLogger(IDController.class);

    @Transactional
    public Registration register(String oldId) throws IncorrectIDException {
        String planet = null;
        Registration registration;

        if (oldId.matches("[A-Z]{5}-P2-[A-Z]{5}-[0-9]{3}")) {
            planet = "P2";
        } else if (oldId.matches("[A-Z]{5}-P3-[A-Z]{5}-[0-9]{3}")) {
            planet = "P3";
        } else if (oldId.matches("[A-Z]{5}-P4-[A-Z]{5}-[0-9]{3}")) {
            planet = "P4";
        } else if (oldId.matches("[A-Z]{5}-P5-[A-Z]{5}-[0-9]{3}")) {
            planet = "P5";
        } else if (oldId.matches("[a-zA-Z0-9]{31}=")) {
            planet = "FLAG";
        }

        if (planet != null) {
            try {
                registration = registrationRepository.saveAndFlush(new Registration(oldId, planet));
                logger.info(registration.toString());
            } catch (Exception e) {
                return null;
            }
            return registration;
        }
        else {
            throw new IncorrectIDException("Unknown alien's ID");
        }
    }

    @Transactional
    public List<Registration> getAllAliens() {
        return registrationRepository.findAll();
    }

    @Transactional
    public List<Registration> getAlienByNewID(String newID) {
        return registrationRepository.findByNewID(newID);
    }
}
