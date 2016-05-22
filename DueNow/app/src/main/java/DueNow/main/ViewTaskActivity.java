package duenow.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import duenow.ListOfTasks;
import duenow.Task;
import duenow.decoratorfactory.R;

public class ViewTaskActivity extends AppCompatActivity {
    TextView name, description, deadline, start, priority, difficulty, taskType;

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
        difficulty = (TextView) findViewById(R.id.difficulty_level);
        taskType = (TextView) findViewById(R.id.task_type);


        System.out.println("CHECK: UNIQUE" + taskInfo.getString("unique", ""));
        ListOfTasks l = new ListOfTasks();
        Task t = l.getTask(taskInfo.getString("unique", ""));
        System.out.println("CHECK: UNIQUE TASK" + t.getName());


        name.setText(t.getName());
        description.setText(t.getDescription());
        final SimpleDateFormat f = new SimpleDateFormat("MMMMM d, yyyy, H:mm");
        deadline.setText(f.format(t.getDeadline().getTime()));
        start.setText(f.format(t.getRecommendedStartTime().getTime()));
        System.out.println("CHECK: UNIQUE PR " + t.getPriority());
        t.setPriority(t.getPriority());
        priority.setText(Integer.toString(t.getPriority()));
        difficulty.setText(t.getDifficulty());
        taskType.setText(t.getType());
        System.out.println("CHECK: UNIQUE DIFF " + t.getDifficulty());


    }

    public void endActivity(View v) {

        ViewTaskActivity.this.finish();

    }
}
