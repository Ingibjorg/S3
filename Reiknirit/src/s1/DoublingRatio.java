package s1;


import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;


/*************************************************************************
 *  Compilation:  javac DoublingRatio.java
 *  Execution:    java DoublingRatio
 *  Dependencies: ThreeSum.java Stopwatch.java StdRandom.java StdOut.java
 *
 *
 *  % java DoublingRatio
 *      250   0.0    2.7
 *      500   0.0    4.8
 *     1000   0.1    6.9
 *     2000   0.6    7.7
 *     4000   4.5    8.0
 *     8000  35.7    8.0
 *  ...
 *
 *************************************************************************/

/**
 *  The <tt>DoublingRatio</tt> class provides a client for measuring
 *  the running time of a method using a doubling ratio test.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class DoublingRatio {

    // This class should not be instantiated.
    private DoublingRatio() { }

    
    private static int K(int N, int N_0){
    	return (int) Math.floor(0.5 * N * Math.log(N_0));
    }

    public static double QuickUnionTEST(int N, int N_0) {
    	QuickUnionUF qu = new QuickUnionUF(N);
    	int k = K(N, N_0);
    	Stopwatch timer = new Stopwatch();
        for (int i = 0; i < k; i++) {
            int u = StdRandom.uniform(N);
            int v = StdRandom.uniform(N);
            qu.union(u,v);
        }
        return timer.elapsedTime();
    }
    
    public static double QuickFindTEST(int N, int N_0) {
    	QuickFindUF qu = new QuickFindUF(N);
    	int k = K(N, N_0);
    	Stopwatch timer = new Stopwatch();
        for (int i = 0; i < k; i++) {
            int u = StdRandom.uniform(N);
            int v = StdRandom.uniform(N);
            qu.union(u,v);
        }
        return timer.elapsedTime();
    }
    
    public static double WeightedQuickUnionTEST(int N, int N_0) {
    	WeightedQuickUnionUF qu = new WeightedQuickUnionUF(N);
    	int k = K(N, N_0);
    	Stopwatch timer = new Stopwatch();
        for (int i = 0; i < k; i++) {
            int u = StdRandom.uniform(N);
            int v = StdRandom.uniform(N);
            qu.union(u,v);
        }
        return timer.elapsedTime();
    }
    
    public static void main(String[] args) {  
    	int T = 6; 
    	int N_0 = 1000;
    	int MAX = 32000;
    	double[][] QUArray = new double[6][3];
    	double[][] QFArray = new double[6][3];
    	double[][] WQUArray = new double[6][3];
    	for(int i = 0; i < 6; i++){
    		for(int j = 0; j < 3; j++){
    			QUArray[i][j] = 0.0;
    			QFArray[i][j] = 0.0;
    			WQUArray[i][j] = 0.0;
    		}
    	}
    	
    	for(int i = 0; i < T; i++){
    		int teljari = 0;
    		double prevQU = QuickUnionTEST(N_0, N_0);
    		double prevQF = QuickFindTEST(N_0, N_0);
    		double prevWQU = WeightedQuickUnionTEST(N_0, N_0);
    		for (int N = N_0; N <= MAX; N += N) {
    			double timeQU = QuickUnionTEST(N, N_0);
    			double timeQF = QuickFindTEST(N, N_0);
    			double timeWQU = WeightedQuickUnionTEST(N, N_0);
    			
    			QUArray[teljari][0] = N;
    			QUArray[teljari][1] += timeQU;
    			QUArray[teljari][2] += prevQU;
    			
    			QFArray[teljari][0] = N;
    			QFArray[teljari][1] += timeQF;
    			QFArray[teljari][2] += prevQF;
    			
    			WQUArray[teljari][0] = N;
    			WQUArray[teljari][1] += timeWQU;
    			WQUArray[teljari][2] += prevWQU;
    			
    			prevQU = timeQU;
    			prevQF = timeQF;
    			prevWQU = timeWQU;
    			
    			teljari++;
    		} 
    	}
    	StdOut.println("QuickFind:");
    	for(int i = 0; i < 6; i++){
    		StdOut.printf("%6.0f %7.7f %5.7f\n", QFArray[i][0], QFArray[i][1]/T, QFArray[i][1]/QFArray[i][2]);
    	}   
    	StdOut.println("QuickUnion:");
    	for(int i = 0; i < 6; i++){
    		StdOut.printf("%6.0f %7.7f %5.7f\n", QUArray[i][0], QUArray[i][1]/T, QUArray[i][1]/QUArray[i][2]);
    	} 
    	StdOut.println("Weighted QuickUnion:");
    	for(int i = 0; i < 6; i++){
    		StdOut.printf("%6.0f %7.7f %5.7f\n", WQUArray[i][0], WQUArray[i][1]/T, WQUArray[i][1]/WQUArray[i][2]);
    	} 


    } 
} 