package DueNow.task;

import java.util.Date;

/**
 * Created by elysi on 3/30/2016.
 */
public abstract class Task {
    protected String description;
    protected Date deadline;
    protected Date timeStarted;
    protected Date timeFinished;
    protected Date recommendedStartTime;
    protected int timeLeft;
    protected int timeNeeded;

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

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public abstract int getTimeNeeded();

}
