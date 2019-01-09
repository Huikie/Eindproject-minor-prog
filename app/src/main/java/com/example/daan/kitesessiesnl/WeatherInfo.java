package com.example.daan.kitesessiesnl;

public class WeatherInfo {
    private Integer speed, degrees, temperature;

    public WeatherInfo(Integer speed, Integer degrees, Integer temperature) {
        this.speed = speed;
        this.degrees = degrees;
        this.temperature = temperature;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDegrees() {
        return degrees;
    }

    public void setDegrees(Integer degrees) {
        this.degrees = degrees;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

}
