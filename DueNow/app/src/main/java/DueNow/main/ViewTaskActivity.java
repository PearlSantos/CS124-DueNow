package duenow.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import duenow.decoratorfactory.R;

public class ViewTaskActivity extends AppCompatActivity {
    TextView name, description, deadline, start, priority, difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        final SharedPreferences taskInfo = getSharedPreferences("TASKS", Context.MODE_PRIVATE);

        name = (TextView) findViewById(R.id.taskTitle);
        description = (TextView) findViewById(R.id.task_description);
        deadline = (TextView) findViewById(R.id.deadline);
        start = (TextView) findViewById(R.id.recommendedTime);
        priority = (TextView) findViewById(R.id.priority_click);
        //difficulty = (TextView) findViewById(R.id.difficulty_level);

//        name.setText(taskInfo.getString("task name", null));
//        description.setText(taskInfo.getString("task desc", null));
//        deadline.setText(taskInfo.getString("task deadline", null));
//        start.setText(taskInfo.getString("recommended start time", null));
//        priority.setText(taskInfo.getString("task priority", null));
        //difficulty.setText(taskInfo.getString("task name", null));

    }

    public void endActivity(View v){

       ViewTaskActivity.this.finish();

    }
}
