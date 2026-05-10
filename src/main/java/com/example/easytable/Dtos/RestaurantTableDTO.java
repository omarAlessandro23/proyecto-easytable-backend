package com.example.easytable.Dtos;

public class RestaurantTableDTO {

    private int tableId;

    private String locationZone;

    public RestaurantTableDTO() {
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
