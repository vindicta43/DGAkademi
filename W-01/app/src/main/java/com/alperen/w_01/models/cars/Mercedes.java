package com.alperen.w_01.models.cars;

import com.alperen.w_01.models.base.Car;

/**
 * Created by Alperen on 1.08.2022.
 */
public class Mercedes extends Car {
    public Mercedes(String carName, int horsePower, int tirePressure) {
        super(carName, horsePower, 2, tirePressure, 8, 2);
    }

    @Override
    public void openDoor() {

    }

    @Override
    public void closeDoor() {

    }

    @Override
    public void startEngine() {

    }

    @Override
    public void stopEngine() {

    }
}
