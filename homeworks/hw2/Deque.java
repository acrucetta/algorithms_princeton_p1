import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

  private Node first;
  private Node last;
  private int N = 0;

  private class Node {
    Item item;
    Node next;
    Node prev;
  }

  // construct an empty deque (constant time (O(1)))
  public Deque() {
    first = null;
    last = null;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return N == 0;
  }

  // return the number of items on the deque
  public int size() {
    return N;
  }

  // add the item to the front (constant)
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item cannot be null");
    }
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
    if (oldfirst != null) {
      oldfirst.prev = first;
    } else {
      last = first;
    }
    N++;
  }

  // add the item to the back (constant)
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item cannot be null");
    }
    Node oldlast = last;
    last = new Node();
    last.item = item;
    if (oldlast != null) {
      oldlast.next = last;
      last.prev = oldlast;
    } else {
      first = last;
    }
    N++;
  }

  // remove and return the item from the front (constant)
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("deque is empty");
    }
    Item item = first.item;
    first = first.next;
    if (first != null) {
      first.prev = null;
    } else {
      last = null;
    }
    N--;
    return item;
  }

  // remove and return the item from the back (constant)
  public Item removeLast() {
    if (isEmpty())
      throw new NoSuchElementException("deque is empty");
    Item item = last.item;
    last = last.prev;
    if (last != null) {
      last.next = null;
    } else {
      first = null;
    }
    N--;
    return item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    if (isEmpty())
      throw new NoSuchElementException("deque is empty");
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
    }

    public Item next() {
      if (!hasNext())
        throw new NoSuchElementException("deque is empty");
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    args = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
    Deque<String> deque = new Deque<String>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-"))
        deque.addFirst(item);
      else if (!deque.isEmpty())
        StdOut.print(deque.removeFirst() + " ");
    }
    StdOut.println("(" + deque.size() + " left on deque)");
  }
}