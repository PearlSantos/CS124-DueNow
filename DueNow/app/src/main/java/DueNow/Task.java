package duenow;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import duenow.state.NotStartedState;
import duenow.state.State;

/**
 * Created by elysi on 3/30/2016.
 */
public class Task implements Observer {
    protected Context c;
    protected String name; // given by user
    protected String description; //given by user
    protected Calendar deadline; //given by user
    protected int priority; // given by user
    protected Calendar timeStarted; //given by user, later on
    protected Calendar timeFinished;  //given by user, laterOn
    protected Calendar recommendedStartTime; // dynamically created
    protected Calendar recommendedTimeFinish; // dynamically created
    protected int timeInterval = 0; // in minutes, total time user worked on task
    protected int timeNeeded; // timeNeeded is in minutes
    protected State state = new NotStartedState(this);

    // don't forget to wrap in difficulty!
    // don't forget to set context!

    public void setC(Context c) {
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public Calendar getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(Calendar timeStarted) {
        this.timeStarted = timeStarted;
    }

    public Calendar getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(Calendar timeFinished) {
        this.timeFinished = timeFinished;
    }

    public Calendar getRecommendedStartTime() {
        return recommendedStartTime;
    }

    public void setRecommendedStartTime(Calendar recommendedStartTime) {
        this.recommendedStartTime = recommendedStartTime;
    }

    public Calendar getRecommendedTimeFinish() {
        return recommendedTimeFinish;
    }

    public void setRecommendedTimeFinish() {
        Calendar cal = (Calendar) recommendedStartTime.clone();
        cal.add(Calendar.MINUTE, timeNeeded);
        this.recommendedTimeFinish = cal;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getTimeNeeded(){
        return this.timeNeeded;
    }

    public void setTimeNeeded(int timeNeeded){ this.timeNeeded = timeNeeded;}

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void update(Observable observable, Object data) {
        Intent start = new Intent();
        start.setAction("START");
        Bundle startBundle = new Bundle();
        startBundle.putString("time", "format time");
        start.putExtras(startBundle);
        PendingIntent pendingIntentStart = PendingIntent.getBroadcast(c, (int) System.currentTimeMillis(), start, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action.Builder s = new NotificationCompat.Action.Builder(android.R.drawable.ic_dialog_email, "Start", pendingIntentStart);
        NotificationCompat.Builder n = new NotificationCompat.Builder(c)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(android.R.drawable.ic_menu_mapmode)
                .setAutoCancel(true)
                .addAction(s.build())
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setWhen(recommendedStartTime.getTimeInMillis());

        NotificationManager notificationManager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)System.currentTimeMillis(), n.build());
    }
}
