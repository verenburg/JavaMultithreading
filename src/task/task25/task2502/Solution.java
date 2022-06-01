package task.task25.task2502;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Машину на СТО не повезем!
*/

public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            Set<Wheel> wheelsOfCar = new HashSet<>();
            if (loadWheelNamesFromDB().length == 4) {
                for (String wheelName : loadWheelNamesFromDB()) {
                    for (Wheel wheel : Wheel.values()) {
                        if (wheelName.equals(wheel.name())) {
                            wheelsOfCar.add(wheel);
                        }
                    }
                }
                wheels = new ArrayList<>(wheelsOfCar);
            } else {
                throw new IllegalArgumentException();
            }
            if (wheels.size() != 4) {
                throw new IllegalArgumentException();
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        List<Wheel> wheels = car.wheels;
        for (Wheel wheel : wheels) {
            System.out.println(wheel);
        }
    }
}
