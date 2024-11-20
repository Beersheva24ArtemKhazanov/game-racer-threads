package telran.multithreading;

import telran.view.*;

public class Main {
    public static void main(String[] args) {
      InputOutput io = new StandardInputOutput();
      int nRacers = io.readDouble("Enter amount of Racers: ", "Wrong amount of Racres").intValue();
      int distance = io.readDouble("Enter distance of Race: ", "Wrong distance of Race").intValue();
      Race race = new Race(distance);
      race.setUp(nRacers);
      race.startRace();
      race.showChampion();
    }
}