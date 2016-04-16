package DueNow.state;

import DueNow.Task;

/**
 * Created by elysi on 4/16/2016.
 */
public abstract class State {
    protected Task t;
    protected String message;
    abstract void startTask();
    abstract void postponeTask();
    abstract void finishTask();
    public String getMessage(){
        return message;
    }
}
