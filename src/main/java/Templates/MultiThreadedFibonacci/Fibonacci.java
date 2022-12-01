package Templates.MultiThreadedFibonacci;

import java.util.LinkedList;
import java.util.Queue;

public class Fibonacci {
    Queue<Integer> sequence = new LinkedList<>();

    public Fibonacci() {
        // the first terms of the sequence
        // 1, 1, 2, 3, 5, 8, 13, ...
        sequence.add(1);
        sequence.add(1);
    }

    public int getNext() {
        // get previous element (and remove from queue)
        int previous = sequence.remove();
        // get latest number sent
        int current = sequence.element();
        // generate next fibonacci number
        int newNumber = previous + current;
        sequence.add(newNumber);
        return previous;
    }
}
