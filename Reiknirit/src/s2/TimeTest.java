package s2;

import java.util.Arrays;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class TimeTest {

	public static void main(String[] args) {
		StdOut.println("N       brute       sorting"); 
		StdOut.println("---------------------------------"); 
	      
		int[] N = {150, 200, 300, 400, 800, 1600, 3200, 6400, 12800};
		for(int i = 0; i < N.length; i++){
			Point[] Points = new Point[N[i]];
			for(int j = 0; j < N[i]; j++){
				Points[j] = new Point(StdRandom.uniform(N[i]), StdRandom.uniform(N[i]));
			}
			Arrays.sort(Points);
			double bruteTime;
			double fastTime;
			Stopwatch timer = new Stopwatch();
			Brute.slopeCheck(Points, N[i]);
			bruteTime = timer.elapsedTime();
			timer = new Stopwatch();
			Fast.slopeCheck(Points, N[i]);
			fastTime = timer.elapsedTime();
			StdOut.printf("%6d %7.7f %5.7f\n", N[i], bruteTime, fastTime);
		}
	}

}
