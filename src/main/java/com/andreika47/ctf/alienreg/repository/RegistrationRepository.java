package com.andreika47.ctf.alienreg.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.andreika47.ctf.alienreg.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByOldID(String oldID);
}