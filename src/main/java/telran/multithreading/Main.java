package telran.multithreading;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import telran.view.*;

public class Main {
  private static final int MAX_THREADS = 10;
  private static final int MIN_THREADS = 2;
  private static final int MIN_DISTANCE = 100;
  private static final int MAX_DISTANCE = 3500;
  private static final int MIN_SLEEP = 2;
  private static final int MAX_SLEEP = 5;
  private static LocalDateTime startTime;

  public static void main(String[] args) {

    InputOutput io = new StandardInputOutput();
    Item[] items = getItems();
    Menu menu = new Menu("Race Game", items);
    menu.perform(io);

  }

  private static Item[] getItems() {
    Item[] res = {
        Item.of("Start new game", Main::startGame),
        Item.ofExit()
    };
    return res;
  }

  static void startGame(InputOutput io) {
    int nThreads = io.readNumberRange("Enter number of the racers", "Wrong number of the racers",
        MIN_THREADS, MAX_THREADS).intValue();
    int distance = io.readNumberRange("Enter distance",
        "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE).intValue();
    Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
    Racer[] racers = new Racer[nThreads];
    startRacers(racers, race);
    joinRacers(racers);
    printTableOfResults(racers);
  }

  private static void printTableOfResults(Racer[] racers) {
    Arrays.sort(racers, Comparator.comparing(Racer::getFinishTime));
    System.out.println("----------------------------");
    System.out.println("|  #  | Number | Race time |");
    System.out.println("----------------------------");
    IntStream.range(0, racers.length)
        .forEach(i -> System.out.printf("| %3d | %6d | %6d ms |\n",
            i + 1, racers[i].getNumber(),
            ChronoUnit.MILLIS.between(startTime, racers[i].getFinishTime())));
    System.out.println("----------------------------");
  }

  private static void joinRacers(Racer[] racers) {
    for (int i = 0; i < racers.length; i++) {
      try {
        racers[i].join();
      } catch (InterruptedException e) {

      }
    }

  }

  private static void startRacers(Racer[] racers, Race race) {
    startTime = LocalDateTime.now();
    for (int i = 0; i < racers.length; i++) {
      racers[i] = new Racer(race, i + 1);
      racers[i].start();
    }

  }

}