package task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static String getQuery(Map<String, String> params) {
        if (params == null || params.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> pair : params.entrySet()){
            if (pair.getValue() != null) {
                sb.append(pair.getKey() + " = " + "'" + pair.getValue() + "'" + " and ");
            }
            if (sb.length() < 5) return "";
        }
        sb.delete(sb.length() - 5, sb.length());
        return sb.toString();
    }
}
