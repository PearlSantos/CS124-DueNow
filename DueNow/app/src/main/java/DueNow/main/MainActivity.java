package duenow.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import duenow.decoratorfactory.R;
import duenow.viewgroup.FlyOutContainer;

public class MainActivity extends AppCompatActivity {

    FlyOutContainer root;
    final String[] options = {"Tasks", "Postponed Tasks", "Finished Tasks", "Account Settings", "Logout"};
    final int[] imgID = {R.mipmap.ic_view_list_black_24dp, R.mipmap.ic_watch_later_black_24dp, R.mipmap.ic_check_black_24dp, R.mipmap.ic_account_box_black_24dp ,R.mipmap.ic_exit_to_app_black_24dp};
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_main, null);
//        list = (ListView) root.findViewById(R.id.menu_item);
//       // list.setAdapter(new CustomAdapter(this, options, imgID));
//        list.setAdapter(new ArrayAdapter<String>(this, R.layout.dummy_item, options));
//        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new AddTaskFragment()).commit();
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

    public void toggleMenu(View v){
        this.root.toggleMenu();
    }
}

class CustomAdapter extends BaseAdapter {
    String[] options;
    Context context;
    int[] imageID;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Context mainActivity,String[] optionsList, int[] optionIcons){
        options = optionsList;
        context = mainActivity;
        imageID = optionIcons;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        ImageView icon;
        TextView item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.menu_list_item, null);
        holder.icon = (ImageView) rowView.findViewById(R.id.icon);
        holder.item = (TextView) rowView.findViewById(R.id.itemname);
        holder.item.setText(options[position]);
        holder.icon.setImageResource(imageID[position]);
        return rowView;
    }
}