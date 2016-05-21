package duenow.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import duenow.ListOfTasks;
import duenow.Task;
import duenow.main.MainActivity;
import duenow.state.FinishedState;
import duenow.state.NotStartedState;
import duenow.state.PostponedState;
import duenow.state.StartedState;
import duenow.state.State;

/**
 * Created by elysi on 5/14/2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

    @Override
    public void onReceive(Context context, Intent intent) {
        Firebase.setAndroidContext(context);

        ListOfTasks l = new ListOfTasks();
      //  Task t = l.getTask(intent.getStringExtra("TASKID"));
        final SharedPreferences prefs = context.getSharedPreferences("STORE_TASK", Context.MODE_PRIVATE);
        String taskString = prefs.getString(intent.getStringExtra("TASKID"), "");
        Task t = null;
        try {
            t = new ObjectMapper().readValue(taskString, new TypeReference<Task>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        State currentState = null;
        String stateName = t.getState();

        if(stateName.equals("NotStartedState")){
            currentState = new NotStartedState(t);
        } else if(stateName.equals("StartedState")){
            currentState = new StartedState(t);
        } else if(stateName.equals("PostponedState")){
            currentState = new PostponedState(t);
        } else
            currentState = new FinishedState(t);


        int id = intent.getIntExtra("ID", 0);
        String action = intent.getAction();

        System.out.println("CHECK: NOTIFICATION RECEIVER" + action);

        if(NotificationMaker.START.equals(action)){
            currentState.startTask();
            System.out.println("CHECK: START");
        }else if(NotificationMaker.FIN.equals(action)){
            currentState.finishTask();

            System.out.println("CHECK: Fin");
        } else if(NotificationMaker.POST.equals(action)){
            currentState.postponeTask();

            System.out.println("CHECK: Post");
        }

        if(!stateName.equals(t.getState())) {
            NotificationManager n = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            n.cancel(id);
        }
        l.updateFirebase(t);
        Intent goToMain = new Intent(context, MainActivity.class);
        goToMain.setAction(action);
        goToMain.putExtra("MESSAGE", currentState.getMessage());

        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, goToMain, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
