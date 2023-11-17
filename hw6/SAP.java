package homeworks.hw6;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SAP {

    // constructor takes a digraph (not necessarily a DAG)
    private final Digraph digraph;

    public SAP(Digraph G) {
        this.digraph = G;
    }

    private static class BFSResult {
        private int ancestor;
        private int length;
    }

    private BFSResult bfs(int v, int w) {
        if (v < 0 || v >= digraph.V() || w < 0 || w >= digraph.V()) {
            throw new IllegalArgumentException();
        }
        if (v == w) {
            BFSResult result = new BFSResult();
            result.ancestor = v;
            result.length = 0;
            return result;
        }

        boolean[] visitedV = new boolean[digraph.V()];
        Queue<Integer> queueV = new LinkedList<>();

        boolean[] visitedW = new boolean[digraph.V()];
        Queue<Integer> queueW = new LinkedList<>();

        queueV.add(v);
        queueW.add(w);

        int shortestPath = Integer.MAX_VALUE;
        int shortestAncestor = -1;

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
                        shortestAncestor = i;
                    }
                }
            }
        }

        BFSResult result = new BFSResult();
        result.ancestor = shortestAncestor;
        result.length = (shortestPath == Integer.MAX_VALUE) ? -1 : shortestPath;
        return result;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    // to find the shortest ancestral path between v and w, we will
    // run a breadth-first search from v and w simultaneously.
    public int length(int v, int w) {
        BFSResult result = bfs(v, w);
        return result.length;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BFSResult result = bfs(v, w);
        return result.ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        List<int[]> visitedPairs = new ArrayList<>();
        int shortestPath = Integer.MAX_VALUE;

        for (int vertexV : v) {
            for (int vertexW : w) {
                if (vertexV == vertexW) {
                    int distance = 0;
                    if (distance < shortestPath) {
                        shortestPath = distance;
                    }
                }
                if (visitedPairs.contains(new int[]{vertexV, vertexW})) {
                    continue;
                }
                visitedPairs.add(new int[]{vertexV, vertexW});
                visitedPairs.add(new int[]{vertexW, vertexV});
                int distance = length(vertexV, vertexW);
                if (distance < shortestPath) {
                    shortestPath = distance;
                }
            }
        }
        return shortestPath;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        List<int[]> visitedPairs = new ArrayList<>();
        int shortestPath = Integer.MAX_VALUE;
        int shortestAncestor = -1;

        for (int vertexV : v) {
            for (int vertexW : w) {
                if (vertexV == vertexW) {
                    int distance = 0;
                    if (distance < shortestPath) {
                        shortestPath = distance;
                        shortestAncestor = vertexV;
                    }
                }
                if (visitedPairs.contains(new int[]{vertexV, vertexW})) {
                    continue;
                }
                visitedPairs.add(new int[]{vertexV, vertexW});
                visitedPairs.add(new int[]{vertexW, vertexV});
                int distance = length(vertexV, vertexW);
                int ancestorVW = ancestor(vertexV, vertexW);
                if (distance < shortestPath) {
                    shortestPath = distance;
                    shortestAncestor = ancestorVW;
                }
            }
        }
        return shortestAncestor;
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