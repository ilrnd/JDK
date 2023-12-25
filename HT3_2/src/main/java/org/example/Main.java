package org.example;

import java.io.File;

/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые,
 * и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа
 * по парно. (используйте instanceof)
 *
 */
public class Main {
    public static void main(String[] args) {
        Object [] array1 = {"String1", 2, new File("file1.txt")};
        Object [] array2 = {"String2", 1, new File("file2.txt")};
        Object [] array3 = {5, "String3", new File("file3.txt")};
        System.out.println(ArrayCompare.compareArrays(array1, array2));
        System.out.println(ArrayCompare.compareArrays(array1, array3));


    }
}