package duenow.decoratorfactory.difficulty;

import duenow.Task;
import duenow.decoratorfactory.TaskDecorator;

/**
 * Created by elysi on 3/30/2016.
 */
public class EasyDifficulty extends TaskDecorator {

    public EasyDifficulty(Task t){
        this.task = t;
    }
    @Override
    public int getTimeNeeded() {
        int addTime = task.getTimeNeeded() - (int) (task.getTimeNeeded()*0.10);
        return addTime;
    }
}
