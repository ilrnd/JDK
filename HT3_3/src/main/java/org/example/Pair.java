package org.example;


public class Pair <T>{
    private final T first, second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
    @Override
    public String toString(){
        return "1. " + first + "\n2. " + second;
    }
}
