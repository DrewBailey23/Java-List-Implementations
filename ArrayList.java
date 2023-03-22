package ArrayList;

public class ArrayList
{
    private Object[] array;
    private int manyItems;
    public ArrayList()
    {
        array = new Object[10];
        manyItems = 0;
    }
    public ArrayList(int initialCapacity)
    {
        array = new Object[initialCapacity];
        manyItems = 0;
    }
    public Object[] getArray()
    {
        return array;
    }
    public void setArray(Object[] obj)
    {
        array = obj;
    }
    public int size()
    {
        return manyItems;
    }
    public int getCapacity()
    {
        return array.length;
    }
    public void ensureCapacity(int capacity)
    {
        if (getCapacity() < capacity)
        {
            Object[] newArray = new Object[getCapacity() * 2];
            for(int x = 0; x < getCapacity(); x++)
            {
                newArray[x] = array[x];
            }
            setArray(newArray);
        }
    }
    public void add(Object object)
    {
        ensureCapacity(getCapacity() + 1);
        array[manyItems] = object;
        manyItems++;
    }
    public Object setIndex(int index, Object obj)
    {
        if (index > size() || index < 0)
        {
            throw new IllegalArgumentException("Index is out of bounds. Use add() if adding an element.");
        }
        else
        {
            Object temp = array[index];
            array[index] = obj;
            return temp;
        }
    }
    public boolean isEmpty()
    {
        if (size() == 0)
        {
            return true;
        }
        return false;
    }
    public int getIndex(Object obj)
    {
        for(int x = 0; x < size(); x++)
        {
            if (array[x] == obj)
            {
                return x;
            }
        }
        return -1;
    }
    public boolean contains(Object obj)
    {
        for(int x = 0; x < size(); x++)
        {
            if (array[x].equals(obj))
            {
                return true;
            }
        }
        return false;
    }
    public Object get(int index)
    {
        if (index > size() || index < 0)
        {
            throw new IllegalArgumentException("Index is out of bounds.");
        }
        else
        {
            return array[index];
        }
    }
    public void remove(int index)
    {
        if (index > size() || index < 0)
        {
            throw new IllegalArgumentException("Index is out of bounds.");
        }
        for(int x = index; x < size() - 1; x++)
        {
            array[x] = array[x + 1];
        }
        manyItems--;
    }
    public void addAll(ArrayList list)
    {
        ensureCapacity(size() + list.size());
        for(int x = 0; x < list.size(); x++)
        {
            this.add(list.get(x));
        }
    }
    public String toString()
    {
        String str = "[";
        for(int x = 0; x < size(); x++)
        {
            if (x == 0)
            {
                str += get(x).toString();
            }
            else
            {
                str += ", " + get(x).toString();
            }
        }
        str += "]";
        return str;
    }
    public static void main(String [] args)
    {
        ArrayList list = new ArrayList();
        list.add("One");
        list.add("Two");
        System.out.println(list);
        ArrayList list2 = new ArrayList();
        list2.add("Seven");
        list2.add("Why am i up at 3");
        System.out.println(list2);
        list.addAll(list2);
        System.out.println(list);
    }
}
