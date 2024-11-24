package telran.multithreading;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private int distance;
    private int minSleep;
    private int maxSleep;
    private LocalDateTime startTime;
    private LinkedList<Racer> finishList = new LinkedList<>();
    AtomicInteger winner = new AtomicInteger(-1);

    public Race(int distance, int minSleep, int maxSleep) {
        this.distance = distance;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime date) {
        startTime = date;
    }

    public void addToFinishList(Racer racer) {
        finishList.add(racer);
    }

    public LinkedList<Racer> getFinishList() {
        return finishList;
    }

    public int getWinner() {
		return winner.get();
	}

    public int getDistance() {
        return distance;
    }

    public int getMinSleep() {
        return minSleep;
    }

    public int getMaxSleep() {
        return maxSleep;
    }

}