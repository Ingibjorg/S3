package d0;

import edu.princeton.cs.introcs.StdDraw;

public class Drawing {

	public static void main(String[] args) {
		//set background color
		StdDraw.clear(StdDraw.LIGHT_GRAY);
		//Pen size
		StdDraw.setPenRadius(0.2);
		StdDraw.line(0, 1, 1, 0);
		
		//set color
		StdDraw.setPenColor(StdDraw.GRAY);
		
		//filled objects
		StdDraw.filledRectangle(0.5, 0.85, 0.1, 0.1);
		StdDraw.filledCircle(0.5, 0.5, 0.2);
		StdDraw.filledEllipse(0.5, 0.2, 0.2, 0.05);
		//set color
		StdDraw.setPenColor(StdDraw.YELLOW);
		
		//not filled objects
		StdDraw.setPenRadius(0.002);
		StdDraw.rectangle(0.5, 0.5, 0.1, 0.1);
		//Pen size
		StdDraw.setPenRadius(0.01);
		StdDraw.circle(0.5, 0.5, 0.5);
		StdDraw.ellipse(0.5, 0.5, 0.5, 0.2);
		
		//Draw Text
		StdDraw.text(0.5, 0.55, "Hello");
		StdDraw.text(0.5, 0.45, "World!");
		
	}
}
