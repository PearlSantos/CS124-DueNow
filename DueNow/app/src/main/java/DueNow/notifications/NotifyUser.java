package duenow.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import duenow.decoratorfactory.R;
import duenow.main.MainActivity;

/**
 * Created by elysi on 5/5/2016.
 */
public class NotifyUser extends AppCompatActivity {
    final static String START = "START";
    final static String FIN = "FINISH";
    final static String POST = "POSTPONED";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teststuff);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    public void createNotification() {
        System.out.println("CHECK: CREATE NOTIF");

        Intent start = new Intent();
        start.setAction(START);
        Bundle startBundle = new Bundle();
        startBundle.putString("time", "format time");
        start.putExtras(startBundle);
        PendingIntent pendingIntentStart = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), start, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action.Builder s = new NotificationCompat.Action.Builder(android.R.drawable.ic_dialog_email, "Start", pendingIntentStart);
        NotificationCompat.Builder n = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(android.R.drawable.ic_menu_mapmode)
                .setAutoCancel(true)
                .addAction(s.build())
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis());

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)System.currentTimeMillis(), n.build());

    }
}
