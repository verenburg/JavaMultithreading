package task.task30.task3013;

/* 
Битовые операции
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = Integer.MAX_VALUE - 133;
        System.out.println(Integer.toString(number, 2));

        String result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);
    }

    public int resetLowerBits(int number) {
        number = number | number >> 1;
        number = number | number >> 2;
        number = number | number >> 4;
        number = number | number >> 8;
        number = number | number >> 16;// всё 32 битное число левее первой единицы останется ноль правее становится "1"
        return number & ~ number >> 1; // объединяем число из пункта выше и такое же со сдвинутым в право битом
                                       // и замененой всех единиц и нулей на противоположные, тогда напротив первой единицы
                                       // тоже встанет единица а все остальные знаки будут противоположные
                                       // что-то типа (00001111 & 11111000) -> 00001000.
    }
}