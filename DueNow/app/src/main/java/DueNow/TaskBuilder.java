package duenow;

import android.content.Context;

import java.util.Calendar;

/**
 * Created by elysi on 5/21/2016.
 */
public class TaskBuilder {
    protected Task t;
    protected Context c;
    protected String name; // given by user
    protected String description; //given by user
    protected Calendar deadline; //given by user
    protected int priority;
    private TaskBuilder(Builder builder) {
        this.c = builder.c;
        this.name = builder.name;
        this.description = builder.description;
        this.deadline = builder.deadline;
        this.priority = builder.priority;
    }
    public static class Builder{
        protected Task t;
        public Builder(Task t){
            this.t = t;
        }
        public Builder context(Context context){
            t.setC(context);
            return this;
        }

        public Builder name(String n){
            t.setName(n);
            return this;
        }

        public Builder description(String d){
            t.setDescription(d);
            return this;
        }

        public Builder deadline(Calendar cal){
            t.setDeadline(cal);
            return this;
        }

        public Builder priority(int p){
            this.priority = p;
            return this;
        }

        public Task build(){
            return new Task(this);
        }
    }

}
