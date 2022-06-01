package task;

import java.io.*;
import java.util.Arrays;


public class SolutionTest {

    public static int[] generateArr() {
        int[] arr = new int[200];
        for (int i = 0; i < 200; i++) {
            int t = (int) (Math.random() * 1000)+1;
            arr[i] = t;
        }
        return arr;
    }

    public static String arrayToLine(int[] arr) {
        String line = "";
        for (int item : arr) {
            line += item + ",";
        }
        line = line.substring(0, line.length()-1);
        return line;
    }

    public static boolean arraysCompare(int[] arrExample, int[] newArray){
        int countOfSameElements = 0;
        for (int i = 0; i < arrExample.length; i++) {
            if (newArray[i] == arrExample[i]) countOfSameElements++;
        }
        return (arrExample.length == countOfSameElements && arrExample.length == newArray.length);
    }

    public static void main(String[] args) throws IOException {
        ArrayCoder arrayCoder = new ArrayCoder(generateArr());
        int[] arr = arrayCoder.getArr();
        Arrays.sort(arr);
        String line = arrayToLine(arr);
        System.out.println(line);
        System.out.println("Количество символов в исходной строке: " + line.length());

        System.out.println("Преобразуем массив в строку :");
        String string = arrayCoder.arrayArchivingToString();
        System.out.println(string);
        System.out.println("Длина строки после преобразования: " + string.length());

        System.out.println("Коэффициент сжатия: " + (double)string.length()/(double)line.length());

        System.out.println("Восстанавливаем массив: ");
        int[] newArr = ArrayDecoder.decodeStringIntoArray(string);
        Arrays.sort(newArr);
        System.out.println(arrayToLine(newArr));

        System.out.print("Сравниваем два полученных массива: ");
        System.out.println(arraysCompare(arr,newArr));
    }
}
