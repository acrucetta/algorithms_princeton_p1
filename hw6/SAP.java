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
        int shortestPath = Integer.MAX_VALUE;

        boolean[] visitedV = new boolean[digraph.V()];
        Queue<Integer> queueV = new LinkedList<>();

        boolean[] visitedW = new boolean[digraph.V()];
        Queue<Integer> queueW = new LinkedList<>();

        queueV.add(v);
        queueW.add(w);

        int distanceV = 0;
        int distanceW = 0;

        while (!queueV.isEmpty() || !queueW.isEmpty()) {
            if (!queueV.isEmpty()) {
                int vertexV = queueV.remove();
                visitedV[vertexV] = true;
                distanceV++;
                for (int neighbor : digraph.adj(vertexV)) {
                    if (!visitedV[neighbor]) {
                        queueV.add(neighbor);
                    }
                }
            }
            if (!queueW.isEmpty()) {
                int vertexW = queueW.remove();
                visitedW[vertexW] = true;
                distanceW++;
                for (int neighbor : digraph.adj(vertexW)) {
                    if (!visitedW[neighbor]) {
                        queueW.add(neighbor);
                    }
                }
            }
    }
        return shortestPath;
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