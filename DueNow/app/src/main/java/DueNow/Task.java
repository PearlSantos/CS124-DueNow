package duenow;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import duenow.notifications.NotificationMaker;
import duenow.state.NotStartedState;
import duenow.state.State;

/**
 * Created by elysi on 3/30/2016.
 */
public class Task {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

    public final String uniqueId = Integer.toString((int) System.currentTimeMillis());

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    protected String type;
    protected String difficulty;
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
    protected String state = "NotStartedState";


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

    public void setTimeNeeded(int timeNeeded){
        this.timeNeeded = timeNeeded;}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
