import java.util.*;
import java.io.*;
public class StringManipulation1 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class FenwickTree {

        /****************
         * DONT USE BIT IF YOU UPDATE INDEX 0 (causes infinite loop)
         ******************/

        int tree[];
        int len;

        FenwickTree(int len) {
            this.len = len;
            tree = new int[len + 10];
        }

        void update(int idx, int val) {
            idx++;
            if (idx == 0)
                throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for (; idx <= len; idx += (idx & -idx))
                tree[idx] += val;
        }

        int query(int idx) {
            idx++;
            int sum = 0;
            for (; idx > 0; idx -= (idx & -idx))
                sum += tree[idx];

            return sum;
        }

        int query(int L, int R) {
            return query(R) - query(L - 1);
        }
    }
    
    static final int DELETE = (int) 1e8;
    
    private static void solve() {
        
        
        int k = nextInt();
        char str[] = nextLine().toCharArray();
        int freq[] = new int[26];
        for(char ch : str)
            freq[ch - 'a']++;

        int positions[][] = new int[26][];
        for(int i = 0; i < 26; i++) {
            positions[i] = new int[freq[i] * k];
            int ptr = 0;
            for(int p = 0; p < str.length; p++)
                if(str[p] == 'a' + i) {
                    for(int q = 0; q < k; q++)
                        positions[i][q * freq[i] + ptr] = q * str.length + p;
                    ptr++;
                }
        }
         
        FenwickTree BIT[] = new FenwickTree[26];
        for(int i = 0; i < 26; i++)
            if(freq[i] > 0)
                BIT[i] = new FenwickTree(freq[i] * k);
        
        int Q = nextInt();
        while(Q-->0) {
            int p = nextInt() - 1;
            int c = nextChar() - 'a';
            FenwickTree curr = BIT[c];
            int lo = 0 , hi = freq[c] * k - 1;
            int toDelete = -1;
            while(lo <= hi) {
                int mid = (lo + hi) >> 1;
                int val = mid - curr.query(mid);
                if(p <= val) {
                    toDelete = mid;
                    hi = mid - 1;
                }
                else
                    lo = mid + 1;
            }
            positions[c][toDelete] = DELETE;
            curr.update(toDelete, 1);
        }
        
        ArrayList<int[]> remaining = new ArrayList<>();
        for(int i = 0; i < 26; i++)
            for(int p : positions[i])
                if(p != DELETE)
                    remaining.add(new int[]{p , i});
        
        Collections.sort(remaining , (p1 , p2) -> p1[0] - p2[0]);
        
        for(int p[] : remaining)
            print((char) ('a' + p[1]));
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    
    static BufferedReader reader;
    static PrintWriter    writer;
    static StringTokenizer st;
    
    static String next()
    {while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
    st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
    static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
    static int    nextInt()   {return Integer.parseInt(next());}
    static long   nextLong()  {return Long.parseLong(next());}     
    static double nextDouble(){return Double.parseDouble(next());}
    static char   nextChar()  {return next().charAt(0);}
    static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
    static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
    static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
    static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
    static void   print(Object o)  { writer.print(o);  }
    static void   println(Object o){ writer.println(o);}
    
    /************************ TEMPLATE ENDS HERE ************************/
    
}