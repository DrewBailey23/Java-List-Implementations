package SortedList;

public class SortedList<T extends Comparable<? super T>> implements Comparable<SortedList<T>>
{
    private Object[] array;
    private int manyItems;
    public SortedList()
    {
        array = new Object[10];
    }
    public SortedList(int len)
    {
        array = new Object[len];
    }
    
    @SuppressWarnings("unchecked")
    public void add(T data)
    {
        ensureCapacity(manyItems + 2);
        boolean check = false;
        for (int x = 0; x < manyItems && (!(check)); x++)
        {
            if (data.compareTo((T)array[x]) <= 0)
            {
                for (int y = manyItems; y >= x; y--)
                {
                    array[y + 1] = array[y];
                }
                array[x] = data;
                check = true;
            }
        }
        if (!(check))
        {
            array[manyItems] = data;
        }
        manyItems++;
    }
    public Object remove(int index)
    {
        if (index < 0 || index > manyItems)
        {
            throw new ArrayIndexOutOfBoundsException("Invalid index.");
        }
        Object temp = get(index);
        for (int x = index; x < manyItems - 1; x++)
        {
            array[x] = array[x + 1];
        }
        manyItems--;
        return temp;
    }
    public void ensureCapacity(int num)
    {
        if (array.length < num)
        {
            Object[] newArr = new Object[num];
            for (int x = 0; x < manyItems; x++)
            {
                newArr[x] = array[x];
            }
            array = newArr;
        }
    }
    
    public Object[] getArray()
    {
        Object[] copy = new Object[manyItems];
        for(int x = 0; x < manyItems; x++)
        {
            copy[x] = array[x];
        }
        return copy;
    }
    public int size()
    {
        return manyItems;
    }
    public int getCapacity()
    {
        return array.length;
    }
    public String toString()
    {
        String str = "";
        for(int x = 0; x < manyItems; x++)
        {
            if (x != manyItems - 1)
            {
                str += array[x] + ", ";
            }
            else 
            {
                str += array[x];
            }
        }
        return str;
    }
    @SuppressWarnings("unchecked")
    public int compareTo(SortedList<T> list)
    {
        if (list == null)
        {
            return 1;
        }
        if (size() != list.size())
        {
            return size() - list.size();
        }
        for(int x = 0; x < size(); x++)
        {
            if (list.get(x).compareTo((T) array[x]) != 0)
            {
                return ((T)array[x]).compareTo(list.get(x));
            }
        }
        return 0; 
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean equals(Object obj)
    {
        if (obj instanceof SortedList)
        {
            if (this.compareTo((SortedList)obj) == 0)
            {
                return true;
            }
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public T get(int index)
    {
        return (T)array[index];
    }
    
    public int getIndex(T data)
    {
        return binarySearch(this, data, 0, this.size() -1);
    }
    public boolean contains(T data)
    {
        if (binarySearch(this, data, 0, this.size() -1) == -1)
        {
            return false;
        }
        return true;
    }
    public static <T extends Comparable<? super T>>int binarySearch(SortedList<T> array, T data, int start, int end)
    {
        if (end > array.size() - 1 || start < 0)
        {
            throw new IndexOutOfBoundsException("Parameters are not within the array's index.");
        }
        int middle = end - ((end - start)/2);
        if (array.get(middle).compareTo(data) > 0 && end - start != 1)
        {
            return binarySearch(array, data, start, middle);
        }
        else if (array.get(middle).compareTo(data) < 0 && end - start != 1)
        {
            return binarySearch(array, data, middle, end);
        }
        else
        {
            if (array.get(middle) == data)
            {
                return middle;
            }
            else if (array.get(start) == data)
            {
                return start;
            }
            else if (array.get(end) == data)
            {
                return end;
            }
            return -1;
        }
    }
    /**
     * Likely will have bug issues if using Objects over primitive data types, as the clone will *probably* contain
     * an array of references to the objects in the original, over new independent objects like it should have. 
     * Easy fix if using objects, force T to implement cloneable and change clone.add(this.get(x)) to 
     * clone.add(this.get(x).clone()). This version works for primitive data types.
     */
    public SortedList<T> clone()
    {
        SortedList<T> clone = new SortedList<T>(this.getCapacity());
        for(int x = 0; x < this.size(); x++)
        {
            clone.add(this.get(x));
        }
        return clone;
    }
    /**
     * Used to add a SortedList to this SortedList.
     * 
     * Will have same bug issue as clone(), fix would be the same as with clone.
     * 
     * @param list a SortedList of the same data type as this SortedList.
     */
    public void addAll(SortedList<T> list)
    {
        for(int x = 0; x < list.size(); x++)
        {
            this.add(list.get(x));
        }
    }
    /**
     * Used to add an array of values to this SortedList.
     * 
     * Will have same bug issue as clone(), fix would be the same as with clone.
     * 
     * @param array an array of the same data type as this SortedList.
     */
    public void addAll(T[] array)
    {
        for(int x = 0; x < array.length; x++)
        {
            this.add(array[x]);
        }
    }
    
    
    public static void main(String [] args)
    {
        SortedList<Integer> list = new SortedList<Integer>();
        SortedList<String> list2 = new SortedList<String>();
        list2.add("aaa");
        list2.add("bbb");
        list2.add("aa");
        System.out.println(list2);
        
        Integer[] arr = {9, 5, 2, 7, 4, 1, 9, 0, 43, 6, 4, 63, 87};
        
        for(int x = 0; x < 13; x++)
        {
            list.add(arr[x]);
        }
        
        System.out.println(list + " " + list.size());
        
        SortedList<Integer> list3 = new SortedList<Integer>();
        list3.add(2);
        list3.add(1);
        list3.add(3);
        list3.add(0);
        
        SortedList<Integer> list4 = new SortedList<Integer>();
        list4.add(0);
        list4.add(1);
        list4.add(2);
        list4.add(3);
        list.addAll(arr);
        System.out.println(list);
    }
}
