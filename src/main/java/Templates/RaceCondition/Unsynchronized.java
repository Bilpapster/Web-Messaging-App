package Templates.RaceCondition;

import java.util.ArrayList;

public class Unsynchronized {
    private static final int NUMBER_OF_THREADS = 2000;
    private int count = 0;

    private void increaseCount() {
        // this simulates work that may be happening
        // before the critical section
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.count++;
    }

    protected int getCount() {
        return this.count;
    }

    public static void main(String [] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        // generate a unique instance
        Unsynchronized unsynchronized = new Unsynchronized();
        // generate threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            // each thread simply executes the increaseCount function
            Thread incrThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    unsynchronized.increaseCount();
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
        System.out.println("Total count:" + unsynchronized.getCount() + "/" + NUMBER_OF_THREADS);
    }
}

