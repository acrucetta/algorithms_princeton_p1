import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

  private double[] arrOpenSites;
  private double mean;
  private double stddev;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    // Create the N by N grid with the int using the Percolation class
    this.arrOpenSites = new double[trials];

    // Run as many trials as described in the input
    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int row = StdRandom.uniformInt(1, n + 1);
        int col = StdRandom.uniformInt(1, n + 1);
        percolation.open(row, col);
      }
      // Add the number of open sites to the array
      this.arrOpenSites[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }

    this.mean = mean();
    this.stddev = stddev();
  }

  // sample mean of percolation threshold
  public double mean() {
    // Use the StdStats class to calculate the mean of the arrOpenSites array
    return StdStats.mean(arrOpenSites);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    // Use the StdStats class to calculate the standard deviation of the
    // arrOpenSites array
    return StdStats.stddev(arrOpenSites);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    // Use the StdStats class to calculate the low endpoint of the 95% confidence
    // interval
    double confindenceLo = mean - ((1.96 * stddev) / Math.sqrt(arrOpenSites.length));
    return confindenceLo;
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    double confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(arrOpenSites.length));
    return confidenceHi;
  }

  // test client (see below)
  public static void main(String[] args) {
    // int n = Integer.parseInt(args[0]);
    // int trials = Integer.parseInt(args[1]);
    int n = 5;
    int trials = 10;

    // Run the trials and print the results
    PercolationStats stats = new PercolationStats(n, trials);

    System.out.println("mean                    = " + stats.mean());
    System.out.println("stddev                  = " + stats.stddev());
    System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }

}
