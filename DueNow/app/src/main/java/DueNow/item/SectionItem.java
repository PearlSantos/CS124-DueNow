package duenow.item;

/**
 * Created by Pearl Santos on 5/9/2016.
 */
public class SectionItem implements Item {
    public String desc;
    public SectionItem(String desc){
        this.desc = desc;
    }
    @Override
    public boolean isSection() {
        return true;
    }
}
