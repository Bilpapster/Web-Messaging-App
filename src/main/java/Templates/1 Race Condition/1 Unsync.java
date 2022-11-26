package race_condition;
import java.util.ArrayList;

public class Unsync {
    private static int NOF_THREADS = 2000;
    private int cnt = 0;

    private void increaseCount() {
        // this simulates work that may be happening 
        // before the critical section
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.cnt++;
    }

    protected int getCount() {
        return this.cnt;
    }

    public static void main(String [] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        // generate a unique instance
        Unsync s = new Unsync(); 
        // generate threads
        for (int i = 0; i < NOF_THREADS; i++) {
            // each thread simply executes the increaseCount function
            Thread incrThread = new Thread(new Runnable() {			
                @Override
                public void run() {
                    s.increaseCount();
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
        System.out.println("CNT:" + Integer.toString(s.getCount()) + "/" + Integer.toString(NOF_THREADS));
    }
}
