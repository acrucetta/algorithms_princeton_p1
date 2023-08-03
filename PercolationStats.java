import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    // Create the N by N grid with the int using the Percolation class
    Percolation percolation = new Percolation(n);

    // Run as many trials as described in the input
    for (int i = 0; i < trials; i++) {
      // Open a random site
      int row = StdRandom.uniformInt(1, n);
      int col = StdRandom.uniformInt(1, n);
      percolation.open(row, col);
    }
  }

  // sample mean of percolation threshold
  public double mean()

    // sample standard deviation of percolation threshold
  public double stddev()

    // low endpoint of 95% confidence interval
  public double confidenceLo()

    // high endpoint of 95% confidence interval
  public double confidenceHi()

   // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);

    // Run the trials and print the results
    PercolationStats stats = new PercolationStats(n, trials);

    System.out.println("mean                    = " + stats.mean());
    System.out.println("stddev                  = " + stats.stddev());
    System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }

}
