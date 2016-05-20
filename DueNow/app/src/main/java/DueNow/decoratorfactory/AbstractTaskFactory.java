package duenow.decoratorfactory;


import duenow.decoratorfactory.tasktype.PersonalTask;
import duenow.decoratorfactory.tasktype.SchoolTask;

/**
 * Created by IanDeLaCruz on 20/05/2016.
 */
public abstract class AbstractTaskFactory {
    public abstract SchoolTask createSchoolTask(String type, int tn);
    public abstract PersonalTask createPersonalTask(String type, int tn);
}
