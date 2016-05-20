package DueNow.decoratorfactory;

import DueNow.decoratorfactory.tasktype.*;

/**
 * Created by IanDeLaCruz on 20/05/2016.
 */
public class SchoolTaskFactory extends AbstractTaskFactory{
    @Override
    SchoolTask createSchoolTask(String type, int tn) {
        SchoolTask st;
        switch (type) {
            case "Homework":
                st = new Homework();
                break;
            case "Long Test":
                st = new LongTest();
                break;
            case "Orals":
                st = new Orals();
                break;
            case "Paper":
                st = new Paper();
                break;
            case "Project":
                st = new Project();
                break;
            case "Quiz":
                st = new Quiz();
                break;
            case "Reading":
                st = new Reading();
                break;
            default:
                st = new Other(tn);
        }
        return st;
    }

    @Override
    PersonalTask createPersonalTask(String type, int tn) {
        return null;
    }
}
