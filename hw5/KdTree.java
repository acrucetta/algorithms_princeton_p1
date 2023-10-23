package homeworks.hw5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private class Node {
        Point2D point;
        Node left;
        Node right;
        boolean isVertical;
        RectHV rect;
    }

    public KdTree() {
        Node root = null;
        root.isVertical = true;
        root.left = null;
        root.right = null;
        root.rect = new RectHV(0, 0, 1, 1);
    }
    public void insert(Point2D p) {
    }

    public void draw() {
    }

    public KdTree nearest(Point2D query) {
        return null;
    }

    public Point2D[] range(RectHV rect) {
        return null;
    }
}
