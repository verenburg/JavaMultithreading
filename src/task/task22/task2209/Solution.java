package task.task22.task2209;

import java.io.*;
import java.util.*;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fn = reader.readLine();
        reader.close();

        BufferedReader fr = new BufferedReader(new FileReader(fn)); //"E:\\Test\\shifr.txt"
        String fContent = "";
        while (fr.ready()) {
            fContent += fr.readLine();
        }
        fr.close();

        String[] line = fContent.split(" ");
        StringBuilder result = getLine(line);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words2) {


        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        while (sb.toString().split(" ").length < words2.length) {

            // В этой части мы передвигаем массив по кругу для того чтобы выбрать первое слово чтобы цепочка собралась
            String s = words2[0];
            for (int j = 0; j < words2.length - 1; j++) {
                words2[j] = words2[j + 1];
            }
            words2[words2.length -1] = s;


            // передаем полученный массив для обработки
            String[] words = new String[words2.length];
            for (int i = 0; i < words2.length; i++){
                words[i] = words2[i];
            }

            // алгоритм составления цепочки
            if (words.length == 0 || words == null) return sb;

            sb = new StringBuilder(words[0]);
            for (int i = 0; i < words.length; i++) {
                String simbol = sb.substring(sb.length() - 1).toUpperCase();
                String smolSimbol = sb.substring(sb.length() - 1);
                for (int j = 1; j < words.length; j++) {
                    boolean f = words[j].startsWith(simbol);
                    boolean f2 = words[j].startsWith(smolSimbol);
                    if (f || f2) {
                        sb.append(" ");
                        sb.append(words[j]);
                        words[j] = "";
                        break;
                    }
                }
            }

            // определяю слова которые не вошли в SB
            ArrayList<String> list = new ArrayList<>();
            for (String word : words2){
                list.add(word);
            }
            for (String str : sb.toString().split(" ")) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).equals(str)) {
                        list.remove(j);
                    }
                }
            }
            // добавляем слова не вошедшие в набор в sb

            for (String line : list) {
                String s1 = line.substring(0,1) + " " + line.substring(line.length()-1);
                int ind = sb.indexOf(s1);
                if (ind > 0) {
                    sb.insert(ind + 1, " " + line);
                } else {
                    s1 = line.substring(0,1) + " " + line.substring(0,1);
                    ind  = sb.indexOf(s1);
                    sb2.append(sb.substring(ind + 2));
                    sb2.append(" ");
                    sb2.append(sb.substring(0,ind+1));
                    sb2.append(" " + line);
                    sb = sb2;
                    sb2 = null;
                }
            }
        }

        return sb;
    }
}
