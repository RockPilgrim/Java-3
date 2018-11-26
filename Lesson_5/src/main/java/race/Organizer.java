package race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Organizer {


    private Road road = Road.getInstance;

    public static void main(String[] args) {
        Organizer organizer = new Organizer();
        organizer.establish();
    }

    public void establish() {
        road.readyToStart = new CountDownLatch(Road.carsCount);
        road.tunnel = new Semaphore(road.carsInTunnel);
        for (int i = 1; i < Road.carsCount + 1; i++) {
            new Thread(new Car(i)).start();
        }
//        startRace();
        if (road.carsPlace.equals(3)) {
            System.out.println("Race complete!");
        }
    }

//    public synchronized void startRace() {
//        try {
//            road.readyToStart.await();
//            System.out.println("!Race start!");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
