package homeworks.hw3;

import javax.sound.sampled.Line;

public class BruteCollinearPoints {

  public int segments = 0;
  public LineSegment[] lineSegments;

  public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
  {
    if (points == null) {
      throw new IllegalArgumentException("points cannot be null");
    }

    // We want to iterate over the array every 4 points
    for (int i = 0; i < points.length; i += 4) {
      Point p1 = points[i];
      if (p1.slopeTo(points[i]) == p1.slopeTo(points[i + 1])
          && p1.slopeTo(points[i]) == p1.slopeTo(points[i + 2])
          && p1.slopeTo(points[i]) == p1.slopeTo(points[i + 3])) {
        LineSegment lineSegment = new LineSegment(p1, points[i + 3]);
        lineSegments[segments] = lineSegment;
        segments++;
      }
    }
  }

  public int numberOfSegments() // the number of line segments
  {
    return segments;
  }

  public LineSegment[] segments() // the line segments
  {
    return lineSegments;
  }
}