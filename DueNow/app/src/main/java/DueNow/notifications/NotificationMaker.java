package duenow.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import duenow.Task;

/**
 * Created by elysi on 5/5/2016.
 */
public class NotificationMaker extends BroadcastReceiver {
    private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");
    final static String START = "STARTED";
    final static String FIN = "FINISHED";
    final static String POST = "POSTPONED";
    public void onReceive(Context context, Intent intent) {
     //   Task t = (Task) intent.getSerializableExtra("TASK");
        System.out.println("CHECK: NOTIFICATION MAKER");
        Task t = new Task();
        t.setName("CS124 Project");
        Calendar deadline3 = new GregorianCalendar();
        deadline3.set(2016, 4, 20, 13, 30);
        t.setDeadline(deadline3);

        int id =  intent.getIntExtra("ID", 0);
        String deadlineString = f.format(t.getDeadline().getTime());

        Intent i = new Intent(context, NotificationReceiver.class);
        i.putExtra("ID", id);
      //  i.putExtra("TASK", t);



        i.setAction(START);

        i.setAction(FIN);

        i.setAction(POST);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action.Builder s = new NotificationCompat.Action.Builder(android.R.drawable.ic_dialog_email, "Start", pendingIntent);
        NotificationCompat.Action.Builder f = new NotificationCompat.Action.Builder(android.R.drawable.ic_btn_speak_now, "Finish", pendingIntent);
        NotificationCompat.Action.Builder p = new NotificationCompat.Action.Builder(android.R.drawable.ic_dialog_dialer, "Postpone", pendingIntent);
        NotificationCompat.Builder n = new NotificationCompat.Builder(context)
                .setContentTitle("Due Now")
                .setContentText(t.getName() + "\n")
                .setContentInfo("Deadline: " + deadlineString)
                .setSmallIcon(android.R.drawable.ic_menu_mapmode)
                .setAutoCancel(true)
                .addAction(s.build())
                .addAction(f.build())
                .addAction(p.build())
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);
        n.build().flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, n.build());

    }
}
