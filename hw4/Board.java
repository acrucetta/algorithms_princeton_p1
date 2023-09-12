import java.lang.reflect.Array;

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
                if (tile[j] != currentTile) {
                    score++;
                }
                // If we're at the last tile, we
                // want to check the tile is 0
                if (currentTile == n * n && tile[j] != 0) {
                    score++;
                }
                currentTile++;
            }
        return score;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
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
    }

}