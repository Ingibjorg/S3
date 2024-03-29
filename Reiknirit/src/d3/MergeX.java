package d3;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;


/**
 *  The <tt>MergeX</tt> class provides static methods for sorting an
 *  array using an optimized version of mergesort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/22mergesort">Section 2.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class MergeX {
    private static final int CUTOFF = 7;  // cutoff to insertion sort

    // This class should not be instantiated.
    private MergeX() { }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {

        // precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];   // to ensure stability
            else                           dst[k] = src[i++];
        }

        // postcondition: dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi, int N) {
        // if (hi <= lo) return;
        if (hi <= lo + N) { 
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid, N);
        sort(dst, src, mid+1, hi, N);

         if (!less(src[mid+1], src[mid])) {
            for (int i = lo; i <= hi; i++) dst[i] = src[i];
            return;
         }

        // using System.arraycopy() is a bit faster than the above loop
       // if (!less(src[mid+1], src[mid])) {
         //   System.arraycopy(src, lo, dst, lo, hi - lo + 1);
           // return;
        //}

        merge(src, dst, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a, int N) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1, N);  
        assert isSorted(a);
    }


    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }


    // exchange a[i] and a[j]
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is a[i] < a[j]?
    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
    	String[] a = new String[1000000];
    	String[] b = new String[1000000];
    	for(int i = 0; i < 1000000; i++){
    		a[i] = String.valueOf(StdRandom.uniform());
    		b[i] = a[i];
    	}
    	double tim = 0;
    	StdOut.printf("%6s %s", "N", "Time");
    	StdOut.println();
    	for(int i = 0; i <= 100; i++){
    		for(int k = 0; k < 10; k++){
    			Stopwatch timer = new Stopwatch();
    			MergeX.sort(a, i);
    			tim += timer.elapsedTime();
    			for(int j = 0; j < 1000000; j++){
            		a[j] = b[j];
            	}
    		}
    		StdOut.printf("%6d & %7.4f  \n", i, tim/10);
    		tim = 0;
    	}
    }
}
