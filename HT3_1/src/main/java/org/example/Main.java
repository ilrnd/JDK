package org.example;

import java.util.Date;

/**
 * Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 *
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
 * и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа
 * по парно. (используйте instanceof)
 *
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа. Класс должен иметь методы
 * getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 *
 * Работу сдать в виде ссылки на гит репозиторий
 */

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Integer num1 = 10;
        Float num2 = 20f;
        System.out.println(MyCalc.sum(num1, num2));
        System.out.println(MyCalc.subtract(num1, num2));
        System.out.println(MyCalc.multiply(num1, num2));
        System.out.println(MyCalc.divide(num1, num2));
    }
}