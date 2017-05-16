import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;


/**
 * Generator which creates a test where Java 7 dual-pivot quicksort algorithm runs in O(n^2) time.
 * 
 * Number of operations is not the best possible:
 * maximal recursion depth is about n^2 / 5 while best possible result is n^2 / 4.
 * 
 * It's because Java 7 checks if array is nearly sorted.
 * If it is, a strange algorithm with something called 'runs' is used.
 * In our case it is not, but in process of this checking Java 7 swaps some elements.
 * I didn't figure out how to maintain these swaps yet. Feel free to improve it!
 * 
 * @author Alexey Dergunov
 * @since  1.7
 */
public class TestCaseGen implements Runnable {

    private final Random rnd = new Random(239);
    
    private final int INSERTION_SORT_THRESHOLD = 47;
//    private final int MAX_RUN_LENGTH = 33;
//    private final int QUICKSORT_THRESHOLD = 286;
//    private final int MAX_RUN_COUNT = 67;
    
    private int MIN_VALUE;
    private int MAX_VALUE;
    private final int NO_VALUE = -1;
    
    private void hackedSort(int[] a, int[] p, int left, int right, boolean leftmost) {
        int length = right - left + 1;
        
        // Use insertion sort on tiny arrays
        if (length < INSERTION_SORT_THRESHOLD) {
            for (int i = right; i >= left; i--) {
                if (a[i] == NO_VALUE) a[i] = MIN_VALUE++;
            }
            randomShuffle(a, left, right); // why not?

            if (leftmost) {
                /*
                 * Traditional (without sentinel) insertion sort,
                 * optimized for server VM, is used in case of
                 * the leftmost part.
                 */
                for (int i = left, j = i; i < right; j = ++i) {
                    int ai = a[i + 1];
                    int pi = p[i + 1];
                    while (ai < a[j]) {
                        a[j + 1] = a[j];
                        p[j + 1] = p[j];
                        if (j-- == left) {
                            break;
                        }
                    }
                    a[j + 1] = ai;
                    p[j + 1] = pi;
                }
            } else {
                /*
                 * Skip the longest ascending sequence.
                 */
                do {
                    if (left >= right) {
                        return;
                    }
                } while (a[++left] >= a[left - 1]);

                for (int k = left; ++left <= right; k = ++left) {
                    int a1 = a[k], a2 = a[left];
                    int p1 = p[k], p2 = p[left];

                    if (a1 < a2) {
                        a2 = a1; a1 = a[left];
                        p2 = p1; p1 = p[left];
                    }
                    while (a1 < a[--k]) {
                        a[k + 2] = a[k];
                        p[k + 2] = p[k];
                    }
                    ++k;
                    a[k + 1] = a1;
                    p[k + 1] = p1;

                    while (a2 < a[--k]) {
                        a[k + 1] = a[k];
                        p[k + 1] = p[k];
                    }
                    a[k + 1] = a2;
                    p[k + 1] = p2;
                }
                int last = a[right];
                int plast = p[right];

                while (last < a[--right]) {
                    a[right + 1] = a[right];
                    p[right + 1] = p[right];
                }
                a[right + 1] = last;
                p[right + 1] = plast;
            }
            return;
        }

        // Inexpensive approximation of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;


        int e3 = (left + right) >>> 1; // The midpoint
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;
        
        if (a[e5] == NO_VALUE) a[e5] = MIN_VALUE++;
        if (a[e4] == NO_VALUE) a[e4] = MIN_VALUE++;
        
        if (a[e1] == NO_VALUE) a[e1] = MAX_VALUE--;
        if (a[e2] == NO_VALUE) a[e2] = MAX_VALUE--;

        // Sort these elements using insertion sort
        if (less(a[e2], a[e1])) { int t = a[e2]; a[e2] = a[e1]; a[e1] = t;
                                  int s = p[e2]; p[e2] = p[e1]; p[e1] = s; }

        if (less(a[e3], a[e2])) { int t = a[e3]; a[e3] = a[e2]; a[e2] = t;
                                  int s = p[e3]; p[e3] = p[e2]; p[e2] = s;
            if (less(t, a[e1])) { a[e2] = a[e1]; a[e1] = t;
                                  p[e2] = p[e1]; p[e1] = s; }
        }
        if (less(a[e4], a[e3])) { int t = a[e4]; a[e4] = a[e3]; a[e3] = t;
                                  int s = p[e4]; p[e4] = p[e3]; p[e3] = s;
            if (less(t, a[e2])) { a[e3] = a[e2]; a[e2] = t;
                                  p[e3] = p[e2]; p[e2] = s;
                if (less(t, a[e1])) { a[e2] = a[e1]; a[e1] = t;
                                      p[e2] = p[e1]; p[e1] = s; }
            }
        }
        if (less(a[e5], a[e4])) { int t = a[e5]; a[e5] = a[e4]; a[e4] = t;
                                  int s = p[e5]; p[e5] = p[e4]; p[e4] = s;
            if (less(t, a[e3])) { a[e4] = a[e3]; a[e3] = t;
                                  p[e4] = p[e3]; p[e3] = s;
                if (less(t, a[e2])) { a[e3] = a[e2]; a[e2] = t;
                                      p[e3] = p[e2]; p[e2] = s;
                    if (less(t, a[e1])) { a[e2] = a[e1]; a[e1] = t;
                                          p[e2] = p[e1]; p[e1] = s; }
                }
            }
        }

        // Pointers
        int less  = left;  // The index of the first element of center part
        int great = right; // The index before the first element of right part

        if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5]) {

            int pivot1 = a[e2];
            int pivot2 = a[e4];
            int ppivot1 = p[e2];
            int ppivot2 = p[e4];

            a[e2] = a[left];
            a[e4] = a[right];
            p[e2] = p[left];
            p[e4] = p[right];

            /*
             * Skip elements, which are less or greater than pivot values.
             */
            //while (a[++less] < pivot1);
            //while (a[--great] > pivot2);
            while (less(a[++less], pivot1));
            while (greater(a[--great], pivot2));

            outer:
            for (int k = less - 1; ++k <= great; ) {
                int ak = a[k];
                int pk = p[k];
                //if (ak < pivot1) { // Move a[k] to left part
                if (less(ak, pivot1)) { // Move a[k] to left part
                    a[k] = a[less];
                    p[k] = p[less];
                    /*
                     * Here and below we use "a[i] = b; i++;" instead
                     * of "a[i++] = b;" due to performance issue.
                     */
                    a[less] = ak;
                    p[less] = pk;
                    ++less;
                //} else if (ak > pivot2) { // Move a[k] to right part
                } else if (greater(ak, pivot2)) { // Move a[k] to right part
                    //while (a[great] > pivot2) {
                    while (greater(a[great], pivot2)) {
                        if (great-- == k) {
                            break outer;
                        }
                    }
                    //if (a[great] < pivot1) { // a[great] <= pivot2
                    if (less(a[great], pivot1)) { // a[great] <= pivot2
                        a[k] = a[less];
                        p[k] = p[less];
                        a[less] = a[great];
                        p[less] = p[great];
                        ++less;
                    } else { // pivot1 <= a[great] <= pivot2
                        a[k] = a[great];
                        p[k] = p[great];
                    }
                    /*
                     * Here and below we use "a[i] = b; i--;" instead
                     * of "a[i--] = b;" due to performance issue.
                     */
                    a[great] = ak;
                    p[great] = pk;
                    --great;
                }
            }

            // Swap pivots into their final positions
            a[left]  = a[less  - 1]; a[less  - 1] = pivot1;
            a[right] = a[great + 1]; a[great + 1] = pivot2;
            p[left]  = p[less  - 1]; p[less  - 1] = ppivot1;
            p[right] = p[great + 1]; p[great + 1] = ppivot2;
            

            // Sort left and right parts recursively, excluding known pivots
            hackedSort(a, p, left, less - 2, leftmost);
            hackedSort(a, p, great + 2, right, false);

            /*
             * If center part is too large (comprises > 4/7 of the array),
             * swap internal pivot values to ends.
             */
            if (less < e1 && e5 < great) {
                /*
                 * Skip elements, which are equal to pivot values.
                 */
                while (a[less] == pivot1) {
                    throw new RuntimeException("We should not enter here!");
//                    ++less;
                }

                while (a[great] == pivot2) {
                    throw new RuntimeException("We should not enter here!");
//                    --great;
                }

                outer:
                for (int k = less - 1; ++k <= great; ) {
                    int ak = a[k];
                    int pk = p[k];
                    if (ak == pivot1) { // Move a[k] to left part
                        a[k] = a[less];
                        p[k] = p[less];
                        a[less] = ak;
                        p[less] = pk;
                        ++less;
                    } else if (ak == pivot2) { // Move a[k] to right part
                        while (a[great] == pivot2) {
                            if (great-- == k) {
                                break outer;
                            }
                        }
                        if (a[great] == pivot1) { // a[great] < pivot2
                            a[k] = a[less];
                            p[k] = p[less];
                            a[less] = pivot1;
                            p[less] = ppivot1;
                            ++less;
                        } else { // pivot1 < a[great] < pivot2
                            a[k] = a[great];
                            p[k] = p[great];
                        }
                        a[great] = ak;
                        p[great] = pk;
                        --great;
                    }
                }
            }

            // Sort center part recursively
            hackedSort(a, p, less, great, false);

        } else { // Partitioning with one pivot
            throw new RuntimeException("We should not enter here!");            
        }
    }
    
    private void randomShuffle(int[] a, int left, int right) {
        for (int i = left; i <= right; i++) {
            int j = left + rnd.nextInt(i - left + 1);
            swap(a, i, j);
        }
    }
    
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private boolean less(int a, int b) {
        if (a != NO_VALUE && b != NO_VALUE) {
            return a < b;
        }
        if (a == NO_VALUE) {
            return b > MAX_VALUE;
        }
        if (b == NO_VALUE) {
            return a < MIN_VALUE;
        }
        throw new RuntimeException("We should not enter here!");
    }
    
    private boolean greater(int a, int b) {
        if (a != NO_VALUE && b != NO_VALUE) {
            return a > b;
        }
        if (a == NO_VALUE) {
            return b < MIN_VALUE;
        }
        if (b == NO_VALUE) {
            return a > MAX_VALUE;
        }
        throw new RuntimeException("We should not enter here!");
    }
    
    public void run() {
        int n = (int)(7.34e4);       // n = 40000 smallest length which breaks the 1sec limit for TLE
        
        int[] a = new int[n];
        int[] p = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = NO_VALUE;
            p[i] = i;
        }
        MIN_VALUE = 1;
        MAX_VALUE = n;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(/*new BufferedWriter(new OutputStreamWriter(System.out)), false */ "hack.txt"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		

        long t1, t2;
        
        t1 = System.nanoTime();
        hackedSort(a, p, 0, n-1, true);
        // checkValues(a, 1, n);
        // checkValues(p, 0, n-1);
        
        applyPermutation(a, p);
        
		int usb = n;
		int ps2 = /*(int) (1e5)*/ 0;
		int other = 0;
		
		int m = usb + ps2;
		pw.println(usb + " " + ps2 + " " + other);
		pw.println(m);
		
		for(int num : a) pw.println(num + " USB");
		
		pw.close();
		t2 = System.nanoTime();
		System.out.println("Generation time = " + (t2 - t1)/1e6 + " ms.");
		
        t1 = System.nanoTime();
        Arrays.sort(a);
        // a = Arrays.stream(a.clone()).parallel().sorted().toArray();
        t2 = System.nanoTime();
        System.out.println("Sorting time = " + (t2 - t1)/1e6 + " ms.");
		
      /*  PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
        printArray(a, out);
        */
/*        try {
            printArray(a, new PrintWriter("JavaSortKillerSmall.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       
        
        t1 = System.nanoTime();
        Arrays.sort(a);
        // a = Arrays.stream(a.clone()).parallel().sorted().toArray();
        t2 = System.nanoTime();
        System.out.println("Sorting time = " + (t2 - t1)/1e6 + " ms.");*/
    }
    
    private void applyPermutation(int[] a, int[] pos) {
        int n = a.length;
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            tmp[pos[i]] = a[i];
        }
        for (int i = 0; i < n; i++) {
            a[i] = tmp[i];
            pos[i] = i;
        }
    }

    private void checkValues(int[] a, int min, int max) {
        boolean[] b = new boolean[max - min + 1];
        for (int x : a) {
            if (b[x - min]) {
                throw new RuntimeException();
            }
            b[x - min] = true;
        }
    }

    private void printArray(int[] a, PrintWriter pw) {
        int n = a.length;
        pw.println(n);
        for (int i = 0; i < n; i++) {
            pw.print(a[i]);
            if (i == n-1) pw.println(); else pw.print(' ');
        }
        pw.close();
    }
    
    public static void main(String[] args) {
        new Thread(null, new TestCaseGen(), "", 128*1024*1024).start();
    }

}