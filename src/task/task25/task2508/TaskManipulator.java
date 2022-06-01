package task.task25.task2508;

public class TaskManipulator implements CustomThreadManipulator, Runnable {
    private Thread thread;

    @Override
    public void run() {
            
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }

    }

    @Override
    public void start(String threadName) {
        this.thread = new Thread(this, threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
