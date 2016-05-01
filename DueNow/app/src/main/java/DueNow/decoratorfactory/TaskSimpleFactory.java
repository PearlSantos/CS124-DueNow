package duenow.decoratorfactory;

import duenow.Task;
import duenow.decoratorfactory.tasktype.Homework;
import duenow.decoratorfactory.tasktype.LongTest;
import duenow.decoratorfactory.tasktype.Orals;
import duenow.decoratorfactory.tasktype.Other;
import duenow.decoratorfactory.tasktype.Paper;
import duenow.decoratorfactory.tasktype.Project;
import duenow.decoratorfactory.tasktype.Quiz;
import duenow.decoratorfactory.tasktype.Reading;

/**
 * Created by elysi on 4/1/2016.
 */
public class TaskSimpleFactory {
    public Task createTask(String type, int tN){ //tN is in minutes
        if(type.equals("Homework")){
            return new Homework();
        } else if(type.equals("Long Test")){
            return new LongTest();
        } else if(type.equals("Orals")){
            return new Orals();
        } else if(type.equals("Paper")){
            return new Paper();
        } else if(type.equals("Project")){
            return new Project();
        } else if(type.equals("Quiz")){
            return new Quiz();
        } else if(type.equals("Reading")){
            return new Reading();
        } else{
            return new Other(tN);
        }
    }
}
