package task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution {
    public static void main(String[] args) {
        //getTokens("level22.lesson13.task01", ".");
    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        List<String> lines = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            lines.add(tokenizer.nextToken());
        }
        //System.out.println(lines);
        return lines.toArray(new String[0]);

    }
}
