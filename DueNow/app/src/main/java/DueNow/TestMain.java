package duenow;

import duenow.decoratorfactory.TaskSimpleFactory;
import duenow.decoratorfactory.difficulty.EasyDifficulty;
import duenow.decoratorfactory.difficulty.HardDifficulty;
import duenow.decoratorfactory.difficulty.MedDifficulty;

/**
 * Created by elysi on 4/16/2016.
 */
public class TestMain {
    public static void main(String args[]){
        TaskSimpleFactory f = new TaskSimpleFactory();
        Task t = f.createTask("Homework", 10);
        System.out.println(t.getTimeNeeded());
        t = new EasyDifficulty(t);
        System.out.println(t.getTimeNeeded());
        t = new MedDifficulty(t);
        System.out.println(t.getTimeNeeded());
        t = new HardDifficulty(t);
        System.out.println(t.getTimeNeeded());
    }
}
