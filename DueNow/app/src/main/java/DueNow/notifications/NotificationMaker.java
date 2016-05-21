package duenow.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import duenow.Task;
import duenow.decoratorfactory.R;

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
        Firebase.setAndroidContext(context);

        System.out.println("CHECK: NOTIFICATION MAKER");
        Task t = new Task();
        t.setName("CS124 Project");
        Calendar deadline3 = new GregorianCalendar();
        deadline3.set(2016, 4, 20, 13, 30);
        t.setDeadline(deadline3);

        int id =  intent.getIntExtra("ID", 0);
        String deadlineString = f.format(t.getDeadline().getTime());

        Intent start = new Intent(context, NotificationReceiver.class);
        Intent fin = new Intent(context, NotificationReceiver.class);
        Intent post = new Intent(context, NotificationReceiver.class);

        start.putExtra("ID", id);
        fin.putExtra("ID", id);
        post.putExtra("ID", id);

        start.setAction(START);
        fin.setAction(FIN);
        post.setAction(POST);

        PendingIntent pendingIntentStart = PendingIntent.getBroadcast(context, id, start, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentFin = PendingIntent.getBroadcast(context, id, fin, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentPost = PendingIntent.getBroadcast(context, id, post, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action.Builder s = new NotificationCompat.Action.Builder(R.mipmap.ic_assignment_white_24dp, "Start", pendingIntentStart);
        NotificationCompat.Action.Builder f = new NotificationCompat.Action.Builder(R.mipmap.ic_done_white_24dp, "Finish", pendingIntentFin);
        NotificationCompat.Action.Builder p = new NotificationCompat.Action.Builder(R.mipmap.ic_pan_tool_white_24dp, "Postpone", pendingIntentPost);
        NotificationCompat.Builder n = new NotificationCompat.Builder(context)
                .setContentTitle("Due Now")
                .setContentText(t.getName() + "\n")
                .setContentInfo("Deadline: " + deadlineString)
                .setSmallIcon(R.drawable.logocircle)
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
