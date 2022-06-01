package task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    final static int MAX_TRUCK_SPEED = 80;
    final static int MAX_SEDAN_SPEED = 120;
    final static int MAX_CABRIOLET_SPEED = 90;

    private int numberOfPassengers;

    double fuel;
    private int type;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private boolean driverAvailable;


    protected Car(int type, int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        this.type = type;
    }

    public static Car create(int type, int numberOfPassengers){
        Car car = null;
        switch (type) {
            case TRUCK:
                car = new Truck(numberOfPassengers);
                break;
            case SEDAN:
                car = new Sedan(numberOfPassengers);
                break;
            case CABRIOLET:
                car = new Cabriolet(numberOfPassengers);
                break;
        }
        return car;
    }


    public boolean isSummer(Date date, Date summerStart, Date summerEnd){
        return (summerStart.getTime() <= date.getTime() && date.getTime() <= summerEnd.getTime());
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getWinterConsumption(int length) {
        return length*winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length) {
        return length*summerFuelConsumption;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        if (isSummer(date, SummerStart, SummerEnd)) {
            return getSummerConsumption(length);
        } else {
            return getWinterConsumption(length);
        }
    }
    private boolean canPassengersBeTransferred() {
        return (isDriverAvailable() && fuel > 0);
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (!canPassengersBeTransferred())
            return 0;
        return numberOfPassengers;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        fastenDriverBelt();
        if (numberOfPassengers > 0)
            fastenPassengersBelts();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();
}