package duenow.notifications;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import duenow.Task;
import duenow.decoratorfactory.R;

/**
 * Created by elysi on 5/5/2016.
 */
public class NotifyUser extends AppCompatActivity {
   private final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

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
        int id =  (int) System.currentTimeMillis();

        Calendar time = Calendar.getInstance();
        time.add(Calendar.SECOND, 5);

        Task testT = new Task();
        testT.setName("CS124 Project");
        Calendar deadline3 = new GregorianCalendar();
        deadline3.set(2016, 4, 20, 13, 30);
        testT.setDeadline(deadline3);

        Intent intent = new Intent(getBaseContext(), NotificationMaker.class);
        intent.putExtra("ID", id);
   //     intent.putExtra("TASK", testT);

        PendingIntent pe = PendingIntent.getBroadcast(getBaseContext(), id, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pe);
    }
}
