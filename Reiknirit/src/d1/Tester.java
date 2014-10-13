package d1;

import edu.princeton.cs.introcs.StdIn;

public class Tester {
	
	public static void main(String[] args)
	{
		LinkedList list = new LinkedList();
		int n = StdIn.readInt();
		for(int i = 0; i < n; ++i)
		{
			String str = StdIn.readString();
			list.add(str);
		}
		while(!StdIn.isEmpty())
		{
			String remove = StdIn.readString();
	
			list.remove(remove);
			list.print();
		}
	}
}
