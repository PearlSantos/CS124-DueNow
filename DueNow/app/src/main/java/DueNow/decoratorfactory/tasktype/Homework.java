package duenow.decoratorfactory.tasktype;

import duenow.Task;

/**
 * Created by elysi on 4/1/2016.
 */
public class Homework extends SchoolTask {
    public Homework(){
        this.type = "Homework";
        this.timeNeeded = 60;
    }
}
