package duenow.decoratorfactory.tasktype;

import duenow.Task;

/**
 * Created by elysi on 4/1/2016.
 */
public class Quiz extends SchoolTask {
    public Quiz(){
        this.type = "Quiz";
        this.timeNeeded = 45;
    }
}
