package task.task25.task2514;

/* 
Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
*/

public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            /*Thread.yield();
            Thread.yield();
            Thread.yield(); Работает не предсказуемо особенно на многопроцессорной машине добиться ожидаемого выода не удалось )))*/
            System.out.println("end-" + index);

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new YieldRunnable(i)).start();
        }
    }
}
