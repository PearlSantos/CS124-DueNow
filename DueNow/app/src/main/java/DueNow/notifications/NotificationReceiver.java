package duenow.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import duenow.Task;
import duenow.main.MainActivity;
import duenow.state.State;

/**
 * Created by elysi on 5/14/2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

    @Override
    public void onReceive(Context context, Intent intent) {
     //   Task t = (Task) intent.getSerializableExtra("TASK");
        Firebase.setAndroidContext(context);

        Task t = new Task();
        t.setName("CS124 Project");
        Calendar deadline3 = new GregorianCalendar();
        deadline3.set(2016, 4, 20, 13, 30);
        t.setDeadline(deadline3);

        State state = t.getState();
        int id = intent.getIntExtra("ID", 0);
        String action = intent.getAction();

        System.out.println("CHECK: NOTIFICATION RECEIVER" + action);

        if(NotificationMaker.START.equals(action)){
            state.startTask();
            System.out.println("CHECK: START");
        }else if(NotificationMaker.FIN.equals(action)){
            state.finishTask();

            System.out.println("CHECK: Fin");
        } else if(NotificationMaker.POST.equals(action)){
            state.postponeTask();

            System.out.println("CHECK: Post");
        }


        NotificationManager n = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        n.cancel(id);

        Intent goToMain = new Intent(context, MainActivity.class);
        goToMain.setAction(action);
        goToMain.putExtra("MESSAGE", state.getMessage());

        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, goToMain, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
