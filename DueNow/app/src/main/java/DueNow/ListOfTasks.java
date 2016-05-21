package duenow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

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

    private static ArrayList<Task>  list = new ArrayList<>();
    private static Task t;
    public static ArrayList<Task> getList(){
        Query queryRef = ref.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
      //          Task task = snapshot.getValue(Task.class);
                Map<String, Object> map = snapshot.getValue(Map.class);
//                String lol = "";
//                try {
//                    lol = new ObjectMapper().writeValueAsString(map);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
               System.out.println("CHECK: MAP" + map);
//                Task task = null;
//                try {
//                    task = new ObjectMapper().readValue(lol, new TypeReference<Task>(){});
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Task task = new ObjectMapper().convertValue(map, Task.class);
                System.out.println(snapshot.child("state").getValue());
//                State s = null;
//                try {
//                    s = new ObjectMapper().readValue((String)snapshot.child("state").getValue(), new TypeReference<State>(){});
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Task task = new Task();
//                t.setName((String)map.get("name"));
//                t.setDescription((String) map.get("description"));
//                t.setPriority((int) map.get("priority"));
//                t.setDeadline((Calendar) map.get("deadline"));


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

        if(!list.isEmpty()) {
            for (Task ta : list) {
                System.out.println(ta.getName());
            }
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

        ObjectMapper map = new ObjectMapper();
        taskMap = map.convertValue(t, Map.class);

//        try {
//            taskMap.put("state", map.writeValueAsString(t.getState()));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        //  specTask = ref.child(t.uniqueId);
       // specTask.setValue(taskMap);
        ref.child(t.uniqueId).setValue(taskMap);
        System.out.println("CHECK: SAVED TO FIREBASE");
        this.notifyObservers();
    }

}
