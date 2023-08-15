package homeworks.hw2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
/*
 *  Requirements
 * 
 * Performance must be constant amoritized time (c*m)
 * Any sequence of m randomized queue operations must take at most cm 
 * steps in the worst case for some constant c.
 * 
 * Memory: use at most 48n + 192 bytes of memory
 * 
 * The iterator must support next() and hasNext() in constant worst-case time.
 * The construction must take linear time in the number of items. We will
 * need to also use a linear amount of extra memory per iterator.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] a = (Item[]) new Object[1];
  private int N = 0;

  private void resize(int max) {
    // Move stack to a new array of size max.
    Item[] temp = (Item[]) new Object[max];
    for (int i = 0; i < N; i++) {
      temp[i] = a[i];
    }
    a = temp;
  }

  // construct an empty randomized queue
  public RandomizedQueue() {
    a = (Item[]) new Object[1];
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return N == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return N;
  }

  // add the item
  public void enqueue(Item item) {
    if (N == a.length)
      resize(2 * a.length);
    a[N++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    // Pick random number from 0 to N
    int randomPop = StdRandom.uniformInt(0, N);

    // Swap the random popped value with N-1
    a[N - 1] = a[randomPop];

    Item item = a[--N];
    a[N] = null;
    if (N > 0 && N == a.length / 4)
      resize(a.length / 2);
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    // Pick random number from 0 to N
    int randomPop = StdRandom.uniformInt(0, N);
    return a[randomPop];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedIterator();
  }

  private class RandomizedIterator implements Iterator<Item> {
    private int[] shuffledIndices;
    private int current = 0;

    public RandomizedIterator() {
      shuffledIndices = new int[N];
      for (int i = 0; i < N; i++) {
        shuffledIndices[i] = i;
      }
      StdRandom.shuffle(shuffledIndices);
    }

    public boolean hasNext() {
      return current < N;
    }

    public Item next() {
      return a[shuffledIndices[current++]];
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
  }

}