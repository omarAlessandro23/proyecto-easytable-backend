package com.example.easytable.Entities;

import jakarta.persistence.*;
@Entity
@Table(name = "Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(name = "rating_avg", nullable = false)
    private Double ratingAvg;
    @Column(name = "web_url", length = 100)
    private String webUrl;
    @Column(name = "google_maps_url", length = 100)
    private String googleMapsUrl;
    @Column(name="latitude",nullable=false)
    private Double latitude;
    @Column(name="longitude",nullable=false)
    private Double longitude;

    public Restaurant(int id, String name, String address, Double ratingAvg, String webUrl, String googleMapsUrl, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.ratingAvg = ratingAvg;
        this.webUrl = webUrl;
        this.googleMapsUrl = googleMapsUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(Double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getGoogleMapsUrl() {
        return googleMapsUrl;
    }

    public void setGoogleMapsUrl(String googleMapsUrl) {
        this.googleMapsUrl = googleMapsUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}