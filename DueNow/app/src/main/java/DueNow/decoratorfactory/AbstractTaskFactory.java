package duenow.decoratorfactory;


import duenow.decoratorfactory.tasktype.PersonalTask;
import duenow.decoratorfactory.tasktype.SchoolTask;

/**
 * Created by IanDeLaCruz on 20/05/2016.
 */
public abstract class AbstractTaskFactory {
    abstract SchoolTask createSchoolTask(String type, int tn);
    abstract PersonalTask createPersonalTask(String type, int tn);
}
