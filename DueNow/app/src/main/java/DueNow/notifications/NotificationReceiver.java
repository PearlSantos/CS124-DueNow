package duenow.notifications;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import duenow.decoratorfactory.R;

/**
 * Created by elysi on 5/5/2016.
 */
public class NotificationReceiver extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy);
    }
}
