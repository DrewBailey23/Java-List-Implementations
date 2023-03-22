/**
 * 
 * @author Drew Bailey
 * @version 1.0
 * @param <T>
 *            generic object variable, must implement the comparable interface.
 */
public class Node<T extends Comparable<? super T>>
{
    private T data;
    private Node<T> head;
    private Node<T> tail;

    public Node()
    {
        head = null;
        tail = null;
        data = null;
    }

    public Node(T info)
    {
        data = info;
        head = null;
        tail = null;
    }

    public Node(T info, Node<T> one)
    {
        data = info;
        head = one;
        tail = null;
    }

    public Node(T info, Node<T> one, Node<T> two)
    {
        data = info;
        head = one;
        tail = two;
    }

    public Node<T> getHead()
    {
        return head;
    }

    public void setHead(Node<T> node)
    {
        head = node;
    }

    public Node<T> getTail()
    {
        return tail;
    }

    public void setTail(Node<T> node)
    {
        tail = node;
    }

    public void setData(T info)
    {
        data = info;
    }

    public T getData()
    {
        return data;
    }

    public String toString()
    {
        return data.toString();
    }
}
