package duenow.decoratorfactory;

import duenow.Task;
import duenow.decoratorfactory.tasktype.Homework;
import duenow.decoratorfactory.tasktype.LongTest;
import duenow.decoratorfactory.tasktype.Orals;
import duenow.decoratorfactory.tasktype.OtherSchool;
import duenow.decoratorfactory.tasktype.Paper;
import duenow.decoratorfactory.tasktype.Project;
import duenow.decoratorfactory.tasktype.Quiz;
import duenow.decoratorfactory.tasktype.Reading;

/**
 * Created by elysi on 4/1/2016.
 */
public class TaskSimpleFactory {

    public Task createTask(String type, int tN){ //tN is in minutes
        Task t = null;
        if(type.equals("Homework")){
            t = new Homework();
        } else if(type.equals("Long Test")){
            t = new LongTest();
        } else if(type.equals("Orals")){
            t = new Orals();
        } else if(type.equals("Paper")){
            t = new Paper();
        } else if(type.equals("Project")){
            t = new Project();
        } else if(type.equals("Quiz")){
            t = new Quiz();
        } else if(type.equals("Reading")){
            t = new Reading();
        } else{
//            Task other = new OtherSchool(tN);
//            return other.Builder();
            t = new OtherSchool(tN);
        }
        return t;
    }
}
