package Templates.SimpleThreads;

import java.util.ArrayList;

public class SimpleThreadSynchronization extends Thread{

    ArrayList<Integer> arrayList;

    public static void main(String... args) throws InterruptedException {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(); // heap
        SimpleThreadSynchronization thread1 = new SimpleThreadSynchronization(arrayList);
        SimpleThreadSynchronization thread2 = new SimpleThreadSynchronization(arrayList);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(arrayList.toString()); // [] ή [1] ή [1,1]
    }


    public SimpleThreadSynchronization(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void do_stuff() {
        this.arrayList.add(1);
    }

    @Override
    public void run() {
        this.do_stuff();
    }
}
