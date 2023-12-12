package edu.hw10.task_1.example;

import edu.hw10.task_1.annotations.Max;
import edu.hw10.task_1.annotations.NotNull;

public class CarClass implements Rogable {
    @NotNull
    private String carModel;

    @Max(190)
    private int maxSpeed;

    public CarClass(String carModel, int maxSpeed) {
        this.carModel = carModel;
        this.maxSpeed = maxSpeed;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "CarClass{"
                + "carModel='" + carModel + '\''
                + ", maxSpeed=" + maxSpeed
                + '}';
    }
}
