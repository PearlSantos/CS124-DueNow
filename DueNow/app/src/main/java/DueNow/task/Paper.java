package DueNow.task;

/**
 * Created by elysi on 3/30/2016.
 */
public class Paper extends Task {
    private int timeN;
    public Paper(int tN){
        this.timeN = tN;
    }

    @Override
    public int getTimeNeeded() {
        return timeN;
    }
}
