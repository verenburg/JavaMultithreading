package task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    public static Hippodrome game = null;

    private List<Horse> horses = new ArrayList<>();

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move(){
        for (Horse horse : horses){
            horse.move();
        }
    }

    public void print(){
        for (Horse horse : horses){
            horse.print();
        }
        for(int i = 0; i < 10; i++){
            System.out.println();
        }
    }

    public Horse getWinner(){
        Horse winner = null;
        double distance = 0.0;
        for (Horse horse : horses){
            if (horse.getDistance() > distance) {
                distance = horse.getDistance();
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException {
        Horse horse1 = new Horse("Цезарь", 3.0, 0.0);
        Horse horse2 = new Horse("Орел", 3.0, 0.0);
        Horse horse3 = new Horse("Актавиан", 3.0, 0.0);

        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        Hippodrome.game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
