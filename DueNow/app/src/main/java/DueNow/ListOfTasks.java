package duenow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Observable;

import duenow.main.AddTaskFragment;
import duenow.main.FinishedTasks;
import duenow.main.MainActivity;
import duenow.main.PostponedTasks;
import duenow.main.TaskListFragment;
// Firebase does not accept Calendar objects. You need to do some magic here. Calendar objects are strings
/**
 * Created by elysi on 4/30/2016.
 */
public class ListOfTasks extends Observable {
    public static final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");
    public static final Firebase ref = new Firebase("https://cs124duenow.firebaseio.com/tasks");

    private static ArrayList<Task>  list = new ArrayList<>();
    private static Task t;
    public static ArrayList<Task> getList(){
        Query queryRef = ref.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
      //          Task task = snapshot.getValue(Task.class);
                Map<String, Object> map = snapshot.getValue(Map.class);
                String lol = "";
                try {
                    lol = new ObjectMapper().writeValueAsString(map);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                System.out.println("CHECK MAP" + map);
                Task task = null;
                try {
                    task = new ObjectMapper().readValue(lol, new TypeReference<Task>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Task task = new ObjectMapper().convertValue(map, new TypeReference<Task>(){});
                list.add(task);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
            // ....
        });

        for(Task ta: list){
            System.out.println(ta.getName());
        }
        return list;
    }

    public static Task getTask(String name, String description){
        Query queryRef = ref.orderByKey().equalTo(name, "name").equalTo(description,"description").limitToFirst(1);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                t = snapshot.getValue(Task.class);
                System.out.println("CHECK: Task Name -" + t.getName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
            // ....
        });
        return t;
    }

    static Map<String, Object> taskMap;
    static Firebase specTask;
    public void updateFirebase(Task t){
        this.addObserver(TaskListFragment.newInstance());
        this.addObserver(PostponedTasks.newInstance());
        this.addObserver(FinishedTasks.newInstance());

        taskMap = new ObjectMapper().convertValue(t, Map.class);
        specTask = ref.child(t.uniqueId);
        specTask.setValue(taskMap);

        this.notifyObservers();
    }

}
