package d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import  edu.princeton.cs.introcs.*;

import java.util.NoSuchElementException;

import  edu.princeton.cs.algs4.*;
import edu.princeton.cs.introcs.StdOut;

public class freqWord <Key extends Comparable<Key>, Value>{
	private Node root;             // root of BST
	private int count = 0;

    private class Node {
        private Key key;           // sorted by key
        private int val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, int val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of key-value pairs in BST
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    public void printWords(){
    	printWords(root);
    }
    public void printWords(Node node){
    	if(node == null){
    		return;
    	}
    	if(node.val >= count){
    		StdOut.println(node.key + " :" + node.val);
    	}
    	printWords(node.left);
    	printWords(node.right);
    }

	   /***********************************************************************
	    *  Insert key-value pair into BST
	    *  If key already exists, update with new value
	    ***********************************************************************/
	    public void put(Key key) {
	        root = put(root, key);
	    }

	    private Node put(Node x, Key key) {
	        if (x == null) return new Node(key, 1, 1);
	        int cmp = key.compareTo(x.key);
	        if      (cmp < 0) x.left  = put(x.left,  key);
	        else if (cmp > 0) x.right = put(x.right, key);
	        else{             
	        	x.val   = x.val+1;
	        	if(x.val > count){ count = x.val;}
	        }
	        x.N = 1 + size(x.left) + size(x.right);
	        return x;
	    }

	   /***********************************************************************
	    *  Delete
	    ***********************************************************************/

	    public void deleteMin() {
	        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
	        root = deleteMin(root);
	    }

	    private Node deleteMin(Node x) {
	        if (x.left == null) return x.right;
	        x.left = deleteMin(x.left);
	        x.N = size(x.left) + size(x.right) + 1;
	        return x;
	    }

	    public void delete(Key key) {
	        root = delete(root, key);
	    }

	    private Node delete(Node x, Key key) {
	        if (x == null) return null;
	        int cmp = key.compareTo(x.key);
	        if      (cmp < 0) x.left  = delete(x.left,  key);
	        else if (cmp > 0) x.right = delete(x.right, key);
	        else { 
	            if (x.right == null) return x.left;
	            if (x.left  == null) return x.right;
	            Node t = x;
	            x = min(t.right);
	            x.right = deleteMin(t.right);
	            x.left = t.left;
	        } 
	        x.N = size(x.left) + size(x.right) + 1;
	        return x;
	    } 


	   /***********************************************************************
	    *  Min
	    ***********************************************************************/
	    public Key min() {
	        if (isEmpty()) return null;
	        return min(root).key;
	    } 

	    private Node min(Node x) { 
	        if (x.left == null) return x; 
	        else                return min(x.left); 
	    } 

	
	public static void main(String[] args) {
		int thing = StdIn.readInt();
		
		// TODO Auto-generated method stub
		freqWord<String, Integer> st = new freqWord<String, Integer>();
		BufferedReader reader = null; 
		try { 
			reader = new BufferedReader(new FileReader("tale_of_two_cites.txt")); 
		}
		catch(Exception e) { 
			StdOut.print("could not open file"); 
			System.exit(1); 
		} 


		String line;
		try {
			line = reader.readLine();
			while( line != null) { 
				StringTokenizer tokens = new StringTokenizer(line, " "); 
				while(tokens.hasMoreTokens()) { 
					String word = tokens.nextToken(); 
					//now do whatever you want with the word
					if(word.length() >= thing ){
						st.put(word);;
					}
				} 

			//read next line 
			line = reader.readLine(); 
			} 
			st.printWords();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
