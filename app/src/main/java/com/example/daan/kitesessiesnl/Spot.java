package com.example.daan.kitesessiesnl;

import java.util.ArrayList;
import java.util.List;

public class Spot {

    String name, type, surface;
    Integer distance, imageId, directionId;
    Double lat, lon;

    public Spot(String name, String type, String surface, Integer distance, Integer imageId, Integer directionId, Double lat, Double lon) {
        this.name = name;
        this.type = type;
        this.surface = surface;
        this.distance = distance;
        this.imageId = imageId;
        this.directionId = directionId;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
