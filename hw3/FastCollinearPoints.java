import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastCollinearPoints {

  private final List<LineSegment> lineSegments = new ArrayList<>();

  public FastCollinearPoints(Point[] points) // finds all line segments containing 4 or more points
  {
    if (points == null) {
      throw new IllegalArgumentException();
    }
    for (Point origin : points) {
      if (origin == null) {
        throw new IllegalArgumentException();
      }
      // We think of P as the origin.
      // For each other point q, determine the slope it makes with p.
      HashMap<Double, List<Point>> slopes = new HashMap<>();
      for (Point q : points) {
        if (q == null || q == origin) {
          continue;
        }
        double slope = origin.slopeTo(q);
        slopes.computeIfAbsent(slope, k -> new ArrayList<>()).add(q);
      }
      // Check if any slope has more than 3 points.
      for (Map.Entry<Double, List<Point>> entry : slopes.entrySet()) {
        List<Point> collinearPoints = entry.getValue();
        if (collinearPoints.size() >= 3) {
          collinearPoints.add(origin);
          collinearPoints.sort(Point::compareTo);
          Point min = collinearPoints.get(0);
          Point max = collinearPoints.get(collinearPoints.size() - 1);
          if (min == origin) {
            lineSegments.add(new LineSegment(min, max));
          }
        }
      }
    }
  }

  public int numberOfSegments() // the number of line segments
  {
    return lineSegments.size();
  }

  public LineSegment[] segments() // the line segments
  {
    return lineSegments.toArray(new LineSegment[0]);
  }

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
