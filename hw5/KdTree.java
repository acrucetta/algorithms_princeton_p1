package homeworks.hw5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private Node root;

    public static class Node {
        public Point2D point;
        public boolean isVertical;
        public Node left;
        public Node right;

        public Node(Point2D point) {
            this.point = point;
            this.isVertical = true;
            this.left = null;
            this.right = null;
        }
    }

    public KdTree() {
        this.root = new Node(null);
    }
    public void insert(Point2D p) {
        // We want to start from the root. We compare the x-coordinate (if the point to be inserted has a smaller x-coordinate
        // than the point at the root, go left; otherwise go right); then, we compare the y-coordinate (if the point to be inserted
        // has a smaller y-coordinate than the point in the node, go left; otherwise go right). Once we reach a null link,
        // we insert the new node there.
        Node newNode = new Node(p);
        newNode.point = p;

        if (root == null) {
            root = newNode;
        }
        Node current = root;
        while (current.point != null) {
            if (current.isVertical) {
                if (p.x() < current.point.x()) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    } else {
                        current = current.right;
                    }
                }
            } else {
                if (p.y() < current.point.y()) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = newNode;
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
                current.point.drawTo(new Point2D(current.point.x(), current.point.y() + 1));
            } else {
                // Draw a horizontal line from the point below the node to the point above the node.
                current.point.drawTo(new Point2D(current.point.x() + 1, current.point.y()));
            }
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }
    }

    public KdTree nearest(Point2D query) {
        return null;
    }

    public Point2D[] range(RectHV rect) {
        return null;
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
    }
}
