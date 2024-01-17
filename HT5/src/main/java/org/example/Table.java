package org.example;

import java.util.concurrent.locks.ReentrantLock;

 public class Table {
    private static final int PHILOSOPHERS_QUANTITY = 5;
    private final Philosopher[] philosophers;
    private final Fork[] forks;
    public static final ReentrantLock reentrantLock = new ReentrantLock();

    public Table() {
        philosophers = new Philosopher[PHILOSOPHERS_QUANTITY];
        forks = new Fork[PHILOSOPHERS_QUANTITY];
        for (int i = 0; i < PHILOSOPHERS_QUANTITY; i++) {
            forks[i] = new Fork();
        }
            for (int i = 0; i < PHILOSOPHERS_QUANTITY; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % PHILOSOPHERS_QUANTITY]);
            new Thread(philosophers[i]).start();
        }
    }
}
