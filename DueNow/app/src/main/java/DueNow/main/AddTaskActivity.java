package duenow.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import duenow.ListOfTasks;
import duenow.OrganizingTasks;
import duenow.Task;
import duenow.TaskBuilder;
import duenow.decoratorfactory.AbstractTaskFactory;
import duenow.decoratorfactory.FactoryProducer;
import duenow.decoratorfactory.R;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
	Spinner sp;
	final String PREFS = "CHOICE";
    final String PREFS2 = "TASK TYPES";
    String choice;
    String taskChoice;
    String dateChoice;
    String priorityChoice;
    String difficultyChoice;
    final Context con = this;

	
	@Override
	public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String month = "";
        switch(monthOfYear+1){
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;

        }
		dateChoice = month + " " + dayOfMonth + ", " + year;
        TextView dateTextView = (TextView) findViewById(R.id.deadline);
	    dateTextView.setText(dateChoice);
	  
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_task);
        final SharedPreferences prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        final SharedPreferences taskTypePrefs = getSharedPreferences(PREFS2, Context.MODE_PRIVATE);

        final ArrayList<String> task_types;
        Set<String> set2 = taskTypePrefs.getStringSet("task types", null);
        if(set2 != null){
            task_types = new ArrayList<String>(set2);
        }
        else{
            task_types = new ArrayList<>();
            task_types.add("None");
            task_types.add("Quiz");
            task_types.add("Paper");
            task_types.add("Long Test");
            task_types.add("Custom");
            Set<String> set = new HashSet<String>();
            set.addAll(task_types);
            SharedPreferences.Editor ed = taskTypePrefs.edit();
            ed.putStringSet("task types", set);

        }

		
		final ArrayList<String> priority = new ArrayList<>();
        priority.add("1(Highest)");
        priority.add("2");
        priority.add("3(Lowest)");
		
		final ArrayList<String> difficulty = new ArrayList<>();
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Hard");

        final Spinner taskType = (Spinner) findViewById(R.id.task_type);
		taskChoice = launchDialog(taskType, task_types);
		
		LinearLayout dueDate = (LinearLayout) findViewById(R.id.due_date);
        dueDate.setClickable(true);
        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddTaskActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.mdtp_accent_color));
                dpd.show(getFragmentManager(), "Datepickerdialog");

            }
        });
		
		final Spinner priorityClick = (Spinner) findViewById(R.id.priority_click);
        priorityChoice =launchDialog(priorityClick, priority);
        
		
		final Spinner difficultyLevel = (Spinner) findViewById(R.id.difficulty_level);
        difficultyChoice =launchDialog(difficultyLevel, difficulty);

        Button save = (Button) findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ed = prefs.edit();
                EditText taskName = (EditText) findViewById(R.id.newTaskName);
                EditText taskDesc = (EditText) findViewById(R.id.taskDescription);
                if(taskName.getText().toString().matches("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(con);
                    builder.setMessage("Please indicate your task's name");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else{
                    ed.putString("taskName", taskName.getText().toString());
                    ed.putString("taskType", taskChoice);
                    ed.putString("deadline", dateChoice);
                    ed.putString("priority", priorityChoice);
                    ed.putString("difficulty", difficultyChoice);
                    if(!taskDesc.getText().toString().matches("")){
                        ed.putString("taskDesc", taskDesc.getText().toString());
                    }

                    ed.commit();
                    Toast toast = Toast.makeText(con, "Task added successfully.", Toast.LENGTH_LONG);
                    toast.show();

                    AbstractTaskFactory fp = FactoryProducer.getFactory("School");
                    Task t = fp.createSchoolTask((String)taskType.getSelectedItem(), 0);


                    Calendar deadline3 = new GregorianCalendar();
                    deadline3.set(2016, 4, 2, 13, 30);


                    TaskBuilder buildTask = new TaskBuilder.Builder(t).difficulty((String)difficultyLevel.getSelectedItem())
                            .name(taskName.getText().toString())
                            .description(taskDesc.getText().toString())
                            .priority((String)priorityClick.getSelectedItem())
                            .deadline(((TextView)findViewById(R.id.deadline)).getText().toString())
                            .build();
                    t = buildTask.createTask();
                    OrganizingTasks o = new OrganizingTasks();
                     o.addTask(t);

                    //buildTask.setNotification(getApplicationContext(), t);

                    ListOfTasks l = new ListOfTasks();
                    l.updateFirebase(t);

                    finish();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
	
	public void endActivity(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setMessage("Discard changes?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddTaskActivity.this.finish();
            }
        });
        builder.setNegativeButton("CANCEL", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
	}
	
	public String launchDialog(Spinner sp, ArrayList<String> list){
        //String choice;
		Spinner spin = sp;
        final Context appCon = this;
		spin.setAdapter(new ArrayAdapter<String>(AddTaskActivity.this, R.layout.dropdown_item, list));
		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = (String) parent.getItemAtPosition(position);
                //final SharedPreferences prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                if (choice.equals("Custom")) {

                    final Dialog dialog = new Dialog(appCon);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_custom_task_type);

                    final Button done = (Button) dialog.findViewById(R.id.ok);
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final EditText customTaskType = (EditText) dialog.findViewById(R.id.custom_task_type);
                            choice = customTaskType.getText().toString();
//                                    SharedPreferences.Editor ed = prefs.edit();
//                                    ed.putString("choice", newTaskType);
//                                    ed.commit();
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
//                        else {
//                            SharedPreferences.Editor ed = prefs.edit();
//                            ed.putString("choice", choice);
//                            ed.commit();
//                            //dialog.dismiss();
//                        }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //dialog.dismiss();
                //close dialog fragment
            }


        });
        System.out.println("CHECK: " + choice);
        return choice;
	}
}



