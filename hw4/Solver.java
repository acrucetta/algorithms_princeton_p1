import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.PriorityQueue;

public class Solver {

    // find a solution to the initial board (using the A* algorithm)
    private MinPQ<SearchNode> pq;
    private Board initialBoard;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final SearchNode previous;
        private final int priority;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = board.manhattan() + moves;
        }

        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        // First, we insert the initial board, 0 moves, and a null previous search node into a priority queue.
        pq = new MinPQ<SearchNode>();
        initialBoard = initial;
        pq.insert(new SearchNode(initial, 0, null));

        // Then we delete from the priority queue the board with the minimum priority,
        // and insert onto the priority queue all neighboring search nodes
        while (!pq.isEmpty()) {
            SearchNode min = pq.delMin();
            if (min.board.isGoal()) {
                break;
            }
            for (Board neighbor : min.board.neighbors()) {
                if (min.previous == null || !neighbor.equals(min.previous.board)) {
                    pq.insert(new SearchNode(neighbor, min.moves + 1, min));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        // We will run the algorithm above on two puzzle instances—one with the initial board
        // and one with the initial board modified by swapping a pair of tiles (the blank square is not a tile).
        // Exactly one of the two will lead to the goal board.
        Solver firstInstance = new Solver(initialBoard);
        Board twinBoard = initialBoard.twin();
        Solver secondInstance = new Solver(twinBoard);
        return firstInstance.moves() != -1;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}