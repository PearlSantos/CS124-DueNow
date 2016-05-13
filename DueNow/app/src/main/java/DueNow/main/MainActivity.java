package duenow.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import duenow.decoratorfactory.R;
import duenow.viewgroup.FlyOutContainer;

public class MainActivity extends AppCompatActivity {

    FlyOutContainer root;
    final String[] options = {"Tasks", "Postponed Tasks", "Finished Tasks", "Account Settings", "Logout"};
    final Integer[] imgID = {R.mipmap.ic_view_list_black_24dp, R.mipmap.ic_watch_later_black_24dp, R.mipmap.ic_check_black_24dp, R.mipmap.ic_account_box_black_24dp ,R.mipmap.ic_exit_to_app_black_24dp};
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_main, null);
        list = (ListView) root.findViewById(R.id.menu);
		list.setAdapter(new CustomList(this, options, imgID));
        list.setOnItemClickListener(new DrawerItemClickListener());
//        list.setAdapter(new ArrayAdapter<String>(this, R.layout.dummy_item, options));
//        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new TaskListFragment()).commit();

        final ImageButton FAB = (ImageButton) root.findViewById(R.id.fabButton);
        FAB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FAB.setVisibility(View.GONE);
                TextView title = (TextView) root.findViewById(R.id.appTitle);
                title.setText("");
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new AddTaskFragment()).commit();
            }
        });

        setContentView(root);
		
		
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
	
	 private class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextViewPlus text = (TextViewPlus) view.findViewById(R.id.menu_item);
//                text.setBackgroundColor(Color.parseColor(getResources().getString(R.string.backgroundGray)));
                selectItem(position);
            }
        }

        private void selectItem(int position) {
            // update the main content by replacing fragments
            Fragment fragment = null;
            //Bundle args = new Bundle();

            switch(position){
                case 0:
                    fragment = new TaskListFragment();
                    root.toggleMenu();
                    break;
                case 1:
                    fragment = new TaskListFragment(); //must be PostponedTasks
                    root.toggleMenu();
                    break;
                case 2:
                    fragment = new TaskListFragment(); //finished tasks
                    root.toggleMenu();
                    break;
                case 3:
                    fragment = new TaskListFragment(); //AccountSettings
                    root.toggleMenu();
                    break;
                case 4:
                    logout(); //logout
                    break;
                default:
                    break;
            }
            if(fragment != null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }


            // update selected item and title, then close the drawer
            list.setItemChecked(position, true);
            //title.setText(menu[position]); //setting the title

        }

    public void toggleMenu(View v){
        this.root.toggleMenu();
    }
	
	public void logout(){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
}

class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] name;
    private final Integer[] imageID;
    public CustomList(Activity context,String[] name, Integer[] imageID) {
        super(context, R.layout.menu_list_item, name);
        this.context = context;
        this.name = name;
        this.imageID = imageID;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.menu_list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemname);;

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(name[position]);

        imageView.setImageResource(imageID[position]);
        return rowView;
    }
}

