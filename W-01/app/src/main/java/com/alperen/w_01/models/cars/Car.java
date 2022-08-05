package com.alperen.w_01.models.cars;

import com.alperen.w_01.models.base.CarEnum;
import com.alperen.w_01.models.base.Vehicle;

/**
 * Created by Alperen on 1.08.2022.
 */
public class Car extends Vehicle {
    private String carName;
    private CarEnum carBrand;

    public Car(int horsePower, int engineCount, int tirePressure, int wheelCount, int doorCount, String carName, CarEnum carBrand) {
        super(horsePower, engineCount, tirePressure, wheelCount, doorCount);
        this.carName = carName;
        this.carBrand = carBrand;
    }

    public Car() {
        super();
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public CarEnum getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarEnum carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public String toString() {
        return "Car name: " + this.carName + "\n" +
                "Car brand: " + this.carBrand + "\n" +
                "Horse power: " + this.getEngine().getHorsePower() + "\n" +
                "Engine count: " + this.getEngine().getEngineCount() + "\n" +
                "Tire pressure: " + this.getWheel().getTirePressure() + "\n" +
                "Wheel count: " + this.getWheel().getWheelCount() + "\n" +
                "Door count: " + this.getDoor().getDoorCount() + "\n\n";
    }
}
