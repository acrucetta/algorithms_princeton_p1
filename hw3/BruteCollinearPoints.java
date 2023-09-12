import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {

  private final List<LineSegment> lineSegments = new ArrayList<>();

  public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
  {
    if (points == null) {
      throw new IllegalArgumentException("points cannot be null");
    }
    if (checkNulls(points)) {
      throw new IllegalArgumentException("points cannot contain null values");
    }

    // We want to check every combination of 4 points in the array;
    // we can do this by iterating through the array 4 times, each time
    // starting at a different index
    for (int i = 0; i < points.length; i += 1) {
      for (int j = i + 1; j < points.length; j += 1) {
        for (int k = j + 1; k < points.length; k += 1) {
          for (int l = k + 1; l < points.length; l += 1) {
            System.out.println("i: " + i + ", j: " + j + ", k: " + k + ", l: " + l);
            // Ensure we don't go out of bounds
            if (l >= points.length) {
              break;
            }
            // Check if the points are collinear
            if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
              // If they are, add them to the lineSegments array
              lineSegments.add(new LineSegment(points[i], points[l]));
            }
          }
        }
      }
    }
  }

  private boolean checkNulls(Point[] points) {
    for (int i = 0; i < points.length; i += 1) {
      if (points[i] == null) {
        return true;
      }
    }
    return false;
  }

  public int numberOfSegments() // the number of line segments
  {
    return lineSegments.size();
  }

  public LineSegment[] segments() // the line segments
  {
    return lineSegments.toArray(new LineSegment[lineSegments.size()]);
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