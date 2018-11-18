/**
 * Java-3 Homework 4
 *
 * Write to console "ABC" five times. Used threads.
 *
 * @author Tokarev Timofei
 * @version 18.11.18
 */
public class ThreadsLesson {

    public static final String wordA = "A";
    public static final String wordB = "B";
    public static final String wordC = "C";
    private Object monitor = new Object();
    private String word = "A";

    public static void main(String[] args) {
        ThreadsLesson lesson = new ThreadsLesson();

    }
    public ThreadsLesson() {
        firstSample();

    }
    private void firstSample() {
        new Thread(new Runnable() {
            public void run() {
                sayA();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                sayB();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                sayC();
            }
        }).start();
    }
    private void sayA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!word.equals(wordA)) {
                        monitor.wait();
                    }
                    Thread.sleep(300);
                    System.out.print(word);
                    word = wordB;
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void sayB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!word.equals(wordB)) {
                        monitor.wait();
                    }
                    Thread.sleep(300);
                    System.out.print(word);
                    word = wordC;
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void sayC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!word.equals(wordC)) {
                        monitor.wait();
                    }
                    Thread.sleep(300);
                    System.out.print(word+" ");
                    word = wordA;
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
