package duenow.notifications;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import duenow.Task;
import duenow.decoratorfactory.R;
import duenow.state.State;

/**
 * Created by elysi on 5/14/2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

    @Override
    public void onReceive(Context context, Intent intent) {
     //   Task t = (Task) intent.getSerializableExtra("TASK");
        Task t = new Task();
        t.setName("CS124 Project");
        Calendar deadline3 = new GregorianCalendar();
        deadline3.set(2016, 4, 20, 13, 30);
        t.setDeadline(deadline3);

        System.out.println("CHECK: NOTIFICATION RECEIVER");

        State state = t.getState();
        int id = intent.getIntExtra("ID", 0);
        String action = intent.getAction();

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

        AlertDialog inform = new AlertDialog.Builder(context).create();
        inform.setMessage(state.getMessage());
        inform.setTitle("Task has been " + action);
        inform.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        inform.show();

        NotificationManager n = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        n.cancel(id);
    }
}
