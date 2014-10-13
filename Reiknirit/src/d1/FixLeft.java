package d1;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class FixLeft {

	public static void main(String[] args) {
		//Create a Stack for strings
		Stack<String> equation = new Stack<String>();
		while (!StdIn.isEmpty()){
			//read input
			String s = StdIn.readString();
			//run code to fix parenthesis if ")" is found
			if(s.equals(")")){
				String num2 = equation.pop();
				String oper = equation.pop();
				String num1 = equation.pop();
				String fix = "( " + num1 +" " + oper + " " + num2 + " " + s;
				equation.push(fix);
			}
			//push input to stack
			else{
				equation.push(s);
			}
		}
		//print out the fixed equation
		StdOut.print(equation.pop());
		StdOut.println();
	}

}
