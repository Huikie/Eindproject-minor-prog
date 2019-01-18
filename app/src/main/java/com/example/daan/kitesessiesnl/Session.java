package com.example.daan.kitesessiesnl;

//Module class for a session.
public class Session implements Comparable<Session> {

    private String name, kite, time, spot, date;

    //Constructor
    public Session(String name, String kite, String time, String spot, String date) {
        this.name = name;
        this.kite = kite;
        this.time = time;
        this.spot = spot;
        this.date = date;
    }

    /** This method defines a way to sort the list of sessions alphabetically based on the spot name.
     * Source: https://stackoverflow.com/questions/22871583/implements-comparable-to-get-alphabetical-sort-with-strings.*/
    @Override
    public int compareTo(Session other) {
        return this.spot.compareToIgnoreCase(other.spot);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
