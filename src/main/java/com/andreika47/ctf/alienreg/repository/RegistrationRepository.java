package com.andreika47.ctf.alienreg.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.andreika47.ctf.alienreg.model.Registration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r FROM Registration r WHERE r.newID = :newID")
    List<Registration> findByNewID(@Param("newID") String newID);
}