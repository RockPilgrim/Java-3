package race;

import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private long speed;
    private long readyTime;
    private String name;
    private Road road;

    public Car(int num) {
        road = Road.getInstance;
        this.name = "Car #" + num;
//        System.out.println(name+" are preparing");
        speed = (long) (1.0 + (Math.random() * 0.5));
        readyTime = (long) (1.0 + (Math.random() * 0.8));
    }

    public void prepare() {
        try {
            System.out.println(name + " prepare");
            Thread.sleep(2000 * readyTime);
            System.out.println(name + " ready");
            road.readyToStart.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void race() {
        try {
            System.out.print(name + " starts. ");
            Thread.sleep(2000 * speed);
            System.out.print("\n"+name + " over tunnel ");
            road.tunnel.acquire();
            System.out.print(" "+name + " in tunnel");
            Thread.sleep(1500 * speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.print("\n"+name + " out tunnel");
            road.tunnel.release();
        }
    }

    private void finish() throws InterruptedException {
        Thread.sleep(3000 * speed);
        System.out.print("\n" + name + " finish race, place: " + (road.carsPlace.incrementAndGet()));
    }

    @Override
    public void run() {
        prepare();
        try {
            road.readyToStart.await();
            race();
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
