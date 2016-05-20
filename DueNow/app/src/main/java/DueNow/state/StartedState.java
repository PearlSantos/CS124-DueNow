package duenow.state;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import duenow.ListOfTasks;
import duenow.OrganizingTasks;
import duenow.Task;

/**
 * Created by elysi on 4/16/2016.
 */
public class StartedState extends State {

    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");
    public StartedState(Task task){
        this.name = "StartedState";
        t = task;
    }

    @Override
    public void startTask() {
        message = "You've already started!";
    }

    @Override
    public void postponeTask() {
        message = "Postponed until: ";
        //recompute rec start time
        OrganizingTasks organizing = new OrganizingTasks();
        organizing.postpone(t);
        message += f.format(t.getRecommendedStartTime().getTime());

        // decrease time needed
        Calendar currTime = Calendar.getInstance();
        long milli = currTime.getTime().getTime() - t.getTimeStarted().getTime().getTime();
        int mins = (int) (milli / (1000)) / 60;
        t.setTimeNeeded(t.getTimeNeeded() - mins);

        // add to time interval
        t.setTimeInterval(t.getTimeInterval() + mins);

        t.setState(new PostponedState(t));

        ListOfTasks.updateFirebase(t);

    }

    @Override
    public void finishTask() {
        message = "You're finished? Awesome!";

        //get timeInterval between time start
        Calendar currTime = Calendar.getInstance();
        long milli = currTime.getTime().getTime() - t.getTimeStarted().getTime().getTime();
        int mins = (int) (milli / (1000)) / 60;
        t.setTimeNeeded(0);
        //transfer task to finished tasks

        // set timeInterval
        t.setTimeInterval(t.getTimeInterval() + mins);

        t.setState(new FinishedState(t));

        ListOfTasks.updateFirebase(t);
    }
}
