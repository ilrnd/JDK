package org.example;

public class MyCalc {
    public static <T extends Number> double sum(T num1, T num2){
        return num1.doubleValue() + num2.doubleValue();
    }
    public static <T extends Number> double subtract(T num1, T num2){
        return num1.doubleValue() - num2.doubleValue();
    }

    public static <T extends Number> double multiply(T num1, T num2){
        return num1.doubleValue() * num2.doubleValue();
    }

    public static <T extends Number> double divide(T num1, T num2){
        return num1.doubleValue() / num2.doubleValue();
    }
}
