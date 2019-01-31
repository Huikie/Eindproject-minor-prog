
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

/**Module class for a session.*/
public class Session {

    private String name, kite, time, spot, date, exactDate;

    //Constructor
    public Session(String name, String kite, String time, String spot, String date, String exactDate) {
        this.name = name;
        this.kite = kite;
        this.time = time;
        this.spot = spot;
        this.date = date;
        this.exactDate = exactDate;
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

    public String getExactDate() {
        return exactDate;
    }

    public void setExactDate(String exactDate) {
        this.exactDate = exactDate;
    }
}
