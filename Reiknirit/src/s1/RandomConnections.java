package s1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class RandomConnections {
	
	public static boolean areAllTrue(boolean[] array)
	{
	    for(boolean b : array) if(!b) return false;
	    return true;
	}

	public static Connection[] genConnections(int N){
		ArrayList<Connection> RndCon = new ArrayList<Connection>();
		boolean[] AllConnections = new boolean[N];
		boolean allGood = true;
		while(allGood){
			int p = StdRandom.uniform(N);
			int q = StdRandom.uniform(N);
			if(p == q){
				continue;
			}
			RndCon.add(new Connection(p,q));
			AllConnections[p] = true;
			if(areAllTrue(AllConnections)){
				break;
			}
		}
		Connection[] output = new Connection[RndCon.size()];
		for(int i = 0; i < RndCon.size(); i++){
			output[i] = RndCon.get(i);
		}
		return output;
	}
	
	public static Connection[] genGridConnections(int M){
        if(M == 1){
                Connection[] RndCon = new Connection[1];
                RndCon[0] = new Connection(0,0);
                return RndCon;
        }
        int size = M*M;
        Connection[] RndCon = new Connection[size];
        WeightedQuickUnionUF WQU = new WeightedQuickUnionUF(size);

        int teljari = 0;
        while(WQU.count() > 1){
                int p = StdRandom.uniform(size);
                int q = StdRandom.uniform(size);

                if(!WQU.connected(p, q)){
                        Connection conn = new Connection (p, q);
                        if((p - q) == 1){
                                if((q + 1) % M != 0){
                                        RndCon[teljari++] = conn;
                                        WQU.union(p, q);
                                }

                        }
                        else if((q - p) == 1){
                                if((p + 1) % M != 0){
                                        RndCon[teljari++] = conn;
                                        WQU.union(p, q);
                                }
                        }
                        else if((p - q) == M || (p - q) == -M){
                                RndCon[teljari++] = conn;
                                WQU.union(p, q);
                        }
                }
        }
        RndCon[size-1] = new Connection (0, 0);
        return RndCon;
}
	
	
public static void main(String[] args) {
	Connection[] rnd = genGridConnections(2);
	for(int i = 0; i < rnd.length; i++){
		StdOut.println(rnd[i].p + " " + rnd[i].q);
	}
}

}
