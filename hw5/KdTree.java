package homeworks.hw5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node root;

    public static class Node {
        public Point2D point;
        public boolean isVertical;
        public Node left;
        public Node right;
        public RectHV bounds;

        public Node(Point2D point, boolean isVertical) {
            this.point = point;
            this.isVertical = isVertical;
            this.left = null;
            this.right = null;
            this.bounds = null;
        }
    }

    public KdTree() {
        this.root = new Node(null, true);
    }
    public void insert(Point2D p) {
        // We want to start from the root. We compare the x-coordinate (if the point to be inserted has a smaller x-coordinate
        // than the point at the root, go left; otherwise go right); then, we compare the y-coordinate (if the point to be inserted
        // has a smaller y-coordinate than the point in the node, go left; otherwise go right). Once we reach a null link,
        // we insert the new node there.
        if (root.point == null) {
            root = new Node(p, true);
            root.bounds = new RectHV(0, 0, 1, 1);
            return;
        }
        Node current = root;
        while (current.point != null) {
            if (current.isVertical) {
                if (p.x() < current.point.x()) {
                    if (current.left == null) {
                        current.left = new Node(p, false);
                        current.left.bounds = new RectHV(current.bounds.xmin(), current.bounds.ymin(), current.point.x(), current.bounds.ymax());
                        break;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = new Node(p, false);
                        current.right.bounds = new RectHV(current.point.x(), current.bounds.ymin(), current.bounds.xmax(), current.bounds.ymax());
                        break;
                    } else {
                        current = current.right;
                    }
                }
            } else {
                if (p.y() < current.point.y()) {
                    if (current.left == null) {
                        current.left = new Node(p, true);
                        current.left.bounds = new RectHV(current.bounds.xmin(), current.bounds.ymin(), current.bounds.xmax(), current.point.y());
                        break;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = new Node(p, true);
                        current.right.bounds = new RectHV(current.bounds.xmin(), current.point.y(), current.bounds.xmax(), current.bounds.ymax());
                        break;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
    }

    public void draw() {
        // All points to the left of the root go in the left subtree; all points to the right go in the right subtree.
        // The vertical splits are red and the horizontal splits are blue.; we can use BFS to traverse the tree.
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            if (current.isVertical) {
                // Draw a vertical line from the point to the left of the node to the point to the right of the node.
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(current.point.x(), current.bounds.ymin(), current.point.x(), current.bounds.ymax());
            } else {
                // Draw a horizontal line from the point below the node to the point above the node.
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(current.bounds.xmin(), current.point.y(), current.bounds.xmax(), current.point.y());
            }
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }
    }

    public Point2D nearest(Point2D query) {
        // To find a closest point to a given query point, start at the root and recursively search in both subtrees
        Node closestPoint = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            double distance = current.point.distanceSquaredTo(query);
            if (distance < closestDistance) {
                closestPoint = current;
                closestDistance = distance;
            }
            if (current.left != null && current.left.bounds.distanceSquaredTo(query) < closestDistance) {
                queue.enqueue(current.left);
            }
            if (current.right != null && current.right.bounds.distanceSquaredTo(query) < closestDistance) {
                queue.enqueue(current.right);
            }
        }
        if (closestPoint == null) {
            return null;
        }
        return closestPoint.point;
    }

    public Point2D[] range(RectHV rect) {
        // To find all points contained in a given query rectangle, start at the root and recursively search
        // for points in both subtrees using the following pruning rule: if the query rectangle does not intersect
        // the rectangle corresponding to a node, there is no need to explore that node (or its subtrees).
        // A subtree is searched only if it might contain a point contained in the query rectangle.
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        List<Point2D> matches = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            if (rect.intersects(current.bounds)) {
                if (rect.contains(current.point)) {
                    matches.add(current.point);
                }
                if (current.left != null) {
                    queue.enqueue(current.left);
                }
                if (current.right != null) {
                    queue.enqueue(current.right);
                }
            }
        }
        return matches.toArray(new Point2D[0]);
    }

    public static void main(String[] args) {
        // Unit testing of the methods (not graded).
        // Create a KdTree object and insert some points.
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        // Draw the points to the standard draw.
        kdTree.draw();

        // Test the range() method.
        Point2D[] points = kdTree.range(new RectHV(0.1, 0.1, 0.6, 0.6));
        for (Point2D point : points) {
            System.out.println(point);
        }
        // It should print out (0.2, 0.3), (0.5, 0.4).

        // Test the nearest() method.
        Point2D nearestPoint = kdTree.nearest(new Point2D(0.1, 0.1));
        System.out.println(nearestPoint);
        // It should print out (0.2, 0.3).
    }
}
