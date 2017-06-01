package Journal;

import java.util.ArrayList;

public class Rows {

    public ArrayList<Object> cols = new ArrayList<Object>();

    public Rows() {}

    public void Add(Object item)
    {
        cols.add(item);
    }
    public Object Get(int index)
    {
        return cols.get(index);
    }
    public void Set(int index, Object element)
    {
        cols.set(index, element);
    }
}