package duenow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Observable;

import duenow.main.MainActivity;
// Firebase does not accept Calendar objects. You need to do some magic here. Calendar objects are strings
/**
 * Created by elysi on 4/30/2016.
 */
public class ListOfTasks extends Observable {
    private static final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");

    private static ArrayList<Task>  list = new ArrayList<>();
    private static Task t;
    public ArrayList<Task> getList(){
        Query queryRef = MainActivity.ref.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Task task = new ObjectMapper().convertValue(snapshot.getValue(), Task.class);
                ListOfTasks.this.addObserver(task);
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
        Query queryRef = MainActivity.ref.orderByKey().equalTo(name, "name").equalTo(description,"description").limitToFirst(1);
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
    public static void updateFirebase(Task t){
        taskMap = new ObjectMapper().convertValue(t, Map.class);
        specTask = MainActivity.ref.child(t.uniqueId);
        specTask.updateChildren(taskMap);
    }

    public void notifyObservers(){
        ListOfTasks.this.notifyAll();
    }
}
