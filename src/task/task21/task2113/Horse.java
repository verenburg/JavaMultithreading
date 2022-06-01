package task.task21.task2113;

public class Horse {


    private String name;
    private double speed;
    private double distance;

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Horse(String name, double speed, double distance) {
        setName(name);
        setSpeed(speed);
        setDistance(distance);
    }

    public void move(){
        distance += speed*Math.random();
    }

    public void print(){
        int points = (int) Math.floor(distance);
        for (int i = 0; i < points; i++){
            System.out.print(".");
        }
        System.out.print(name);
        System.out.println();
    }
}
