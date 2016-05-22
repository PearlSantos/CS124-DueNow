package duenow.decoratorfactory.tasktype;

import duenow.Task;

/**
 * Created by elysi on 4/1/2016.
 */
public class OtherPersonal extends PersonalTask {
    public OtherPersonal(int tN) {
        this.type = "Other Personal";
        this.timeNeeded = tN;
    }
}