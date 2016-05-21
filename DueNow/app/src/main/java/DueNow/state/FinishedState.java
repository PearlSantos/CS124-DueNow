package duenow.state;

import duenow.Task;

/**
 * Created by elysi on 4/16/2016.
 */
public class FinishedState extends State {
    public FinishedState(Task t){
        this.t = t;
        this.name = "FinishedState";
    }
    @Override
    public void startTask() {
        message = "You're already finished!";
    }

    @Override
    public void postponeTask() {
        message = "You're already finished!";
    }

    @Override
    public void finishTask() {
        message = "You're already finished!";
    }
}
