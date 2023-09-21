import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {

    public int[][] Tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        Tiles = tiles;
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(Tiles.length).append("\n");
        for (int[] tile : Tiles) {
            for (int j = 0; j < Tiles.length; j++) {
                s.append(tile[j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return Tiles.length;
    }

    // number of tiles out of place
    // This is calculated by noting what's the value
    // of each tile and comparing it to the goal state.
    // Assuming the goal state is an ordered array of
    // numbers from 1 to n^2
    public int hamming() {
        int score = 0;
        int n = Tiles.length;
        int currentTile = 1;
        for (int[] tile : Tiles)
            for (int j = 0; j < Tiles.length; j++) {
                if (currentTile == n * n) { // Last tile should be 0
                    if (tile[j] != 0) {
                        score++;
                    }
                    break;
                }
                if (tile[j] != currentTile) { // Tile is not in the right place
                    score++;
                }
                currentTile++;
            }
        return score;
    }

    // sum of Manhattan distances between tiles and goal
    // The manhattan distance is calculated with
    // the sum of the vertical and horizontal distance
    public int manhattan() {
        int score = 0;
        int n = Tiles.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < Tiles.length; j++) {
                int currentTile = Tiles[i][j];
                if (currentTile == 0) { // Ignore the blank tile
                    continue;
                }
                int goalRow = (currentTile - 1) / n;
                int goalCol = (currentTile - 1) % n;
                score += Math.abs(goalRow - i) + Math.abs(goalCol - j);
            }
        return score;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    // We want to compare the tiles of the board
    // one by one
    public boolean equals(Object y) {
        int n = Tiles.length;
        if (y == null || y.getClass() != this.getClass() || ((Board) y).dimension() != n) {
            return false;
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < Tiles.length; j++) {
                if (Tiles[i][j] != ((Board) y).Tiles[i][j]) {
                    return false;
                }
            }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighborList = new ArrayList<>();

        // We want to swap the current 0 to the directions allowed;
        // then we add them to the neighborList
        int[] zeroCoordinates = getZeroCoordinates();
        assert zeroCoordinates != null;
        int zeroRow = zeroCoordinates[0];
        int zeroCol = zeroCoordinates[1];

        // Based on the position of the zero, we can swap it
        // to the following directions
        if (zeroRow > 0) {
            neighborList.add(swap(-1, 0));
        }
        if (zeroRow < Tiles.length - 1) {
            neighborList.add(swap(1, 0));
        }
        if (zeroCol > 0) {
            neighborList.add(swap(0, -1));
        }
        if (zeroCol < Tiles.length - 1) {
            neighborList.add(swap(0, 1));
        }
        return neighborList;
    }

    // Swap to the given direction,
    // we will use i and j coordinates
    private Board swap(int i, int j) {
        // Copy current board
        int[][] copyTiles = deepCopy(Tiles);
        int[] zeroCoordinates = getZeroCoordinates();
        assert zeroCoordinates != null;
        int zeroRow = zeroCoordinates[0];
        int zeroCol = zeroCoordinates[1];

        // Swap the zero with the given direction
        int temp = copyTiles[zeroRow][zeroCol];
        copyTiles[zeroRow][zeroCol] = copyTiles[zeroRow + i][zeroCol + j];
        copyTiles[zeroRow + i][zeroCol + j] = temp;
        return new Board(copyTiles);
    }

    private int[] getZeroCoordinates() {
        int n = Tiles.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < Tiles.length; j++) {
                if (Tiles[i][j] == 0) {
                    return new int[] {i, j};
                }
            }
        return null;
    }

    private int[][] deepCopy(int[][] tiles) {
        int[][] copy = new int[Tiles.length][Tiles.length];
        for (int i = 0; i < Tiles.length; i++) {
            copy[i] = Tiles[i].clone();
        }
        return copy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] clonedBoard = deepCopy(Tiles);

        // Get two tiles that are not 0 and swap them
        int i = 0; int j = 0;
        while (clonedBoard[i][j] == 0 || clonedBoard[i][j + 1] == 0) {
            j++;
            if (j == Tiles.length - 1) {
                i++;
                j = 0;
            }
        }
        int temp = clonedBoard[i][j];
        clonedBoard[i][j] = clonedBoard[i][j + 1];
        clonedBoard[i][j + 1] = temp;
        return new Board(clonedBoard);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = new int[3][3];
        int currentTile = 1;
        for (int[] tile : tiles)
            for (int j = 0; j < tiles.length; j++) {
                tile[j] = currentTile;
                currentTile++;
            }
        tiles[2][2] = 0;
        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
        System.out.println(board.isGoal());

        // Test neighbors
        System.out.println("Neighbors:");
        for (Board neighbor : board.neighbors()) {
            System.out.println(neighbor);
        }

        // Test twin
        System.out.println("Twin:");
        System.out.println(board.twin());
    }

}