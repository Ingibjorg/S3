package d4;

import  edu.princeton.cs.introcs.*;
import  edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

public class BST_D4<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
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


   /**********************************************************************
    * Assignment part.
    * TODO: Implement
    *       [ ] avgCompares
    *       [ ] nLeaves
    *       [ ] floor
    *********************************************************************/

    public int avgCompares(){
    	
        //FjoldiNoda
    	int N = root.N;
    	
    	//Internal Path lengdin
    	int IPL = internalPathLength(root, 0);
    	
    	//Formula fyrir avarage compares
        return  (IPL /  N) + 1;
    }
    
  private int internalPathLength(Node node, int length ){
	  
	  //Skila 0 i enda tres
	  if(node == null){
	      return 0;
	  }
	  
	  //lengd i vinstra tre
	  int leftLength = internalPathLength(node.left, length + 1);
	  
	  //lengd i haegra tre
	  int rightLength = internalPathLength(node.right, length + 1);
	  
	  //Alls lengd i tre
	  return (length + leftLength + rightLength);
  }

    public int nLeaves()
    {
        //Kall á recursive fall
        return nLeaves(root);
    }
    private int nLeaves(Node node){

    	// Nodan er null
    	if(node == null){
    		return 0;
    	}
        
    	// Nodan er lauf
    	if(node.left == null && node.right == null){
    		return 1;
    	}
        
    	// Skila fjolda laufa i vinstri og haegri trjam undir node
        return nLeaves(node.left) + nLeaves(node.right);
    }
    public Key floor(Key key) {

        // Finna staersa lykil sem er minni eda jafn key
    	boolean found = false;
    	Node node = root;
    	Key floorKey = null;
        
        // Renna i gegnum tre i leit ad lykli
    	while(!found){
        
        	//Kominn i enda tres
    		if(node == null){ 
    			found = true;
    			break;
    		}
    		int cmpr = key.compareTo(node.key);
            
            //node.key er jafn key
    		if(cmpr == 0){ 
    			found = true;
    			floorKey = key;
    		}       
    		else if(cmpr > 0 ){ // key er staerri
    			floorKey = node.key;
                
                // ekkert i haegra legg
    			if(node.right == null){ 
    				found = true;
    			}
    			else{ // fer i haegri legg
    				node = node.right;
    			}
    		} 
    		else{ // key er minni
    			node = node.left;
    		}
    		
    	}
        return floorKey;
    } 



   /***********************************************************************
    *  Insert key-value pair into BST
    *  If key already exists, update with new value
    ***********************************************************************/
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
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

   /*****************************************************************************
    *  Test client
    *****************************************************************************/
    public static void test_floor() {
        /**
         *  Each line in the test case starts with some operation, either "F"
         *  for "floor" and "P" for "put". For "floor", a key of type string
         *  will follow which will be the parameter to a function call to
         *  floor.  For "put", a key of type string and value of type int will
         *  follow which then are inserted into the tree.
         */
        BST_D4<String, Integer> st = new BST_D4<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String operation = StdIn.readString();
            String key = StdIn.readString();
            if (operation.equals("F")){
                StdOut.printf("Calling floor(\"%s\") = \"%s\"\n", key, st.floor(key));
            } else {
                int value = StdIn.readInt();
                st.put(key, value);
                StdOut.printf("Inserting node with key=\"%s\" and value=%d\n", key, value);
            }
        }

        StdOut.println();

        StdOut.printf("avgCompares %d\n", st.avgCompares());
    }

    public static void test_nLeaves() {
        /**
         * Each line in the test case consists of a key of type string and
         * value of type int that are inserted into the tree.
         */
        BST_D4<String, Integer> st = new BST_D4<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            int value = StdIn.readInt();
            st.put(key, value);
            StdOut.printf("Inserting node with key=\"%s\" and value=%d\n", key, value);
        }

        StdOut.println();

        StdOut.printf("nLeaves %d\n", st.nLeaves());
    }

    public static void test_avgCompares() {
        /**
         * Each line in the test case consits of a key of type string and value
         * of type int that are inserted into the tree.
         */
        BST_D4<String, Integer> st = new BST_D4<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            int value = StdIn.readInt();
            st.put(key, value);
            StdOut.printf("Inserting node with key=\"%s\" and value=%d\n", key, value);
        }

        StdOut.println();

        StdOut.printf("avgCompares %d\n", st.avgCompares());
    }

    public static void main(String[] args) {
        /**
         * The test client reads in the name of the function to test, which can
         * be one of "avgCompares", "nLeaves", "floor".
         */
        String test_name = StdIn.readString();
        if("floor".equals(test_name)){
            test_floor();
        } else if("avgCompares".equals(test_name)){
            test_avgCompares();
        } else if("nLeaves".equals(test_name)){
            test_nLeaves();
        }
    }
}
