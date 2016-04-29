package DueNow.state;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DueNow.OrganizingTasks;
import DueNow.Task;

/**
 * Created by elysi on 4/16/2016.
 */
public class NotStartedState extends State {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");
    public NotStartedState(Task task){
        t = task;
    }
    @Override
    public void startTask() {
        Calendar start = Calendar.getInstance();
        message = "Task Started:" + f.format(start);
        // activate start time
        t.setTimeStarted(start);
        t.setState(new StartedState(t));
    }

    @Override
    public void postponeTask() {
        message = "Postponed until: ";
        // recompute rec start time
        OrganizingTasks organizing = new OrganizingTasks();
        organizing.postpone(t);
        message += f.format(t.getRecommendedStartTime());
        t.setState(new PostponedState(t));
    }

    @Override
    public void finishTask() {
        message = "You need to start first!";
    }
}
