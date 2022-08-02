package com.alperen.w_01.models.cars;

import com.alperen.w_01.models.base.Car;

/**
 * Created by Alperen on 1.08.2022.
 */
public class BMW extends Car{

    public BMW(String carName, int horsePower, int tirePressure) {
        super(carName, horsePower, 2, tirePressure, 6, 2);
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
