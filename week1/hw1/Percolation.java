import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private int[][] arr;
  private int openSites = 0;
  private final WeightedQuickUnionUF uf;
  private final int topVirtualSite;
  private final int bottomVirtualSite;

  private boolean validate(int row, int col) {
    if (row < 1 || row > arr.length || col < 1 || col > arr.length) {
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
    this.arr = new int[n][n];
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
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    if (!validate(row, col)) {
      throw new IllegalArgumentException("row and col must be between 1 and " + arr.length);
    }
    row = row - 1;
    col = col - 1;

    if (arr[row][col] == 0) {
      arr[row][col] = 1;
      openSites++;
    }
    // Now we will connect the site to the ones around it
    // We will check if the site is open and then connect it
    int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    for (int[] direction : directions) {
      int neighborRow = row + direction[0];
      int neighborCol = col + direction[1];
      if (!validate(neighborRow + 1, neighborCol + 1)) {
        continue;
      }
      if (arr[neighborRow][neighborCol] == 1) {
        uf.union(row * arr.length + col, neighborRow * arr.length + neighborCol);
      }
    }

    // Now we will connect the site to the virtual sites
    // If the site is in the top row, we will connect it to the top virtual site
    if (row == 0) {
      uf.union(row * arr.length + col, topVirtualSite);
    }

    // If the site is in the bottom row, we will connect it to the bottom virtual
    // site
    if (row == arr.length - 1) {
      uf.union(row * arr.length + col, bottomVirtualSite);
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (!validate(row, col)) {
      throw new IllegalArgumentException("row and col must be between 1 and " + arr.length);
    }
    row = row - 1;
    col = col - 1;
    return arr[row][col] == 1;
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (!validate(row, col)) {
      throw new IllegalArgumentException("row and col must be between 1 and " + arr.length);
    }
    row = row - 1;
    col = col - 1;
    return arr[row][col] == 1 && uf.find(row * arr.length + col) == uf.find(topVirtualSite);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openSites;
  }

  // does the system percolate?
  public boolean percolates() {
    // A system percolates if there's a full site in the bottom row
    return uf.find(topVirtualSite) == uf.find(bottomVirtualSite);
  }

  // test client (optional)
  public static void main(String[] args) {
  }
}