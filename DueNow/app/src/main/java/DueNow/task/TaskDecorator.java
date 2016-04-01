package DueNow.task;

/**
 * Created by elysi on 3/30/2016.
 */
public abstract class TaskDecorator extends Task{
    protected Task task;
    protected int timeNeeded;
    public abstract int getTimeNeeded();
}
