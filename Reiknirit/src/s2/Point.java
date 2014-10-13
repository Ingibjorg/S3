package s2;

import java.util.Comparator;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdDraw;

/*************************************************************************
 * Compilation: javac Point.java Execution: Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 * @author Magnus M. Halldorsson, email: mmh@ru.is
 *************************************************************************/
public class Point implements Comparable<Point> {

    public final int x, y;

    // compare points by slope
    //The SLOPE_ORDER comparator should compare points by the slopes they make with the 
    //invoking point (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2)
    //if and only if the slope (y1 − y0)/(x1 − x0) is less than the slope (y2 − y0)/(x2 − x0). 
    //Treat horizontal, vertical, and degenerate line segments as in the slopeT o() method.
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>(){
    		    @Override
    		    public int compare(Point a, Point b)
    		    {
    		        double slopeA = slopeTo(a);
    		        double slopeB = slopeTo(b);
    		        return Double.compare(slopeA, slopeB);
    		    }
    };
    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        // TODO: Implement this
    	//The slopeTo() method should return the slope between the invoking point (x0, y0) 
    	//and the argument point (x1, y1), which is given by the formula (y1 − y0)/(x1 − x0). 
    	//Treat the slope of a horizontal line segment as positive zero; treat the slope of a 
    	//vertical line segment as positive infinity; treat the slope of a degenerate line
    	//segment (between a point and itself) as negative infinity.
    	if(this.y == that.y && this.x != that.x){return 0.0;}
    	if(this.x == that.x && this.y != that.y){return Double.POSITIVE_INFINITY;}
    	if(this.x == that.x && this.y == that.y){return Double.NEGATIVE_INFINITY;}
        return ((double) that.y - this.y) / (that.x - this.x);
    }

    /**
     * Is this point lexicographically smaller than that one? comparing
     * y-coordinates and breaking ties by x-coordinates
     */
    public int compareTo(Point that) {
        // TODO: Implement this
    	//The compareTo() method should compare points by their y-coordinates, breaking 
    	//ties by their xcoordinates. Formally, the invoking point (x0, y0) is less than
    	//the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
    	if(this.y < that.y){
    		return -1;
    	}
    	if(this.y == that.y && this.x < that.x){
    		return -1;
    	}
    	if(this.y == that.y && this.x == that.x){
    		return 0;
    	}
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    

    public static void main(String[] args) {
        /*
         * Do not modify
         */
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        out.printf("Testing slopeTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].slopeTo(points[i - 1]));
        }
        out.printf("Testing compareTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].compareTo(points[i - 1]));
        }
        out.printf("Testing SLOPE_ORDER comparator...\n");
        for (int i = 2; i < points.length; i++) {
            out.println(points[i].SLOPE_ORDER.compare(points[i - 1],
                    points[i - 2]));
        }
    }
}
