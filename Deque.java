import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node first;
  private Node last;
  private int N = 0;

  private class Node {
    Item item;
    Node next;
  }

  // construct an empty deque (constant time (O(1)))
  public Deque() {

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
  }

  // add the item to the back (constant)
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item cannot be null");
    }

  }

  // remove and return the item from the front (constant)
  public Item removeFirst() {
    if (isEmpty())
      throw new NoSuchElementException("deque is empty");
  }

  // remove and return the item from the back (constant)
  public Item removeLast() {
    if (isEmpty())
      throw new NoSuchElementException("deque is empty");
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    if (isEmpty())
      throw new NoSuchElementException("deque is empty");
  }

  // unit testing (required)
  public static void main(String[] args) {
    return;
  }

}