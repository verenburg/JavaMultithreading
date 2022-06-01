package task.task25.task2512;

import java.util.ArrayList;
import java.util.List;


/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        List<String> reasons = new ArrayList<>();
        while (e != null) {
            reasons.add(0,e.toString());
            e = e.getCause();
        }

        for (String cause : reasons) {
            System.out.println(cause);
        }

    }

    public static void main(String[] args) {
    }
}
