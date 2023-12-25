package org.example;

/**
 *Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. Класс должен иметь методы
 * getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * также переопределение метода toString(), возвращающее строковое представление пары.
 */
public class Main {
    public static void main(String[] args) {
        Pair pair = new Pair<>("Name", 2);
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);

    }
}