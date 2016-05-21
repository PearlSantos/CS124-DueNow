package duenow.state;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import duenow.ListOfTasks;
import duenow.Task;

/**
 * Created by elysi on 4/16/2016.
 */
public class PostponedState extends State {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

    public PostponedState(Task task){
        this.name = "PostponedState";
        t = task;
    }

    @Override
    public void startTask() {
        message = "Starting task: ";
        Calendar start = Calendar.getInstance();
        message = "Originally Started: " + f.format(t.getTimeStarted().getTime()) + "\nTime Started: " + f.format(start.getTime());

        t.setTimeStarted(start);
        t.setState(new StartedState(t));


        ListOfTasks l = new ListOfTasks();
        l.updateFirebase(t);
    }

    @Override
    public void postponeTask() {
        message = "It's already postponed!";
    }

    @Override
    public void finishTask() {
        message = "You need to start first!";
    }
}
