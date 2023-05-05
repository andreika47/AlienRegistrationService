package com.andreika47.ctf.alienreg.model;

import com.mifmif.common.regex.Generex;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "aliens")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "planet", length = 32, nullable = false)
    private String planet;
    @Column(name = "oldID", length = 32, nullable = false)
    private String oldID;
    @Column(name = "newID", length = 32, nullable = false)
    private String newID;

    public Registration() {

    }

    public Registration(String oldID, String planet) {
        this.oldID = oldID;
        this.planet = planet;

        Generex generexP1 = new Generex("[0-9]{3}-[A-Z]{5}-[0-9]{3}-P1");
        newID = generexP1.random();
    }

    public Registration(String oldID, String newID, String planet) {
        this.oldID = oldID;
        this.newID = newID;
        this.planet = planet;
    }

    public long getId() {
        return id;
    }

    public String getOldID() {
        return oldID;
    }

    public void setOldID(String id) {
        this.oldID = id;
    }

    public String getNewID() {
        return newID;
    }

    public void setNewID(String id) {
        this.newID = id;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", old ID='" + oldID + '\'' +
                ", new ID='" + newID + '\'' +
                ", planet='" + planet + '\'' +
                '}';
    }
}