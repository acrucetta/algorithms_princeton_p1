package homeworks.hw6;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

public class SAP {

    // constructor takes a digraph (not necessarily a DAG)
    private final Digraph digraph;

    public SAP(Digraph G) {
        this.digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    // to find the shortest ancestral path between v and w, we will
    // run a breadth-first search from v and w simultaneously.
    // We will keep track of the shortest ancestral path found so far.
    public int length(int v, int w) {

        boolean[] visitedV = new boolean[digraph.V()];
        Queue<Integer> queueV = new LinkedList<>();

        boolean[] visitedW = new boolean[digraph.V()];
        Queue<Integer> queueW = new LinkedList<>();

        queueV.add(v);
        queueW.add(w);

        int shortestPath = Integer.MAX_VALUE;
        int[] distanceV = new int[digraph.V()];
        int[] distanceW = new int[digraph.V()];

        visitedW[w] = true;
        visitedV[v] = true;

        while (!queueV.isEmpty() || !queueW.isEmpty()) {

            // BFS from v
            if (!queueV.isEmpty()) {
                int vertexV = queueV.remove();
                for (int neighbor : digraph.adj(vertexV)) {
                    if (!visitedV[neighbor]) {
                        queueV.add(neighbor);
                        visitedV[neighbor] = true;
                        distanceV[neighbor] = distanceV[vertexV] + 1;
                    }
                }
            }

            // BFS from w
            if (!queueW.isEmpty()) {
                int vertexW = queueW.remove();
                for (int neighbor : digraph.adj(vertexW)) {
                    if (!visitedW[neighbor]) {
                        queueW.add(neighbor);
                        visitedW[neighbor] = true;
                        distanceW[neighbor] = distanceW[vertexW] + 1;
                    }
                }
            }

            // check if we have found a common ancestor
            for (int i=0; i<digraph.V(); i++) {
                if (visitedV[i] && visitedW[i]) {
                    int distance = distanceV[i] + distanceW[i];
                    if (distance < shortestPath) {
                        shortestPath = distance;
                    }
                }
            }
    }
        return shortestPath == Integer.MAX_VALUE ? -1 : shortestPath;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return -1;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}