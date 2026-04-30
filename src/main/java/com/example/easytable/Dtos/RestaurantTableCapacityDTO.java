package com.example.easytable.Dtos;

public class RestaurantTableCapacityDTO {


        private String name;
        private Long totalCapacity;

        public RestaurantTableCapacityDTO() {}

        public RestaurantTableCapacityDTO(String name, Long totalCapacity) {
            this.name = name;
            this.totalCapacity = totalCapacity;
        }

        public String getName() {
            return name;
        }

        public Long getTotalCapacity() {
            return totalCapacity;
        }

}
