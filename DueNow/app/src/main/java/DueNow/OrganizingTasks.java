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

    public OrganizingTasks() {
        this.list = ListOfTasks.getList();
    }

    public ArrayList<Task> getList(){
        return list;
    }

    public void addTask(Task newTask) {
        ArrayList<Task> newList = new ArrayList<>();
        Calendar currTime = Calendar.getInstance();
        int intervalInHour = getIntervalToDeadline(newTask, currTime);
        Calendar recStartTime;
        int listSize = list.size();
        if (list.isEmpty()) {
            recStartTime = (Calendar) currTime.clone();
            recStartTime.add(Calendar.HOUR, intervalInHour / 2);
            newTask.setRecommendedStartTime(recStartTime);
            newTask.setRecommendedTimeFinish();
            newList.add(newTask);
            System.out.println("REC START TIME: " + dateFormat.format(newTask.getRecommendedStartTime()));
            System.out.println("REC FINISH TIME: " + dateFormat.format(newTask.getRecommendedTimeFinish()));
        } else {
            //  Calendar setRecStartTime = (Calendar) currTime.clone();
            //  setRecStartTime.add(Calendar.HOUR, intervalInHour / 2);
            int indexOfList = 0;
            while (indexOfList < list.size()) {
                Task analyzeNow = list.get(indexOfList);
                if (getIntervalToDeadline(analyzeNow, currTime) > getIntervalToDeadline(newTask, currTime)) {
                    recStartTime = (Calendar) analyzeNow.getRecommendedTimeFinish().clone();
                    recStartTime.add(Calendar.MINUTE, REST_TIME);
                    newTask.setRecommendedStartTime(recStartTime);
                    newTask.setRecommendedTimeFinish();
                    newList.add(newTask);
                    adjustRecTime(newList, indexOfList);
                } else if (getIntervalToDeadline(analyzeNow, currTime) == getIntervalToDeadline(newTask, currTime)) {
                    if (analyzeNow.getPriority() > newTask.getPriority()) {
                        recStartTime = (Calendar) analyzeNow.getRecommendedTimeFinish().clone();
                        recStartTime.add(Calendar.MINUTE, REST_TIME);
                        newTask.setRecommendedStartTime(recStartTime);
                        newTask.setRecommendedTimeFinish();
                        newList.add(newTask);
                        adjustRecTime(newList, indexOfList);
                    } else {
                        newList.add(analyzeNow);
                        recStartTime = (Calendar) analyzeNow.getRecommendedTimeFinish().clone();
                        recStartTime.add(Calendar.MINUTE, REST_TIME);
                        newTask.setRecommendedStartTime(recStartTime);
                        newTask.setRecommendedTimeFinish();
                        newList.add(newTask);
                        indexOfList++;
                        adjustRecTime(newList, indexOfList);
                    }
                    break;
                } else {
                    newList.add(analyzeNow);
                    indexOfList++;
                }
            }
            if(listSize == newList.size()){
                recStartTime = (Calendar) newList.get(newList.size()-1).getRecommendedTimeFinish().clone();
                recStartTime.add(Calendar.MINUTE, REST_TIME);
                newTask.setRecommendedStartTime(recStartTime);
                newTask.setRecommendedTimeFinish();
                newList.add(newTask);
            }
        }


        list = newList;
    }

    public void adjustRecTime(ArrayList<Task> newList, int indexStart) {
        while (indexStart < list.size()) {
            Task prevTask = list.get(indexStart-1);
            Task nowTask = list.get(indexStart);
            Calendar recStartTime = (Calendar) prevTask.getRecommendedTimeFinish().clone();
            recStartTime.add(Calendar.MINUTE, REST_TIME);
            nowTask.setRecommendedStartTime(recStartTime);
            nowTask.setRecommendedTimeFinish();
            newList.add(nowTask);
            indexStart++;
        }
    }

    public int getIntervalToDeadline(Task t, Calendar currTime) {
        long milli = t.getDeadline().getTime().getTime() - currTime.getTime().getTime();
        int hour = (int) milli / (60 * 60 * 1000) % 24;
        return hour;
    }

    public boolean checkDeadline(Task t) {
        int hasTime = t.getDeadline().getTime().compareTo(t.getRecommendedStartTime().getTime());
        if (hasTime > 0) return true;
        return false;
    }

    public void delete(int index){
        list.remove(index);
    }

    public void delete(Task task){
        list.remove(task);
    }

    public void postpone(int index){
        Task postponed = list.get(index);
        list.remove(index);
        addTask(postponed);
    }

    public void postpone(Task task){
        Task postponed = task;
        list.remove(postponed);
        addTask(postponed);
    }
}
