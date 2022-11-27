package Templates.RaceCondition;
import java.util.ArrayList;

public class Sync2 {
    private static int NUMBER_OF_THREADS = 2000;
    private int count = 0;
    private void increaseCount() {
        // this simulates work that may be happening
        // before the critical section
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // only block the critical section
        synchronized(this) {
            this.count++;
        }
    }

    protected int getCount() {
        return this.count;
    }

    public static void main(String [] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        // generate a unique instance
        Sync2 sync2 = new Sync2();
        // generate threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            // each thread simply executes the increaseCount function
            Thread incrThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    sync2.increaseCount();
                }
            });
            // start the thread and add it into the thread list
            incrThread.start();
            threads.add(incrThread);
        }
        // wait for all of the threads to finish
        while (!threads.isEmpty()) {
            threads.get(0).join();
            threads.remove(0);
        }
        System.out.println("CNT:" + Integer.toString(sync2.getCount()) + "/" + Integer.toString(NUMBER_OF_THREADS));
    }
}

