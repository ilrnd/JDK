package org.example;

public class ArrayCompare{
    public static <T> boolean compareArrays(T [] array1, T [] array2){
        if(array1.length != array2.length){
            return false;
        } else {
            for (int i = 0; i < array1.length; i++) {
                if (!array1[i].getClass().getName().equals(array2[i].getClass().getName())){
                    return false;
                };
            }
        }
        return true;
    }
}
