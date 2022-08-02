package com.alperen.w_01.models.base;

/**
 * Created by Alperen on 1.08.2022.
 */
abstract public class Car implements CarActions{
    public class Engine {
        private int horsePower;
        private int engineCount;

        private Engine(int horsePower, int engineCount) {
            this.horsePower = horsePower;
            this.engineCount = engineCount;
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

    public class Wheel {
        private int tirePressure;
        private int wheelCount;

        private Wheel(int tirePressure, int wheelCount) {
            this.tirePressure = tirePressure;
            this.wheelCount = wheelCount;
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

    public class Door {
        private int doorCount;

        private Door(int doorCount) {
            this.doorCount = doorCount;
        }

        public int getDoorCount() {
            return doorCount;
        }

        public void setDoorCount(int doorCount) {
            this.doorCount = doorCount;
        }
    }

    private String carName;
    private Engine engine;
    private Wheel wheel;
    private Door door;

    public Car(String carName, int horsePower, int engineCount, int tirePressure, int wheelCount, int doorCount) {
        this.carName = carName;
        this.engine = new Engine(horsePower, engineCount);
        this.wheel = new Wheel(tirePressure, wheelCount);
        this.door = new Door(doorCount);
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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


