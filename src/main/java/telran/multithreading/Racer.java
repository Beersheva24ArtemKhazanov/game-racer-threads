package telran.multithreading;

import java.util.Random;

public class Racer extends Thread {
    private Race race;
    private int number;
    private int position = 0;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    public void run() {
        while (position < race.getDistance()) {
            stepForwardAndWait();
            race.winner.set(number);
            System.out.printf("Now first is number %d \n", number);
        }
    }

    private void stepForwardAndWait() {
        position++;
        try {
            sleep(new Random().nextInt(race.getSleepTimeOut()));
        } catch (InterruptedException e) {
        }
    }
}
