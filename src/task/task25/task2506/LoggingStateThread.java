package task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread supervisingThread;
    LoggingStateThread(Thread thread){
        supervisingThread = thread;
        supervisingThread.setDaemon(true);
        
    }
    @Override
    public void run() {

        State currentState = supervisingThread.getState();
        System.out.println(currentState);
        State newState;
        do {
            newState = supervisingThread.getState();
            if (!currentState.equals(newState)) {
                currentState = newState;
                System.out.println(currentState);
            }
        } while (!currentState.equals(State.TERMINATED));
    }
}
