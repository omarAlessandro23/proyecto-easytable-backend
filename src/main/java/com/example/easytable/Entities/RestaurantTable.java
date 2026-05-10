package com.example.easytable.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="restaurant_tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;

    @Column(name = "location_zone", length = 50)
    private String locationZone;

    public RestaurantTable() {
    }

    public RestaurantTable(int tableId, String locationZone) {
        this.tableId = tableId;
        this.locationZone = locationZone;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getLocationZone() {
        return locationZone;
    }

    public void setLocationZone(String locationZone) {
        this.locationZone = locationZone;
    }
}
