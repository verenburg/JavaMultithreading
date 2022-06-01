package task.task26.task2603;

import java.util.Comparator;

/* 
Убежденному убеждать других не трудно
Сортировка объектов <T> по нескольким параметрам
*/

public class Solution {
    // Для того чтобы эта штука заработала нужно сделать несколько классов наследников интерфеса
    // Comparator<T> в каждом из них описать свой метод compare(T o1, T o2). потом сложить их
    // массив Comparator<T>[] и передать его конструктору CustomizedComparator.
    // и вот полученный объект передать в качестве второго параметра в метод Collections.sort(List<T>,второй параметр)

    public static class CustomizedComparator<T>  implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2) {
            int res = 0;
            for (Comparator<T> comparator : comparators){
                res = comparator.compare(o1, o2);
                if (res != 0) {
                    return res;
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
