import java.util.ArrayList;
import java.util.List;

public class Stack1{
    private List<String> list;
    private int size;
    public String topper;

    public Stack1(){
        list = new ArrayList<String>();
        size = 0;
        topper = null;
    }

    public int getSize(){
        return size;
    }

    public void push (String element){
        list.add(element);
        topper = element;
        size++;
    }

    public String pop(){
        String maximum = list.get(size-1);
        list.remove(size-1);
        size--;
        topper = size == 0 ? null : list.get(size-1);
        return maximum;
    }
}
