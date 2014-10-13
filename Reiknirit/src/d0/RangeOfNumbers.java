package d0;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class RangeOfNumbers {

	public static void main(String[] args) {
		//define variables
		int maxNum;
		int minNum;
		//read number of elements
		int n = StdIn.readInt();
		//read n integers
		int[] list = new int[n];
		for(int i = 0; i < n; ++i){
			list[i] = StdIn.readInt();
		}
		//order first  two elements
		if(list[0] < list[1]){
			maxNum = list[1];
			minNum = list[0];
		}
		else{
			maxNum = list[0];
			minNum = list[1];
		}
		//Find higher or lower numbers
		for(int i = 2; i < n; ++i){
			if(maxNum < list[i]){
				maxNum = list[i];
			}
			else if(list[i] < minNum){
				minNum = list[i];
			}
		}
		//output difference 
		StdOut.print(maxNum - minNum);
        StdOut.println();
		
	}

}
