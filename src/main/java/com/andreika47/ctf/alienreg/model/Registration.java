package com.andreika47.ctf.alienreg.model;

import com.mifmif.common.regex.Generex;

import javax.persistence.*;

@Entity
@Table(name = "aliens")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "planet")
    private String planet;
    @Column(name = "oldID")
    private String oldID;
    @Column(name = "newID")
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