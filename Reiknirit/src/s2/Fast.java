package s2;

import java.util.Arrays;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Fast {
	
	public static Point[] readPoints(int N){
		int x, y;
		Point[] array = new Point[N];
		// Les inn stök og set þau í fylki
		for(int i = 0; i < N; ++i){
			x = StdIn.readInt();
			y = StdIn.readInt();
			array[i] = new Point(x, y);
		}
		return array;
	}

	public static void printLine(Point[] pointArray, Point p, int start, int end){
		// Prenta út punkta í línu sem eru afmarkaðir 
		// frá start til end í fylkinu pointArray
		StdOut.print(p);
		for(int i = start; i <= end; ++i){
			StdOut.print(" -> " + pointArray[i]);
		}
		StdOut.println();
	}

	private static Point[] sortPointArray(Point[] pointArray, Point p, int start, int N){
		// Skila sorteruðu fylki út frá 
		// hallatölu staka við punktinn p
		Point[] sortedPointArray = new Point[N];
		for(int i = 0; i < N; ++i){
			sortedPointArray[i] = pointArray[i];
		}
		Arrays.sort(sortedPointArray, start, N, p.SLOPE_ORDER);
		return sortedPointArray;
	}

	public static void slopeCheck(Point[] pointArray, int N){
		for(int i = 0; i < N; ++i){
			// Sæki punkt p úr fylkinu
			Point p = pointArray[i];
			
			// Fæ fylki með sorteruðum punktum 
			Point[] sortedPointArray = sortPointArray(pointArray, p, i, N);
			
			int start = 0, end = 0;
			double prevSlope = p.slopeTo(sortedPointArray[i]);
			
			for(int j = i + 1; j < N; ++j){
				double currSlope = p.slopeTo(sortedPointArray[j]);
				// Ef fyrri hallatala er ekki sú sama og núverandi
				if(prevSlope != currSlope){
					// Ef punktarnir eru 3 eða fleiri
					if(1 < (end - start)){
						//printLine(sortedPointArray, p, start, end);    
					}
					// Endurstilli punkta bilið
					start = j;
					end = j;
					prevSlope = currSlope;
				} 
				else{
					// Stækka punkta bilið
					end++;
				}
			}
			// Ef punktarnir eru 3 eða fleiri
			if(1 < (end - start)){
				//printLine(sortedPointArray, p, start, end);
			}
		}
	}

	public static void main(String[] args) {
		// Fjöldi punkta
		int N = StdIn.readInt();
		
		// Kalla á fall sem skilar array af punktum
		Point[] Points = readPoints(N);
		
		// Sortera punktunum
		Arrays.sort(Points);
		
		// Kalla á fall sem skoðar hallatölu punktanna
		slopeCheck(Points, N);
	}
}