package com.falalurahman.farmcontroller;


public class DeviceStatus {

    Boolean light;
    Boolean food_motor;
    Boolean water_motor;

    public DeviceStatus() {
    }

    public DeviceStatus(Boolean light, Boolean food_motor, Boolean water_motor) {
        this.light = light;
        this.food_motor = food_motor;
        this.water_motor = water_motor;
    }

    public Boolean getLight() {
        return light;
    }

    public void setLight(Boolean light) {
        this.light = light;
    }

    public Boolean getFood_motor() {
        return food_motor;
    }

    public void setFood_motor(Boolean food_motor) {
        this.food_motor = food_motor;
    }

    public Boolean getWater_motor() {
        return water_motor;
    }

    public void setWater_motor(Boolean water_motor) {
        this.water_motor = water_motor;
    }
}
