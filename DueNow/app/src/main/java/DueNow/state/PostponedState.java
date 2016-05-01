package DueNow.state;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DueNow.Task;

/**
 * Created by elysi on 4/16/2016.
 */
public class PostponedState extends State {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

    public PostponedState(Task task){
        t = task;
    }

    @Override
    public void startTask() {
        message = "Starting task: ";
        Calendar start = Calendar.getInstance();
        message = "Originally Started: " + f.format(t.getTimeStarted()) + "\nTime Started: " + f.format(start);
        t.setTimeStarted(start);
        t.setState(new StartedState(t));

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
