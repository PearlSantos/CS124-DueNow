package duenow.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import duenow.decoratorfactory.R;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
	Spinner sp;
	final String PREFS = "CHOICE";
	
	@Override
	public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
		String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
		Context context = getApplicationContext();
//		CharSequence text = "Hello toast!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, date, duration);
		toast.show();
	  //dateTextView.setText(date);
	  
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_task);
		

		
		Button save = (Button) findViewById(R.id.saveButton);
		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				//save your shit here
			}
		});
		
		final ArrayList<String> task_types = new ArrayList<>();
		task_types.add("None");
        task_types.add("Quiz");
        task_types.add("Paper");
        task_types.add("Long Test");
        task_types.add("Custom");
		
		final ArrayList<String> priority = new ArrayList<>();
        priority.add("1(Highest)");
        priority.add("2");
        priority.add("3(Lowest)");
		
		final ArrayList<String> difficulty = new ArrayList<>();
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Hard");

        LinearLayout taskType = (LinearLayout) findViewById(R.id.task_type);
        taskType.setClickable(true);
        taskType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Hi hello how r u", Toast.LENGTH_LONG);
                toast.show();
                //launchDialog(task_types);

            }
        });
		
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
                dpd.show(getFragmentManager(), "Datepickerdialog");

            }
        });
		
		LinearLayout priorityClick = (LinearLayout) findViewById(R.id.priority_click);
        priorityClick.setClickable(true);
        priorityClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialog(priority);

            }
        });
		
		LinearLayout difficultyLevel = (LinearLayout) findViewById(R.id.difficulty_level);
        taskType.setClickable(true);
        taskType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialog(difficulty);

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
		//make toast here that says you stupid bitch u didn't save gago
		finish();
	}
	
	public void launchDialog(ArrayList<String> list){

		final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.task_type_spinner);

                final SharedPreferences prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                sp = (Spinner) dialog.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
                sp.setAdapter(adapter);

                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String choice = (String) parent.getItemAtPosition(position);
                        if (choice.equals("Custom")) {

                            final Dialog dialog2 = new Dialog(getApplicationContext());
                            dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog2.setContentView(R.layout.layout_custom_task_type);

                            final Button done = (Button) dialog2.findViewById(R.id.ok);
                            done.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    final EditText customTaskType = (EditText) dialog2.findViewById(R.id.custom_task_type);
                                    String newTaskType = customTaskType.getText().toString();
                                    SharedPreferences prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor ed = prefs.edit();
                                    ed.putString("choice", newTaskType);
                                    ed.commit();
                                    dialog2.dismiss();
                                }
                            });

                            dialog2.show();
                            dialog.dismiss();

                        } else {
                            SharedPreferences.Editor ed = prefs.edit();
                            ed.putString("choice", choice);
                            ed.commit();
                            dialog.dismiss();
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        dialog.dismiss();
                        //close dialog fragment
                    }


                });
	}
	
	 
}



