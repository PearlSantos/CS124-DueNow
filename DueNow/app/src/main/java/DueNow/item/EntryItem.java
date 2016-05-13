package duenow.item;

/**
 * Created by Pearl Santos on 5/9/2016.
 */
public class EntryItem implements Item{
    public String taskname;
    public String dateStart;
    public String deadLine;
    public EntryItem(String taskname, String dateStart, String deadline){
        this.taskname = taskname;
        this.dateStart = dateStart;
        this.deadLine = deadline;
    }
    @Override
    public boolean isSection() {
        return false;
    }
}
