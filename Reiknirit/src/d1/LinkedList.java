package d1;

import edu.princeton.cs.introcs.*;

public class LinkedList {
	
	private Node front;
	private int N;
	
	private class Node
	{
		String key;
		Node next;
	}

	/**
	 * Constructor
	 */
	public LinkedList()
	{
		N = 0;
		front = null;
	}
	
	/**
	 * Pushes the item to the back of the list.
	 */
	public void add(String str)
	{
		Node node = new Node();
		node.key = str;
		node.next = front;
		front = node;
	}
	
	public void print()
	{
		Node iter = front;
		while( iter != null )
		{
			StdOut.print(iter.key);
			iter = iter.next;
			if( iter != null )
			{
				StdOut.print(" ");
			}
		}
		StdOut.println();
	}
	
	/**
	 * TODO: Implement
	 */
	public void remove(String str)
	{
		//Call recursive helper function
		front = remove_helper(str, front);
	}
	private Node remove_helper(String str, Node node)
	{
		//check if we have gone out of the list
		if(node == null){return null;}
		//Check if we find current node has str
		if(str.equals(node.key)){
			//jump over it
			return remove_helper(str, node.next);
		}
		//nothing found go to next node
		node.next = remove_helper(str, node.next);
		//and return this node
		return node;
	}
}
