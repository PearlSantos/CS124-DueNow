package duenow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import duenow.main.FinishedTasks;
import duenow.main.PostponedTasks;
import duenow.main.TaskListFragment;
import duenow.state.State;
/**
 * Created by elysi on 4/30/2016.
 */
public class ListOfTasks extends Observable {
    public static final SimpleDateFormat f = new SimpleDateFormat("MMM dd, EEE, hh:mm a");
    public static final Firebase ref = new Firebase("https://cs124duenow.firebaseio.com/tasks");

    private static Task t;
    public static ArrayList<Task> list = new ArrayList<>();

    public static ArrayList<Task> getList(){
        Query queryRef = ref.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Map<String, Object> map = snapshot.getValue(Map.class);
                System.out.println("CHECK: MAP" + map);
                t = new ObjectMapper().convertValue(map, Task.class);
                list.add(t);
                System.out.println("CHECK: LIST SIZE " + list.size());
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
        });

        System.out.println("CHECK: END LIST SIZE" + list.size());
        if(!list.isEmpty()) {
            for (Task ta : list) {
                System.out.println("CHECK: END" + ta.getName());
            }
        }
        return list;
    }


    public ArrayList<Task> getList(String state) {
        Query queryRef = ref.orderByChild("state").equalTo(state, "state");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Map<String, Object> map = snapshot.getValue(Map.class);
                System.out.println("CHECK: MAP" + map);
                Task task = new ObjectMapper().convertValue(map, Task.class);
                System.out.println(snapshot.child("state").getValue());
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
        return list;
    }

    public static Task getTask(String uniqueId){
        System.out.println("CHECK: " + uniqueId);
        Query queryRef = ref.orderByChild("uniqueId").equalTo(uniqueId);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Map<String, Object> map = snapshot.getValue(Map.class);
                System.out.println("CHECK: MAP" + map);
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
    public void updateFirebase(Task t){
        ObjectMapper map = new ObjectMapper();
        taskMap = map.convertValue(t, Map.class);

        ref.child(t.uniqueId).updateChildren(taskMap);
        System.out.println("CHECK: SAVED TO FIREBASE");

        this.notifyObservers();
    }

}
