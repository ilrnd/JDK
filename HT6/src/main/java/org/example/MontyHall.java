package org.example;

import java.util.HashMap;
import java.util.Random;

/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>
 * Вывести на экран статистику по победам и поражениям
 */

public class MontyHall {
    private static final Random random = new Random();
    private static final int ROUNDS = 1000;
    private static final int NUMBER_OF_DOORS = 3;

    private static HashMap<Integer, Boolean> result = new HashMap();


    public static void main(String[] args) {

        int wins = 0;
        for (int i = 0; i < ROUNDS; i++) {
            int prize = random.nextInt(NUMBER_OF_DOORS);
            int choice1 = random.nextInt(NUMBER_OF_DOORS);
            int gameHost = choosingTheDoor(prize, choice1);
            if(choice1 == prize){
                result.put(i, true);
            }
            int choice2 = choosingTheDoor(choice1, gameHost);
            if (choice2 == prize){
                result.put(i, true);
                wins ++;
            } else {
                result.put(i, false);
            }
            System.out.printf("Попытка: %d, результат: %b\n", i, result.get(i));
        }
        System.out.println("Количество побед при смене выбора: " + wins);
    }
    private static int choosingTheDoor(int door1, int door2){
        int result;
        do {
            result = random.nextInt(NUMBER_OF_DOORS);
        }
        while (result == door1 || result == door2);
        return result;
    }
}