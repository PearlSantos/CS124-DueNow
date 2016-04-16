package DueNow.decoratorfactory;

import DueNow.Task;

/**
 * Created by elysi on 3/30/2016.
 */
public abstract class TaskDecorator extends Task {
    protected Task task;
    public abstract int getTimeNeeded();
}
