package com.alperen.w_01.models.base;

/**
 * Created by Alperen on 1.08.2022.
 */
abstract public class Vehicle {
    protected static class Engine {
        private int horsePower;
        private int engineCount;

        public Engine(int horsePower, int engineCount) {
            this.horsePower = horsePower;
            this.engineCount = engineCount;
        }

        public Engine() {

        }

        public int getHorsePower() {
            return horsePower;
        }

        public void setHorsePower(int horsePower) {
            this.horsePower = horsePower;
        }

        public int getEngineCount() {
            return engineCount;
        }

        public void setEngineCount(int engineCount) {
            this.engineCount = engineCount;
        }
    }

    protected static class Wheel {
        private int tirePressure;
        private int wheelCount;

        public Wheel(int tirePressure, int wheelCount) {
            this.tirePressure = tirePressure;
            this.wheelCount = wheelCount;
        }

        public Wheel() {

        }

        public int getTirePressure() {
            return tirePressure;
        }

        public void setTirePressure(int tirePressure) {
            this.tirePressure = tirePressure;
        }

        public int getWheelCount() {
            return wheelCount;
        }

        public void setWheelCount(int wheelCount) {
            this.wheelCount = wheelCount;
        }
    }

    protected static class Door {
        private int doorCount;

        public Door(int doorCount) {
            this.doorCount = doorCount;
        }

        public Door() {

        }

        public int getDoorCount() {
            return doorCount;
        }

        public void setDoorCount(int doorCount) {
            this.doorCount = doorCount;
        }
    }

    private Engine engine;
    private Wheel wheel;
    private Door door;

    public Vehicle(int horsePower, int engineCount, int tirePressure, int wheelCount, int doorCount) {
        this.engine = new Engine(horsePower, engineCount);
        this.wheel = new Wheel(tirePressure, wheelCount);
        this.door = new Door(doorCount);
    }

    public Vehicle() {

    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}


