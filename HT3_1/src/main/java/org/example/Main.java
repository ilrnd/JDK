package org.example;

import java.util.Date;

/**
 * Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
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