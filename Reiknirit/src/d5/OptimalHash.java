package d5;

import edu.princeton.cs.introcs.StdOut;

public class OptimalHash {
	
	// Function that generates hash
	public static int hashFun(int a, int M, int k){
		int hash = (a * k) % M;
		return hash;
	}
	
	public static void main(String[] args) { 
		String[] strengur = "S E A R C H X M P L".split(" ");
		int N = strengur.length;
		
		// Run through all possible m
		for(int m = 1; true; m++){
			char[] fylki = new char[m];
			// Run through all possible a
			outerloop:
			for(int a = 1; a <= m-1. ; a++){
				
				// Run through input
				for(int i = 0; i < N; i++){
					
					// Get value for char
					int k = (int) strengur[i].charAt(0);
					int holf = hashFun(a, m, k);
					
					// Check for collision
					if(fylki[holf] == '\u0000' || fylki[holf] == strengur[i].charAt(0)){
						// Check if we are at the end of input
						if(i == (N - 1)){
							StdOut.println("a = " + a + " and M = " + m);
							return;
						}
						// Insert input char to array
						fylki[holf] = strengur[i].charAt(0);
					}
					else{
						// Break because of collision
						break outerloop;
					}
				}
			}
		}
    }
}
