package org.example;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Philosopher implements Runnable{
    private final int TIME_TO_EAT = 500;
    private final int MIN_TIME_TO_THINK = 500;
    private int eatCounter = 0;
    private int id;
    private final Fork leftFork;
    private final Fork rightFork;


    public Philosopher(int id, final Fork leftFork, final Fork rightFork){
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void think(){
        System.out.printf("Филосов %d думает о жизни\n", id);
        try {
            sleep(MIN_TIME_TO_THINK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  void eat(){
        System.out.printf("Филосов %d дорвался до еды, поел %d раз(а)\n", id, eatCounter + 1);
        eatCounter++;
        try {
            sleep(TIME_TO_EAT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (eatCounter < 3){
            try {
                Table.reentrantLock.lock();
                leftFork.take();
                rightFork.take();
                eat();
                rightFork.put();
                leftFork.put();
            } finally {
                Table.reentrantLock.unlock();
            }
        think();
        }
    }
}
