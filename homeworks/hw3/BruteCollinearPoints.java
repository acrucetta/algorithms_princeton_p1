package homeworks.hw3;

public class BruteCollinearPoints {

  public int segments = 0;
  public LineSegment[] lineSegments;

  public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
  {
    if (points == null) {
      throw new IllegalArgumentException("points cannot be null");
    }

    // Intialize the lineSegments array
    lineSegments = new LineSegment[points.length];

    // We want to iterate over the array every 4 points
    for (int i = 0; i < points.length; i += 4) {
      Point p1 = points[i];
      System.out.println("p1: " + p1);
      System.out.println("Current i: " + i);
      if (p1.slopeTo(points[i + 1]) == p1.slopeTo(points[i + 2])
          && p1.slopeTo(points[i + 1]) == p1.slopeTo(points[i + 3])) {
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

  public static void main(String[] args) {
    // Test the BruteCollinearPoints class
    Point[] points = new Point[8];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);
    points[4] = new Point(5, 5);
    points[5] = new Point(6, 6);
    points[6] = new Point(7, 7);
    points[7] = new Point(8, 8);
    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    System.out.println("Number of segments: " + bruteCollinearPoints.numberOfSegments());
    System.out.println("Segments: " + bruteCollinearPoints.segments());
  }
}