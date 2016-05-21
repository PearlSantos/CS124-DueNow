package duenow;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

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
        public Builder context(Context context){
            t.setC(context);
            return this;
        }

        public Builder name(String n){
            t.setName(n);
            return this;
        }

        public Builder description(String d){
            t.setDescription(d);
            return this;
        }

        public Builder deadline(Calendar cal){
            t.setDeadline(cal);
            return this;
        }

        public Builder priority(int p){
            t.setPriority(p);
            return this;
        }

        public TaskBuilder build(){
            return new TaskBuilder(this);
        }

    }

    public Task createTask(){
        return this.t;
    }

    public void setNotification() {
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
