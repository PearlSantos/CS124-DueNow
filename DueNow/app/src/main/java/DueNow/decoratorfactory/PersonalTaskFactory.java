package DueNow.decoratorfactory;

import DueNow.decoratorfactory.tasktype.PersonalTask;
import DueNow.decoratorfactory.tasktype.SchoolTask;

/**
 * Created by IanDeLaCruz on 20/05/2016.
 */
public class PersonalTaskFactory extends AbstractTaskFactory {

    @Override
    SchoolTask createSchoolTask(String type, int tn) {
        return null;
    }

    @Override
    PersonalTask createPersonalTask(String type, int tn) {
        PersonalTask pt;
        switch (type){
            case "Dinner":
                pt = new Dinner();
                break;
            default:
                pt = new Other(tn);
                break;
        }
    }
}
