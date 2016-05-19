package duenow;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by elysi on 4/30/2016.
 */
public class ListOfTasks {
    private static final Firebase ref = new Firebase("https://cs124duenow.firebaseio.com/tasks");
    private static ArrayList<Task>  list = new ArrayList<>();
    private static Task t;
    public static ArrayList<Task> getList(){
        Query queryRef = ref.orderByKey();
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                list.add(snapshot.getValue(Task.class));
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


}
