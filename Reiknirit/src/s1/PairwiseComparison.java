package s1;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdOut;

public class PairwiseComparison {
	
	 public static double QuickFindTEST(int N, Connection[] conns) {
	    	QuickFindUF qf = new QuickFindUF(N*N);
	    	Stopwatch timer = new Stopwatch();
	    	for(int i = 0; i < conns.length; i++){
	    		qf.union(conns[i].p, conns[i].q);
	    	}
	        return timer.elapsedTime();
	    }
	 
	 public static double QuickUnionTEST(int N, Connection[] conns) {
	    	QuickUnionUF qu = new QuickUnionUF(N*N);
	    	Stopwatch timer = new Stopwatch();
	    	for(int i = 0; i < conns.length; i++){
	    		qu.union(conns[i].p, conns[i].q);
	    	}
	        return timer.elapsedTime();
	    }
	 public static double WeightedQuickUnionTEST(int N, Connection[] conns) {
	    	WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(N*N);
	    	Stopwatch timer = new Stopwatch();
	    	for(int i = 0; i < conns.length; i++){
	    		wqu.union(conns[i].p, conns[i].q);
	    	}
	        return timer.elapsedTime();
	    }
	 public static double WeightedQuickUnionPCTEST(int N, Connection[] conns) {
	    	WeightedQuickUnionPathCompressionUF wqupc = new WeightedQuickUnionPathCompressionUF(N*N);
	    	Stopwatch timer = new Stopwatch();
	    	for(int i = 0; i < conns.length; i++){
	    		wqupc.union(conns[i].p, conns[i].q);
	    	}
	        return timer.elapsedTime();
	    }
	 public static double WeightedQuickUnionBHTEST(int N, Connection[] conns) {
	    	WeightedQuickUnionByHeightUF wqubh = new WeightedQuickUnionByHeightUF(N*N);
	    	Stopwatch timer = new Stopwatch();
	    	for(int i = 0; i < conns.length; i++){
	    		wqubh.union(conns[i].p, conns[i].q);
	    	}
	        return timer.elapsedTime();
	    }
	
	
	public static void main(String[] args) {
		for(int N = 10; N <= 4000000; N *= 2 ){
			double QFQUratio = 0.0;
			double WQUWQUPCratio = 0.0;
			double HQUWQUratio = 0.0;
			for(int T = 0; T < 10; T++){
				Connection[] conns = RandomConnections.genGridConnections(N);
			
				double QFtime = QuickFindTEST(N, conns);
				double QUtime = QuickUnionTEST(N, conns);
				double WQUtime = WeightedQuickUnionTEST(N, conns);
				double WQUPCtime = WeightedQuickUnionPCTEST(N, conns);
				double HQUtime = WeightedQuickUnionBHTEST(N, conns);
				StdOut.println(QFtime +" | " +QUtime +" | " +WQUtime +" | " +WQUPCtime +" | " + HQUtime);
				QFQUratio += QUtime / QFtime;
				WQUWQUPCratio += WQUPCtime / WQUtime;
				HQUWQUratio += HQUtime / WQUtime;
			}
			StdOut.print("N :" + N + " QFQUratio: " + QFQUratio/10 + 
					" WQUWQUPCratio: " + WQUWQUPCratio/10 + " HQUWQUratio: " + HQUWQUratio/10);
			StdOut.println();
		}
	}
}
