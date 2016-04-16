package DueNow.decoratorfactory;

import DueNow.Task;
import DueNow.decoratorfactory.tasktype.Homework;
import DueNow.decoratorfactory.tasktype.LongTest;
import DueNow.decoratorfactory.tasktype.Orals;
import DueNow.decoratorfactory.tasktype.Other;
import DueNow.decoratorfactory.tasktype.Paper;
import DueNow.decoratorfactory.tasktype.Project;
import DueNow.decoratorfactory.tasktype.Quiz;
import DueNow.decoratorfactory.tasktype.Reading;

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
