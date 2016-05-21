package duenow.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import duenow.ListOfTasks;
import duenow.Task;
import duenow.state.State;

/**
 * Created by elysi on 5/20/2016.
 */
public class CodeReuse {

    public static void refresh(Fragment frag, ViewGroup container){
        FragmentManager manager = frag.getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment newFragment = frag;
        frag.onDestroy();
        ft.remove(frag);
        if(container !=null)
            ft.replace(container.getId(), newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

//    public static void refreshList(String state, ListView listview, Context c){
//        ArrayList<Task> list = ListOfTasks.getList();
//        ArrayList<Task> newList= new ArrayList<>();
//        for(Task e: list){
//            if(e.getState().equals(state)){
//                newList.add(e);
//            }
//        }
//
//        listview.setAdapter(new EntryAdapter(c, newList));
//    }
}
