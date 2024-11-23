package telran.multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private int distance;
    private int minSleep;
    private int maxSleep;
    private LocalDateTime startTime;
    AtomicInteger winner = new AtomicInteger(-1);

    public Race(int distance, int minSleep, int maxSleep) {
        this.distance = distance;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public synchronized void setStartTime(LocalDateTime date) {
        startTime = date;
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