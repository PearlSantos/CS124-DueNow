package duenow;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import duenow.decoratorfactory.difficulty.EasyDifficulty;
import duenow.decoratorfactory.difficulty.HardDifficulty;
import duenow.decoratorfactory.difficulty.MedDifficulty;
import duenow.notifications.NotificationMaker;

/**
 * Created by elysi on 5/21/2016.
 */
public class TaskBuilder {
    protected Task t;
    private TaskBuilder(Builder builder) {
        this.t = builder.t;
    }
    public static class Builder{
        protected Task t;
        public Builder(Task t){
            this.t = t;
        }

        public Builder name(String n){
            t.setName(n);
            return this;
        }

        public Builder description(String d){
            t.setDescription(d);
            return this;
        }

        public Builder difficulty(String diff){
            if(diff.equals("Easy")){
                t = new EasyDifficulty(t);
            } else if(diff.equals("Medium")){
                t = new MedDifficulty(t);
            } if(diff.equals("Hard")){
                t = new HardDifficulty(t);
            }

            return this;
        }
        public Builder deadline(String cal){
           // final SimpleDateFormat f = new SimpleDateFormat("MMMMM d, hh:mm a");
            final SimpleDateFormat f = new SimpleDateFormat("MMMMM d, yyyy");
            Date date = null;
            try {
                 date = f.parse(cal);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar deadline = Calendar.getInstance();
            deadline.setTime(date);
            t.setDeadline(deadline);
            return this;
        }

        public Builder priority(String p){
            if(p.contains("1"))
                t.setPriority(1);
            else if(p.contains("2"))
                 t.setPriority(2);
            else t.setPriority(3);
            return this;
        }

        public TaskBuilder build(){
            return new TaskBuilder(this);
        }

    }

    public Task createTask(){
        return this.t;
    }

    public static void setNotification(Context c, Task t) {
        int id =  (int) System.currentTimeMillis();

        Intent intent = new Intent(c, NotificationMaker.class);
        intent.putExtra("ID", id);
        //     intent.putExtra("TASK", testT);

        PendingIntent pe = PendingIntent.getBroadcast(c, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pe);

        alarmManager.set(AlarmManager.RTC_WAKEUP, t.getRecommendedStartTime().getTimeInMillis(), pe);

        System.out.println("CHECK: ALARM MANAGER");
    }

}
