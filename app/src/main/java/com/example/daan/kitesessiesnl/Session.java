package com.example.daan.kitesessiesnl;

//Module class for a session.
public class Session {

    private String name, kite, time, spot;

    //Constructor
    public Session(String name, String kite, String time, String spot) {
        this.name = name;
        this.kite = kite;
        this.time = time;
        this.spot = spot;
    }

    // Getters & setters.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKite() {
        return kite;
    }

    public void setKite(String kite) {
        this.kite = kite;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }
}
