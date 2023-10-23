package homeworks.hw5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {

    SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        Queue<Point2D> queue = new Queue<>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                queue.enqueue(point);
            }
        }
        return queue;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Point2D closestPoint = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (Point2D point : points) {
            double distance = point.distanceTo(p);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // Initialize a new PointSET
        PointSET pointSET = new PointSET();

        // Insert some points
        pointSET.insert(new Point2D(0.1, 0.1));
        pointSET.insert(new Point2D(0.2, 0.2));
        pointSET.insert(new Point2D(0.3, 0.3));
        pointSET.insert(new Point2D(0.4, 0.4));
        pointSET.insert(new Point2D(0.5, 0.5));

        // Check if the set is empty
        System.out.println("Is the set empty? " + pointSET.isEmpty());

        // Check the size of the set
        System.out.println("The size of the set is " + pointSET.size());

        // Check if the set contains a point
        System.out.println("Does the set contain (0.1, 0.1)? " + pointSET.contains(new Point2D(0.1, 0.1)));

        // Draw the set
        pointSET.draw();

        // Get all points in a rectangle
        RectHV rect = new RectHV(0.1, 0.1, 0.3, 0.3);
        System.out.println("Points in the rectangle " + rect.toString() + ":");
        for (Point2D point : pointSET.range(rect)) {
            System.out.println(point.toString());
        }

        // Get the nearest point to a point
        Point2D point = new Point2D(0.1, 0.1);
        System.out.println("The nearest point to " + point.toString() + " is " + pointSET.nearest(point).toString());
    }
}