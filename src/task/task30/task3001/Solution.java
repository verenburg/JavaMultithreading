package task.task30.task3001;

/*
Конвертер систем счислений
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {

        int base = number.getNumberSystem().getNumberSystemIntValue();
        char[] digits = number.getDigit().toCharArray();
        int summ = 0;
        for (int i = digits.length; i > 0; i--) {
            int digit = Integer.parseInt(String.valueOf(digits[i-1]));

            summ += digit * Math.pow(base,i-1);
        }

        int newOsn = expectedNumberSystem.getNumberSystemIntValue();
        int resultOfDevision = summ / newOsn;
        String remainder = String.valueOf(summ % newOsn);
        String newDigit = remainder + "";
        summ = resultOfDevision;
        while (resultOfDevision > newOsn) {
            resultOfDevision = summ / newOsn;

            remainder = String.valueOf(summ % newOsn);

            if (Integer.parseInt(remainder) > 9) {
                switch (remainder) {
                    case "10":
                        remainder = "a";
                        break;
                    case "11":
                        remainder = "b";
                        break;
                    case "12":
                        remainder = "c";
                        break;
                    case "13":
                        remainder = "d";
                        break;
                    case "14":
                        remainder = "e";
                        break;
                    case "15":
                        remainder = "f";
                        break;
                }
            }

            newDigit = remainder + newDigit;

            summ = resultOfDevision;
        }

        Number result = new Number(expectedNumberSystem, newDigit);
        return result;
    }
}
