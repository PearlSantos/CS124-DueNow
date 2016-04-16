package DueNow;

import java.util.Date;

import DueNow.state.NotStartedState;
import DueNow.state.State;

/**
 * Created by elysi on 3/30/2016.
 */
public class Task {
    protected String name;
    protected String description;
    protected Date deadline;
    protected Date timeStarted;
    protected Date timeFinished;
    protected Date recommendedStartTime;
    protected int timeLeft; // timeLeft is in minutes
    protected int timeInterval = 0; // in minutes, total time user worked on task
    protected int timeNeeded; // timeNeeded is in minutes
    protected State state = new NotStartedState(this);

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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(Date timeStarted) {
        this.timeStarted = timeStarted;
    }

    public Date getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(Date timeFinished) {
        this.timeFinished = timeFinished;
    }

    public Date getRecommendedStartTime() {
        return recommendedStartTime;
    }

    public void setRecommendedStartTime(Date recommendedStartTime) {
        this.recommendedStartTime = recommendedStartTime;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeNeeded(){
        return this.timeNeeded;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
