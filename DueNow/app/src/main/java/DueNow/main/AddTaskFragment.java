package duenow.main;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import duenow.ListOfTasks;
import duenow.OrganizingTasks;
import duenow.Task;
import duenow.decoratorfactory.AbstractTaskFactory;
import duenow.decoratorfactory.FactoryProducer;
import duenow.decoratorfactory.R;

public class AddTaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTaskFragment newInstance(String param1, String param2) {
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AddTaskFragment() {
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
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.layout_new_task, container, false);

        ArrayList<String> diff = new ArrayList<String>();
        diff.add("Easy");
        diff.add("Medium");
        diff.add("Difficult");
        Spinner d = (Spinner) rootView.findViewById(R.id.difficulty);
        d.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, diff));

                ((Button) rootView.findViewById(R.id.testSave)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AbstractTaskFactory fp = FactoryProducer.getFactory("School");
                        Task t = fp.createSchoolTask("Homework", 0);

                        EditText taskName = (EditText) rootView.findViewById(R.id.taskName);
                        EditText taskDescription = (EditText) rootView.findViewById(R.id.taskName);

                        Calendar deadline3 = new GregorianCalendar();
                        deadline3.set(2016, 4, 2, 13, 30);

                        t.setDeadline(deadline3);
                        t.setC(getContext());
                        t.setName(taskName.getText().toString());
                        t.setDescription(taskDescription.getText().toString());

                       // OrganizingTasks o = new OrganizingTasks();
                       // o.addTask(t);

                        ListOfTasks l = new ListOfTasks();
                        l.updateFirebase(t);

                    }
                });



        return rootView;

    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with OtherSchool Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
