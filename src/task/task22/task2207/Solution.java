package task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fn = reader.readLine();
        reader.close();


        BufferedReader fileReader = new BufferedReader(new FileReader(fn));
        String fileContent ="";
        while (fileReader.ready()) {
            fileContent += fileReader.readLine() + " ";
        }
        fileReader.close();
/* Решение рабочее но валидатор не принимает ...*/
        String[] words = fileContent.split(" ");
        List<String> list = Arrays.asList(words);
        list.stream().distinct();
        for (String s : list){
            for (String s2 : list){
                StringBuilder sb = new StringBuilder(s2);
                if (s.equals(sb.reverse().toString())) {
                    Pair pair = new Pair();
                    pair.first = s;
                    pair.second = s2;
                    for(Pair pair1 : result) {
                        if (pair1.equals(pair) || pair.toString().equals(pair1.toString())) result.remove(pair1);
                    }
                    result.add(pair);
                }
            }
        }


       for (Pair pair : result) {
            System.out.println(pair);
       }

    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
