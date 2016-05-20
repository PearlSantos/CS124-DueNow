package DueNow.decoratorfactory;

import DueNow.decoratorfactory.tasktype.PersonalTask;
import DueNow.decoratorfactory.tasktype.SchoolTask;

/**
 * Created by IanDeLaCruz on 20/05/2016.
 */
public abstract class AbstractTaskFactory {
    abstract SchoolTask createSchoolTask(String type, int tn);
    abstract PersonalTask createPersonalTask(String type, int tn);
}
