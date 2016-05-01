package duenow.state;

/**
 * Created by elysi on 4/16/2016.
 */
public class FinishedState extends State {
    @Override
    public void startTask() {
        message = "You're already finished!";
    }

    @Override
    public void postponeTask() {
        message = "You're already finished!";
    }

    @Override
    public void finishTask() {
        message = "You're already finished!";
    }
}
