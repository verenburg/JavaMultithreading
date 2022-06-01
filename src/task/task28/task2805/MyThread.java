package task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread{
    private static volatile AtomicInteger threadPriority = new AtomicInteger(1);

    private synchronized void setPriorityInMyThread() {

        int newPriority  = threadPriority.getAndIncrement();

        if (threadPriority.intValue() > MAX_PRIORITY) {
            threadPriority.set(1);
        }

        if (getThreadGroup() != null && newPriority > getThreadGroup().getMaxPriority()) {
            newPriority = getThreadGroup().getMaxPriority();
        }

        setPriority(newPriority);
    }

    
    public MyThread() {
        super();
        setPriorityInMyThread();
    }

    public MyThread(Runnable target) {
        super(target);
        setPriorityInMyThread();
    }

    public MyThread(ThreadGroup group, Runnable target) { //
        super(group, target);
        setPriorityInMyThread();
    }

    public MyThread(String name) {
        super(name);
        setPriorityInMyThread();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriorityInMyThread();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriorityInMyThread();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriorityInMyThread();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriorityInMyThread();
    }


}
