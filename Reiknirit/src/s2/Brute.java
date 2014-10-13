package s2;

import java.util.Arrays;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Brute {
	
	public static void slopeCheck(Point[] pointArray, int N){
		for(int i = 0; i < N; ++i){
			// Sæki punkt p úr fylkinu
			Point p = pointArray[i];
			for(int j = i + 1; j < N; ++j){
				// Sæki punkt q úrfylkinu
				Point q = pointArray[j];
				for(int k = j + 1; k < N; ++k){
					// Sæki punkt r úr fylkinu
					Point r = pointArray[k];
					
					// Finn hallatölu punktanna
					double pslopeq = p.slopeTo(q);
					double psloper = p.slopeTo(r);
					
					// Ef hallatalan er sú sama
					if(pslopeq == psloper){
						for(int x = k + 1; x < N; ++x){
							// Sæki punkt s úr fylkinu
							Point s = pointArray[x];
							
							// Finn hallatöluna
							double pslopes = p.slopeTo(s);
							
							// Ef hallatalan er sú sama prentast út línu hlutinn.
							if(pslopeq == pslopes){
								//StdOut.println(p + " -> " +  q + " -> " +  r + " -> " + s);
							}
						}
					}
				}
			}
		}
	}
	
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
