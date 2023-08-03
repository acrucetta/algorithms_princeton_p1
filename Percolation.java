import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int[][] arr;
  private int open_sites = 0;
  private final WeightedQuickUnionUF uf;
  private final int topVirtualSite;
  private final int bottomVirtualSite;

  private boolean isInputValid(int row, int col) {
    if (row < 0 || row > arr.length) {
      return false;
    }
    if (col < 0 || col > arr.length) {
      return false;
    }
    return true;
  }

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("n must be greater than 0");
    }
    // Create an array of N x N with all the sites blocked
    // Blocked = 0; Open = 1
    int[][] arr = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        arr[i][j] = 0;
      }
    }
    // Create a WeightedQuickUnionUF object with N x N + 2 for the virtual sites
    // The virtual sites will be at the top and bottom
    this.uf = new WeightedQuickUnionUF(n * n + 2);
    this.topVirtualSite = n * n;
    this.bottomVirtualSite = n * n + 1;

    // Now we will connect the top row to the top virtual site
    for (int i = 0; i < n; i++) {
      uf.union(topVirtualSite, i);
    }

    // Now we will connect the bottom row to the bottom virtual site
    for (int i = 0; i < n; i++) {
      uf.union(bottomVirtualSite, n * (n - 1) + i);
    }
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    // Adjust the rows and cols to start from 1
    row = row - 1;
    col = col - 1;
    if (!isInputValid(row, col)) {
      throw new IllegalArgumentException("row and col must be between 0 and N");
    }
    if (arr[row][col] == 0) {
      arr[row][col] = 1;
      open_sites++;
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    row = row - 1;
    col = col - 1;
    if (!isInputValid(row, col)) {
      throw new IllegalArgumentException("row and col must be between 0 and N");
    }
    return arr[row][col] == 1;
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    row = row - 1;
    col = col - 1;
    if (!isInputValid(row, col)) {
      throw new IllegalArgumentException("row and col must be between 0 and N");
    }
    return arr[row][col] == 0;
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return open_sites;
  }

  // does the system percolate?
  public boolean percolates() {
    // We want to iterate through the rows using the
    // weighted quick union find algorithm; we will
    // create a virtual site at the top
    for (int i=0;i<arr.length;i++) {
      // Percolate means, we check for each i,j whether the ones
      // around it are open or not
    }

    // Then we will try to find if the bottom row
    // is connected to the top row until
    // we find no more connections or we reach the bottom
  }

  // test client (optional)
  public static void main(String[] args)
}