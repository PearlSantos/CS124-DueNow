package duenow.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import duenow.decoratorfactory.R;
import duenow.item.EntryItem;
import duenow.item.Item;
import duenow.item.SectionItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskListFragment newInstance(String param1, String param2) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<Item> items = new ArrayList<>();
        items.add(new SectionItem("Monday"));
        items.add(new EntryItem("Hi","Hi","Hi"));
        items.add(new EntryItem("Hello","Hello","Hello"));
        items.add(new EntryItem("How","How","How"));
        items.add(new SectionItem("Tuesday"));
        items.add(new EntryItem("Are","Are","Are"));
        items.add(new EntryItem("You","You","You"));
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //ImageButton FAB = (ImageButton) rootView.findViewById(R.id.fabButton);


        ListView taskList = (ListView) rootView.findViewById(R.id.main_listview);
       // taskList.setAdapter(new CustomMainAdapter(this.getContext(), taskName, dateStart, deadline));
        taskList.setAdapter(new EntryAdapter(this.getContext(), items));
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



}


class EntryAdapter extends ArrayAdapter {
    ArrayList<Item> items;
    Context context;
    private static LayoutInflater vi = null;
    public EntryAdapter(Context context,ArrayList<Item> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final Item i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                SectionItem si = (SectionItem)i;
                rowView = vi.inflate(R.layout.dummy_item, null);
                rowView.setOnClickListener(null);
                rowView.setOnLongClickListener(null);
                rowView.setLongClickable(false);
                final TextView sectionView = (TextView) rowView.findViewById(R.id.divider);
                sectionView.setText(si.desc);
            } else {
                EntryItem ei = (EntryItem)i;
                rowView = vi.inflate(R.layout.list_item, null);
                TextView taskName = (TextView) rowView.findViewById(R.id.taskname);
                TextView timeStart = (TextView) rowView.findViewById(R.id.starting_date);
                TextView deadline = (TextView) rowView.findViewById(R.id.deadline);
                if(taskName != null) taskName.setText(ei.taskname);
                if(timeStart != null) timeStart.setText(ei.dateStart);
                if(deadline != null) deadline.setText(ei.deadLine);
            }
        }
        return rowView;
    }
}



