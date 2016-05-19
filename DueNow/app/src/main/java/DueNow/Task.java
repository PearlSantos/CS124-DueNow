package duenow;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.firebase.client.Firebase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import duenow.notifications.NotificationMaker;
import duenow.state.NotStartedState;
import duenow.state.State;

/**
 * Created by elysi on 3/30/2016.
 */
public class Task implements Observer {
    public final String uniqueId = Integer.toString((int) System.currentTimeMillis());
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

    protected final Firebase ref = new Firebase("https://cs124duenow.firebaseio.com/tasks");
    Map<String, Object> placeholder = new HashMap<String, Object>();
    protected final Firebase specTask = ref.child(uniqueId);

    private void updateFirebase(String key, Object value){
        placeholder.clear();
        placeholder.put(key, value);
        specTask.updateChildren(placeholder);
    }

//    private Task(Builder builder) {
//        this.c = builder.c;
//        this.name = builder.name;
//        this.description = builder.description;
//        this.deadline = builder.deadline;
//        this.priority = builder.priority;
//    }
//
//    public Task() {
//    }
//
//    public static class Builder{
//        protected Context c;
//        protected String name; // given by user
//        protected String description; //given by user
//        protected Calendar deadline; //given by user
//        protected int priority; // given by user
//
//        public Builder context(Context context){
//            this.c = context;
//            return this;
//        }
//
//        public Builder name(String n){
//            this.name = n;
//            return this;
//        }
//
//        public Builder description(String d){
//            this.description = d;
//            return this;
//        }
//
//        public Builder deadline(Calendar cal){
//            this.deadline = cal;
//            return this;
//        }
//
//        public Builder priority(int p){
//            this.priority = p;
//            return this;
//        }
//
//        public Task build(){
//            return new Task(this);
//        }
//    }

    public void setC(Context c) {
        updateFirebase("c", c);
        this.c = c;
    }

    public String getName() {
        updateFirebase("name", name);
        return name;
    }

    public void setName(String name) {
        updateFirebase("name", name);
        this.name = name;
    }

    public String getDescription() {
        updateFirebase("description", description);
        return description;
    }

    public void setDescription(String description) {
        updateFirebase("name", description);
        this.description = description;
    }

    public Calendar getDeadline() {
        updateFirebase("deadline", deadline);
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        updateFirebase("deadline", deadline);
        this.deadline = deadline;
    }

    public Calendar getTimeStarted() {
        updateFirebase("timeStarted", timeStarted);
        return timeStarted;
    }

    public void setTimeStarted(Calendar timeStarted) {
        updateFirebase("timeStarted", timeStarted);
        this.timeStarted = timeStarted;
    }

    public Calendar getTimeFinished() {
        updateFirebase("timeFinished", timeFinished);
        return timeFinished;
    }

    public void setTimeFinished(Calendar timeFinished) {
        updateFirebase("timeFinished", timeFinished);
        this.timeFinished = timeFinished;
    }

    public Calendar getRecommendedStartTime() {
        updateFirebase("recommendedStartTime", recommendedStartTime);
        return recommendedStartTime;
    }

    public void setRecommendedStartTime(Calendar recommendedStartTime) {
        updateFirebase("recommendedStartTime", recommendedStartTime);
        this.recommendedStartTime = recommendedStartTime;
    }

    public Calendar getRecommendedTimeFinish() {
        updateFirebase("recommendedTimeFinish", recommendedTimeFinish);
        return recommendedTimeFinish;
    }

    public void setRecommendedTimeFinish() {
        Calendar cal = (Calendar) recommendedStartTime.clone();
        cal.add(Calendar.MINUTE, timeNeeded);
        this.recommendedTimeFinish = cal;
        updateFirebase("recommendedTimeFinish", recommendedTimeFinish);
    }

    public int getTimeInterval() {
        updateFirebase("timeInterval", timeInterval);
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        updateFirebase("timeInterval", timeInterval);
        this.timeInterval = timeInterval;
    }

    public int getTimeNeeded(){
        updateFirebase("timeNeeded", timeNeeded);
        return this.timeNeeded;
    }

    public void setTimeNeeded(int timeNeeded){
        updateFirebase("timeNeeded", timeNeeded);
        this.timeNeeded = timeNeeded;}

    public State getState() {
        updateFirebase("state", getState());
        return state;
    }

    public void setState(State state) {
        updateFirebase("state", state);
        this.state = state;
    }

    public int getPriority() {
        updateFirebase("priority", priority);
        return priority;
    }

    public void setPriority(int priority) {
        updateFirebase("priority", priority);
        this.priority = priority;
    }

    @Override
    public void update(Observable observable, Object data) {
        Intent intent = new Intent(c, NotificationMaker.class);
       // intent.putExtra("TASK", testT);
        PendingIntent pe = PendingIntent.getBroadcast(c, 123, intent, 0);
        AlarmManager alarmManager = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, recommendedStartTime.getTimeInMillis(), pe);
    }
}
