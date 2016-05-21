package duenow.decoratorfactory;


import duenow.decoratorfactory.tasktype.Dinner;
import duenow.decoratorfactory.tasktype.OtherPersonal;
import duenow.decoratorfactory.tasktype.PersonalTask;
import duenow.decoratorfactory.tasktype.SchoolTask;

/**
 * Created by IanDeLaCruz on 20/05/2016.
 */
public class PersonalTaskFactory extends AbstractTaskFactory {

    @Override
    public SchoolTask createSchoolTask(String type, int tn) {
        return null;
    }

    @Override
    public PersonalTask createPersonalTask(String type, int tn) {
        PersonalTask pt;
        switch (type){
            case "Dinner":
                pt = new Dinner();
                break;
            default:
                pt = new OtherPersonal(tn);
                break;
        }
        return pt;
    }
}
