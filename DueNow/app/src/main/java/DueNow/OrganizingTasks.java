package duenow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Created by elysi on 4/20/2016.
 */
public class OrganizingTasks {
    private ArrayList<Task> list;
    private final int REST_TIME = 30; //mins
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private ListOfTasks lot = new ListOfTasks();
    public OrganizingTasks() {
        this.list = lot.getList();
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
        } else {
            //  Calendar setRecStartTime = (Calendar) currTime.clone();
            //  setRecStartTime.add(Calendar.HOUR, intervalInHour / 2);
            int indexOfList = 0;
            while (indexOfList < list.size()) {
                Task analyzeNow = list.get(indexOfList);
                Task prev = null;
                if(indexOfList > 0){
                    prev = list.get(indexOfList-1);
                }
                if (getIntervalToDeadline(analyzeNow, currTime) > getIntervalToDeadline(newTask, currTime)) {
                    if(prev!= null) {
                        newList.add(setRecStartTime(prev, newTask));
                        //   System.out.println("Interval Prev Task:" + analyzeNow.getName());
                    } else{
                        newList.add(setRecStartTimeFirst(newTask, currTime));
                    }
                    adjustRecTime(newList, indexOfList);
                    break;
                } else if (getIntervalToDeadline(analyzeNow, currTime) == getIntervalToDeadline(newTask, currTime)) {
                    if (analyzeNow.getPriority() > newTask.getPriority()) {
                        if(prev!=null) {
                            newList.add(setRecStartTime(prev, newTask));
                        } else{
                            newList.add(setRecStartTimeFirst(newTask, currTime));
                        }
                       // System.out.println("Priority Prev Task:" + analyzeNow.getName());
                        adjustRecTime(newList, indexOfList);
                    } else {
                        newList.add(analyzeNow);
                        newList.add(setRecStartTime(analyzeNow, newTask));
                     //   System.out.println("Priority No Change Prev Task:" + analyzeNow.getName());
                        indexOfList++;
                        adjustRecTime(newList, indexOfList);
                    }
                    break;
                } else {
                    System.out.println("Continued looping");
                    newList.add(analyzeNow);
                    indexOfList++;
                }
            }
            //if add to last index
            if(listSize == newList.size()){
                System.out.println("ADD to LAST");
                newList.add(setRecStartTime(newList.get(newList.size()-1), newTask));
            }
        }


        list = newList;
        for(Task e: list){
            lot.updateFirebase(e);
        }
        lot.notifyAll();
    }

    public void adjustRecTime(ArrayList<Task> newList, int indexStart) {
        Task newTask = newList.get(newList.size()-1);
        newList.add(setRecStartTime(newTask, list.get(indexStart)));
        indexStart++;
        while (indexStart < list.size()) {
            Task prevTask;
            Task nowTask;
            if(indexStart > 0) {
                prevTask = list.get(indexStart - 1);
                nowTask = list.get(indexStart);
            } else{
                prevTask = newList.get(0);
                nowTask = list.get(0);
            }
          //  System.out.println("Adjusting Prev Task:" + nowTask.getName());
            newList.add(setRecStartTime(prevTask, nowTask));
            indexStart++;
        }
    }

    public Task setRecStartTimeFirst(Task nowTask, Calendar currTime){
        Calendar recStartTime = (Calendar) currTime.clone();
        recStartTime.add(Calendar.MINUTE, REST_TIME);
        nowTask.setRecommendedStartTime(recStartTime);
        nowTask.setRecommendedTimeFinish();
        return nowTask;
    }
    public Task setRecStartTime(Task analyzeNow, Task nowTask){
        Calendar recStartTime = (Calendar) analyzeNow.getRecommendedTimeFinish().clone();
        recStartTime.add(Calendar.MINUTE, REST_TIME);
        nowTask.setRecommendedStartTime(recStartTime);
        nowTask.setRecommendedTimeFinish();
        return nowTask;
    }

    public int getIntervalToDeadline(Task t, Calendar currTime) {
        long milli = t.getDeadline().getTime().getTime() - currTime.getTime().getTime();
      //  long milli = currTime.getTime().getTime() - currTime.getTime().getTime();
        int hour = (int) milli / (60 * 60 * 1000) % 24;
        System.out.println("TASK: " + t.getName() + " HOUR: " + hour);
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
