
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

/** Module class for a spot.*/
public class Spot implements Comparable<Spot> {

    private String name, type, surface, imageId;
    private Integer distance, directionId, status;
    private Double lat, lon;

    // Constructor.
    public Spot(String name, String type, String surface, Integer distance, String imageId, Integer directionId, Integer status, Double lat, Double lon) {
        this.name = name;
        this.type = type;
        this.surface = surface;
        this.distance = distance;
        this.imageId = imageId;
        this.directionId = directionId;
        this.lat = lat;
        this.lon = lon;
        this.status = status;
    }

        /** This method defines a way to sort the list of spots alphabetically based on the spot name.
        * Source: https://www.youtube.com/watch?v=hncd_WgF83c*/
        @Override
        public int compareTo(Spot other) {
            return this.name.compareToIgnoreCase(other.name);
        }

    // Getters & Setters
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
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
