package DueNow.state;

import DueNow.Task;

/**
 * Created by elysi on 4/16/2016.
 */
public class StartedState extends State {

    public StartedState(Task task){
        t = task;
    }

    @Override
    public void startTask() {
        message = "You've already started!";
    }

    @Override
    public void postponeTask() {
        message = "Postponed until: ";
        //recompute recommended start time
        // decrease timeNeeded
        // add to timeInterval
        t.setState(new PostponedState(t));

    }

    @Override
    public void finishTask() {
        message = "You're finished? Awesome!";

        //get timeInterval between time start
        //transfer task to finished tasks
        // set timeInterval

        t.setState(new FinishedState());
    }
}
