package task.task29.task2909.car;

public class Truck extends Car{
    public Truck(int numberOfPassengers) {
        super(TRUCK,numberOfPassengers);
    }

    public int getMaxSpeed() {
        return MAX_TRUCK_SPEED;
    }
}
