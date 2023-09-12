import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args) {
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      rq.enqueue(item);
    }
    int k = Integer.parseInt(args[0]);

    if (k > rq.size()) {
      throw new IllegalArgumentException("k is greater than the size of the queue");
    }

    for (int i = 0; i < k; i++) {
      StdOut.println(rq.dequeue());
    }
  }
}