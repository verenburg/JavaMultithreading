package task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }
    
    public void run() {
            try {
                int i = 1;
                while (true) {
                    String str = String.format("Some text for " + i);
                    map.put(i + "", str);
                    i++;
                    System.out.println(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
            }

    }
}
