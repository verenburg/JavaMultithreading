package task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber)
/* {
        boolean matcher1 = telNumber.matches("\\d{1}" + "\\(" + "[0-9]{3}" + "\\)" + "[0-9]{6}");
        boolean matcher2 = telNumber.matches("^\\+" + "\\d{2}" + "\\(" + "[0-9]{3}" + "\\)" + "[0-9]{7}");
        boolean matcher3 = telNumber.matches("\\+" + "[0-9]{12}");
        boolean matcher4 = telNumber.matches("\\(" + "[0-9]{3}" + "\\)" + "[0-9]{7}");
        boolean matcher5 = telNumber.matches("\\+" +"\\d*" + "\\(" + "[0-9]{3}" + "\\)" + "\\d*");

        return  matcher1 || matcher2 || matcher3 || matcher4 || matcher5;
    } */
        {
            if (telNumber == null) {
                return false;
            }
            return (telNumber.matches("^\\+(\\d[()]?){12}$") || telNumber.matches("^([()]?\\d){10}$"))
                    && telNumber.matches("^(\\+)?(\\d+)?(\\(\\d{3}\\))?\\d+$");
        }

    public static void main(String[] args) {
        String [] numbers = new String[8];
        numbers = new String[]{"+380501234567",
                "+38(050)1234567",
                "(050)1234567",
                "0(501)234567",
                "+38)050(1234567",
                "+38(050)123-45-67",
                "050ххх4567",
                "050123456",
                "(0)501234567",
                "+7(499)80501234",
                "+237(499)501234"};
        for (String s : numbers) {
            System.out.println(checkTelNumber(s));
        }

    }
}
