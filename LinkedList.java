/**
 * @author Andrew Bailey
 * @param <T> accepts any object with the Comparable interface implemented.
 */
public class LinkedList<T extends Comparable<? super T>> implements Comparable<LinkedList<T>>
{
    private Node<T> head;
    private Node<T> tail;
    private Node<T> cursor;
    private int manyNodes;
    public LinkedList()
    {
        head = null;
        tail = null;
        cursor = null;
        manyNodes = 0;
    }
    public Node<T> getHead()
    {
        return head;
    }
    public Node<T> getTail()
    {
        return tail;
    }
    public Node<T> getCursor()
    {
        return cursor;
    }
    public void setHead(Node<T> node)
    {
        head = node;
    }
    public void setTail(Node<T> node)
    {
        tail = node;
    }
    public void setCursor(Node<T> node)
    {
        cursor = node;
    }
    public int size()
    {
        return manyNodes;
    }
    public boolean isCurrent()
    {
        if (cursor == null)
        {
            return false;
        }
        return true;
    }
    public boolean hasNext()
    {
        if (this.isCurrent())
        {
            if (this.getCursor().getTail()!= null)
            {
                return true;
            }
        }
        return false;
    }
    public void start()
    {
        this.setCursor(head);
    }
    public boolean hasPrevious()
    {
        if (this.isCurrent())
        {
            if (this.getCursor().getHead() != null)
            {
                return true;
            }
        }
        return false;
    }
    public T getCurrent()
    {
        return this.getCursor().getData();
    }
    public T advance()
    {
        if (!(isCurrent()))
        {
            throw new IllegalStateException("No element selected.");
        }
        if (!(this.hasNext()))
        {
            this.setCursor(null);
            return null;
        }
        else
        {
            this.setCursor(this.getCursor().getTail());
            return this.getCurrent();
        }
    }
    public T regress()
    {
        if (!(isCurrent()))
        {
            throw new IllegalStateException("No element selected.");
        }
        if(!(this.hasPrevious()))
        {
            this.setCursor(null);
            return null;
        }
        else
        {
            this.setCursor(this.getCursor().getHead());
            return this.getCurrent();
        }
    }
    public void addAfter(T data)
    {
        if (this.size() == 0)
        {
            Node<T> node = new Node<T>(data);
            this.setHead(node);
            this.setTail(node);
            this.setCursor(node);
        }
        else if (this.getCursor() == null)
        {
            Node<T> node = new Node<T>(data, this.getTail());
            this.getTail().setTail(node);
            this.setTail(node);
            this.setCursor(node);
        }
        else if (this.getCursor() != null)
        {
            Node<T> node = new Node<T>(data, this.getCursor(), this.getCursor().getTail());
            this.getCursor().setTail(node);
            if (node.getTail() == null)
            {
                this.setTail(node);
            }
            this.setCursor(node);
        }
        manyNodes++;
    }
    public void addBefore(T data)
    {
        if (this.size() == 0)
        {
            Node<T> node = new Node<T>(data);
            this.setHead(node);
            this.setTail(node);
            this.setCursor(node);
        }
        else if (!(this.isCurrent()))
        {
            Node<T> node = new Node<T>(data, null, this.getHead());
            this.getHead().setHead(node);
            this.setHead(node);
            this.setCursor(node);
        }
        else if (this.isCurrent())
        {
            if (this.hasPrevious())
            {
                Node<T> node = new Node<T>(data, this.getCursor().getHead(), this.getCursor());
                this.getCursor().getHead().setTail(node);
                this.getCursor().setHead(node);
                this.setCursor(node);
            }
            else
            {
                Node<T> node = new Node<T>(data, null, this.getCursor());
                this.getCursor().setHead(node);
                this.setHead(node);
                this.setCursor(node);
            }
        }
        manyNodes++;
    }
    public T remove()
    {
        if (!(isCurrent()))
        {
            throw new IllegalStateException("No element selected.");
        }
        T data = getCurrent();
        if (this.size() == 1)
        {
            this.setHead(null);
            this.setTail(null);
            this.setCursor(null);;
        }
        else if (this.getCursor() == this.getHead())
        {
            this.advance();
            this.getCursor().setHead(null);
            this.setHead(this.getCursor());
        }
        else if (this.getCursor() == this.getTail())
        {
            this.regress();
            this.getCursor().setTail(null);
            this.setTail(this.getCursor());
        }
        else
        {
            this.getCursor().getHead().setTail(this.getCursor().getHead());
            this.getCursor().getTail().setHead(this.getCursor().getHead());
            this.setCursor(this.getCursor().getTail());
        }
        return data;
    }
    public LinkedList<T> clone()
    {
        LinkedList<T> clone = new LinkedList<T>();
        Node<T> cursorMark = null;
        if (this.isCurrent())
        {
            cursorMark = this.getCursor();
        }
        for(this.start(); this.isCurrent(); advance())
        {
            clone.addAfter(this.getCurrent());
        }
        this.setCursor(cursorMark);
        return clone;
    }
    public String toString()
    {
        String str = "<";
        Node<T> cursorMark = this.getCursor();
        for(this.start(); this.isCurrent(); this.advance())
        {
            if (this.getCursor() == this.getHead() && this.getCursor() == cursorMark)
            {
                str += "[" + this.getCurrent().toString() + "]";
            }
            else if (this.getCursor() == cursorMark)
            {
                str += ", [" + this.getCurrent().toString() + "]";
            }
            else if (this.getCursor() == this.getHead())
            {
                str += this.getCurrent().toString();
            }
            else
            {
                str += ", " + this.getCurrent().toString();
            }
        }
        this.setCursor(cursorMark);
        str += ">";
        return str;
    }
    @Override
    public int compareTo(LinkedList<T> list)
    {
        if (this.size() != list.size())
        {
            return this.size() - list.size();
        }
        else if (this.size() == list.size())
        {
            Node<T> cursorOne = this.getCursor();
            Node<T> cursorTwo = list.getCursor();
            list.start();
            for(this.start(); this.isCurrent(); this.advance())
            {
                if (this.getCurrent() != list.getCurrent())
                {
                    int num = this.getCurrent().compareTo(list.getCurrent());
                    this.setCursor(cursorOne);
                    list.setCursor(cursorTwo);
                    return num;
                }
                list.advance();
            }
            this.setCursor(cursorOne);
            list.setCursor(cursorTwo);
            return 0;
        }
        return 0;
    }
    public boolean equals(LinkedList<T> list)
    {
        if (this.toString().equals(list.toString()))
        {
            return true;
        }
        return false;
    }
    public static <T extends Comparable<? super T>> LinkedList<T> concatenation(LinkedList<T> list1, LinkedList<T> list2)
    { 
        LinkedList<T> combined = new LinkedList<T>();
        Node<T> cursOne = list1.getCursor();
        Node<T> curseTwo = list2.getCursor();
        for(list1.start(); list1.isCurrent(); list1.advance())
        {
            combined.addAfter(list1.getCurrent());
        }
        for(list2.start(); list2.isCurrent(); list2.advance())
        {
            combined.addAfter(list2.getCurrent());
        }
        combined.setCursor(null);
        list1.setCursor(cursOne);
        list2.setCursor(curseTwo);
        return combined; 
    }
    public void addAll(LinkedList<T> list)
    {
        Node<T> cursorMark = this.getCursor();
        Node<T> cursorMark2 = list.getCursor();
        this.setCursor(null);
        for(list.start(); list.isCurrent(); list.advance())
        {
            this.addAfter(list.getCurrent());
        }
        list.setCursor(cursorMark2);
        this.setCursor(cursorMark);
    }
    public void addAll(T[] array)
    {
        Node<T> cursorMark = this.getCursor();
        this.setCursor(null);
        for(int x = 0; x < array.length; x++)
        {
            if (array[x] != null)
            {
                this.addAfter(array[x]);
            }
        }
        this.setCursor(cursorMark);
    }
    public Object[] toArray()
    {
        Object[] arr = new Object[this.size()];
        Node<T> cursorMark = this.getCursor();
        int x = 0;
        for(this.start(); this.isCurrent(); this.advance(), x++)
        {
            arr[x] = this.getCurrent();
        }
        this.setCursor(cursorMark);
        return arr;
    }
    
    public static void main(String [] args)
    {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.addAfter(1);
        list.addAfter(2);
        list.addAfter(3);
        list.addBefore(4);
        list.setCursor(null);
        list.addAfter(5);
        list.start();
        list.addBefore(6);
        list.remove();
        System.out.println(list);
    }
}
