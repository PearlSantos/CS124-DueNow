//package duenow.viewgroup;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import duenow.decoratorfactory.R;
//
///**
// * Created by Pearl Santos on 5/12/2016.
// */
//public class SpinnerDialog extends Dialog {
//    public Spinner sp;
//    final String PREFS = "CHOICE";
//    ArrayList<String> options;
//    boolean customizable;
//    //int layout;
//
////    public SpinnerDialog(ArrayList<String> options) {
////        this.options = options;
////        // Empty constructor required for DialogFragment
////    }
//
////    public SpinnerDialog(ArrayList<String> options, boolean customizable){
////
////    }
//
////    public static SpinnerDialog newInstance() {
////        SpinnerDialog fragment = new SpinnerDialog();
////        Bundle args = new Bundle();
////        args.putStringArrayList("ArrayList", options);
////        //args.putBoolean("boolean", customizable);
////        //args.putInt("int", layout);
////        fragment.setArguments(args);
////        return fragment;
////    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.task_type_spinner, container);
//        ArrayAdapter<String> adapter = null;
//        final SharedPreferences prefs = getActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
//        sp = (Spinner) view.findViewById(R.id.spinner);
//        if(!options.isEmpty()){
//            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,options);
//            sp.setAdapter(adapter);
//        }
//
//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String choice = (String) parent.getItemAtPosition(position);
//                if(choice.equals("Custom")){
//                    //open new dialog here
//                    final Dialog dialog = new Dialog(getActivity());
//                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                    dialog.setContentView(R.layout.layout_custom_task_type);
//
//                    final Button done = (Button) dialog.findViewById(R.id.ok);
//                    done.setOnClickListener(new View.OnClickListener(){
//                        @Override
//                        public void onClick(View v) {
//                            final EditText customTaskType = (EditText) dialog.findViewById(R.id.custom_task_type);
//                            String newTaskType = customTaskType.getText().toString();
//                            SharedPreferences prefs = getActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
//                            SharedPreferences.Editor ed = prefs.edit();
//                            ed.putString("choice", newTaskType);
//                            ed.commit();
//                            dialog.dismiss();
//                        }
//                    });
//
//                    dialog.show();
//                    dismiss();
//
//                }
//
//
//                SharedPreferences.Editor ed = prefs.edit();
//                ed.putString("choice", choice);
//                ed.commit();
//                dismiss();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                dismiss();
//                //close dialog fragment
//            }
//        });
//
//
//
//        return view;
//    }
//}
