package task.task30.task3010;

import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {

        String line = null;

        try {
            line = args[0];
            Pattern pattern = Pattern.compile("[^0-9A-Za-z]");
            if (pattern.matcher(line).find()) {
                System.out.println("incorrect");
            } else {
                line = line.toUpperCase();
                char[] chars = line.toCharArray();
                char max = 0;
                for (char c : chars) {
                    if (c > max) {
                        max = c;
                    }
                }

                int radix;
                if (max <= '9') {
                    radix = max - 47;
                } else {
                    radix = max - 54;
                }
                if (radix < 2) {
                    radix = 2;
                }
                System.out.println(radix);
            }
        } catch (Exception e) {
        }


/*
        boolean isNumber = false;
        for (int i = 2; i <= 36; i++) {
            if (isNumber(Integer.parseInt(line,i))) {
                System.out.println(i);
                isNumber = true;
                break;
            }
        }
        if (isNumber == false) {
            System.out.println("incorrect");
        }*/
    }
}