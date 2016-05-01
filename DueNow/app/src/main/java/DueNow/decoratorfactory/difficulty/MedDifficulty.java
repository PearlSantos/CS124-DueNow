package duenow.decoratorfactory.difficulty;

import duenow.Task;
import duenow.decoratorfactory.TaskDecorator;

/**
 * Created by elysi on 4/1/2016.
 */
public class MedDifficulty extends TaskDecorator {

    public MedDifficulty(Task t){
        this.task = t;
    }
    @Override
    public int getTimeNeeded() {
        int addTime = task.getTimeNeeded() + (int) (task.getTimeNeeded()*0.30);
        return addTime;
    }
}
