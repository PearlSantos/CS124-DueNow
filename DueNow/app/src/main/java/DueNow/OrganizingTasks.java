package DueNow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by elysi on 4/20/2016.
 */
public class OrganizingTasks {
    private ArrayList<Task> list;
    private final int REST_TIME = 30; //mins
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public OrganizingTasks(ArrayList<Task> list){
        this.list = list;
    }

    public ArrayList<Task> addTask(Task newTask){
        ArrayList<Task> newList = new ArrayList<>();
        Calendar currTime = Calendar.getInstance();
        int intervalInHour = getIntervalToDeadline(newTask, currTime);
        if(list.isEmpty()){
            Calendar recStartTime = (Calendar) currTime.clone();
            recStartTime.add(Calendar.HOUR, intervalInHour/2);
            newTask.setRecommendedStartTime(recStartTime);
            newTask.setRecommendedTimeFinish(REST_TIME);
            newList.add(newTask);
            System.out.println("REC START TIME: " + dateFormat.format(newTask.getRecommendedStartTime()));
            System.out.println("REC FINISH TIME: " +  dateFormat.format(newTask.getRecommendedTimeFinish()));
        } else{
            Calendar setRecStartTime = (Calendar) currTime.clone();
            setRecStartTime.add(Calendar.HOUR, intervalInHour / 2);
            int indexOfList = 0;
            for(int i = 0; i <list.size(); i++){
                if(true){

                } else{
                    newList.add(indexOfList, list.get(i));
                    indexOfList++;
                }
            }
        }




        return newList;
    }

    public int getIntervalToDeadline(Task t, Calendar currTime){
        long milli = t.deadline.getTime().getTime() - currTime.getTime().getTime();
        int hour = (int) milli / (60 * 60 * 1000) % 24;
        return hour;
    }

    public boolean checkDeadline(Task t){
        int hasTime  = t.deadline.getTime().compareTo(t.getRecommendedStartTime().getTime());
        if(hasTime > 0) return true;
        return false;
    }

}
