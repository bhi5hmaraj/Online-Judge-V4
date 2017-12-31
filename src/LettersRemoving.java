import java.util.*;
import java.io.*;
public class LettersRemoving {
    
    
    
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
            if (idx == 0)
                throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for (; idx <= len; idx += (idx & -idx))
                tree[idx] += val;
        }

        int query(int idx) {
            int sum = 0;
            for (; idx > 0; idx -= (idx & -idx))
                sum += tree[idx];

            return sum;
        }
        
        void upd(int L , int R) {
            update(L, 1);
            update(R + 1, -1);
        }
        
    }
    
    static FenwickTree BIT;
    static int n;
    static int get(int idx) {
        int L = 1 , R = n;
        int ret = -1;
        while(L <= R) {
            int M = (L + R) >> 1;
            if(idx <= M - BIT.query(M)) {
                R = M - 1;
                ret = M;
            }
            else 
                L = M + 1;
        }
        
        return ret;
    }
    
    private static void solve() {
        
        n = nextInt();
        int q = nextInt();
        
        char str[] = nextLine().toCharArray();
        TreeSet<Integer>[] positions = new TreeSet[128];
        for(int i = 0; i < 128; i++)
            positions[i] = new TreeSet<>();
        
        for(int i = 0; i < n; i++)
            positions[str[i]].add(i + 1);
        
        BIT = new FenwickTree(n + 1);
        
        while(q-->0) {
            int l = get(nextInt());
            int r = get(nextInt());
            char ch = nextChar();
            Integer curr = positions[ch].ceiling(l);
            while(curr != null && curr <= r) {
                BIT.upd(curr, n);
                positions[ch].remove(curr);
                curr = positions[ch].ceiling(curr);
            }
        }
        
        ArrayList<int[]> remaining = new ArrayList<>();
        for(int i = 0; i < 128; i++) 
            for(int pos : positions[i])
                remaining.add(new int[]{pos , i});
        
        Collections.sort(remaining, (p1 , p2) -> p1[0] - p2[0]);
        remaining.forEach(p -> print((char)p[1]));
        
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