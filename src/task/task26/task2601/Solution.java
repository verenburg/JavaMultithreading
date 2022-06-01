package task.task26.task2601;

/*
Почитать в инете про медиану выборки
*/

import java.util.*;
import java.util.List;

public class Solution {
    public static double mediana = 0;

    public static void main(String[] args) {
        Integer[] array = {1,1,1,1,2,35,58,75,6,7};
        Integer[] a = sort(array);
        for (Integer el : a) {
            //System.out.print(el + " ");
        }

    }



    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);

        int medIndex = 0;

        if (array.length % 2 == 0) {
            medIndex = array.length / 2;
            mediana =(double)(array[medIndex] + array[medIndex - 1])/(double) 2;
        } else {
            medIndex = array.length / 2;
            mediana = array[medIndex];
        }
        final double MED = mediana;

        List<Integer> delta = new ArrayList<>();
        for (Integer el : array) {
            delta.add(el);
        }

        Comparator<Integer> deltaSort = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (int) (Math.abs(o1 - MED) - Math.abs(o2 - MED));
            }
        };

        Collections.sort(delta,deltaSort);
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = delta.get(i);
        }
        return result;
    }
}
