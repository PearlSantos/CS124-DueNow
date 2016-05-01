package duenow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import duenow.decoratorfactory.TaskSimpleFactory;
import duenow.decoratorfactory.difficulty.EasyDifficulty;
import duenow.decoratorfactory.difficulty.HardDifficulty;
import duenow.decoratorfactory.difficulty.MedDifficulty;

/**
 * Created by elysi on 4/16/2016.
 */
public class TestMain {
    public static void main(String args[]){

        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, EEE, hh:mm a");
        TaskSimpleFactory f = new TaskSimpleFactory();
        Task t = f.createTask("Homework", 10);
        Calendar deadline = new GregorianCalendar();
        deadline.set(2016, 4, 1, 15, 30);
        t.setName("Homework in Th131");
        t.setDescription("MUST do to get an A+++");
        t.setDeadline(deadline);
        t.setPriority(3);
        OrganizingTasks o = new OrganizingTasks();
        o.addTask(t);

        Task t2 = f.createTask("Quiz", 10);
        Calendar deadline2 = new GregorianCalendar();
        deadline2.set(2016, 4, 3, 13, 30);
        t2.setName("Quiz in CS124");
        t2.setDescription("MUST do to get an A+++");
        t2.setDeadline(deadline2);
        t2.setPriority(3);
        o.addTask(t2);

        Task t3 = f.createTask("Orals", 10);
        Calendar deadline3 = new GregorianCalendar();
        deadline3.set(2016, 4, 2, 13, 30);
        t3.setName("Philo orals");
        t3.setDescription("MUST do to get an A+++");
        t3.setDeadline(deadline3);
        t3.setPriority(2);
        o.addTask(t3);

        Task t4 = f.createTask("LongTest", 10);
        Calendar deadline4 = new GregorianCalendar();
        deadline4.set(2016, 4, 1, 20, 30);
        t4.setName("Long Test in CS162");
        t4.setDescription("MUST do to get an A+++");
        t4.setDeadline(deadline4);
        t4.setPriority(1);
        o.addTask(t4);

        ArrayList<Task> list = o.getList();
        for(Task h: list){
            System.out.println("Name: " + h.getName());
            System.out.println("Deadline: " + sdf.format(h.getDeadline().getTime()));
            System.out.println("Recommended Start Time " + sdf.format(h.getRecommendedStartTime().getTime()));
            System.out.println("Recommended Finish Time " + sdf.format(h.getRecommendedTimeFinish().getTime()));
        }
//
//        t = new EasyDifficulty(t);
//        System.out.println(t.getTimeNeeded());
//        t = new MedDifficulty(t);
//        System.out.println(t.getTimeNeeded());
//        t = new HardDifficulty(t);
//        System.out.println(t.getTimeNeeded());

    }
}
