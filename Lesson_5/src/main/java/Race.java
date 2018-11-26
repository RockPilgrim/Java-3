import java.util.concurrent.CountDownLatch;

public class Race {

    public static void main(String[] args) {
// задаем количество потоков
        final int THREADS_COUNT = 2;
// задаем значение счетчика
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        System.out.println("Начинаем");
        for (int i = 1; i < THREADS_COUNT+5 ; i++) {
            final int w = i;
            new Thread(() -> {
                try {
// считаем, что выполнение задачи занимает ~1 сек
                    Thread.sleep(500 + (int) (500 * Math.random()));
                    cdl.countDown();
// как только задача выполнена, уменьшаем счетчик
                    System.out.println("Поток #" + w + " - готов");
                    try {
                        cdl.await();
//                        if (cdl.getCount()==0){
//                            System.out.println("Start "+w);
//                        }
                        cdl.await();
                        System.out.println("Run " + w);
// пока счетчик не приравняется нулю, будем стоять на этой строке
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}


