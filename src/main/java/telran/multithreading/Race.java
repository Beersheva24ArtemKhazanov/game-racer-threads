package telran.multithreading;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Race {
    private int distance;
    private ArrayList<Racer> racers;
    private static final int MIN_SLEEP_TIMEOUT = 10;
    private static final int MAX_SLEEP_TIMEOUT = 100;
    private static int maxTimeOut;
    private static int minTimeOut;
    protected AtomicInteger winner;

    public Race(int distance) {
        this.distance = distance;
        maxTimeOut = MAX_SLEEP_TIMEOUT;
        minTimeOut = MIN_SLEEP_TIMEOUT;
        racers = new ArrayList<>();
        winner = new AtomicInteger();
    }

    public void setTimeOut(int min, int max) {
        Race.minTimeOut = min;
        Race.maxTimeOut = max;
    }

    public int getSleepTimeOut() {
        return new Random().nextInt(minTimeOut, maxTimeOut);
    }

    public int getDistance() {
        return distance;
    }

    public void setUp(int nRacers) {
        IntStream.rangeClosed(1, nRacers).forEach(n -> addRacer(new Racer(this, n)));
    }

    private void addRacer(Racer racer) {
        racers.add(racer);
    }

    public void startRace() {
        startingRacers();
        waitingRacers();
    }

    private void waitingRacers() {
        racers.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });
    }

    private void startingRacers() {
        racers.stream().forEach(Racer::start);
    }

    public void showChampion() {
        System.out.printf("The Winner in the Race is %d", winner.get());
    }
}
