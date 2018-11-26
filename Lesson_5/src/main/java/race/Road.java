package race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Road {

    public static final Road getInstance = new Road();
    protected int carsInTunnel = 2;
    protected AtomicInteger carsPlace=new AtomicInteger(0);
    protected final static int carsCount = 4;
    protected CountDownLatch readyToStart;
    protected ReentrantLock lock=new ReentrantLock(false);
    protected Semaphore tunnel;

    private Road() {
    }

}
