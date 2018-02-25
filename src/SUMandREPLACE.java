import java.util.*;
import java.io.*;
public class SUMandREPLACE {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int tau[];
    static final int MAX = (int) 1e6;
    
    static  {
        tau = new int[MAX + 1];
        for(int i = 1; i <= MAX; i++)
            for(int j = i; j <= MAX; j+= i)
                tau[j]++;
    }
    

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

        int query(int L, int R) {
            return query(R) - query(L - 1);
        }
    }

    
    private static void solve() {
        
        FasterScanner scan = new FasterScanner();
        
        int n = scan.nextInt();
        int m = scan.nextInt();
        
        int a[] = new int[n + 1];
        for(int i = 1; i <= n; i++)
            a[i] = scan.nextInt();
        
        int ones[] = Arrays.stream(a).map(val -> val == 1 ? 1 : 0).toArray();
        
        for(int i = 1; i <= n; i++)
            ones[i] += ones[i - 1];
        
        FenwickTree twosBIT = new FenwickTree(n);
        for(int i = 1; i <= n; i++) 
            if(a[i] == 2) 
                twosBIT.update(i, 1);   
        
        TreeMap<Integer, Integer> othersMap = new TreeMap<>();
        for(int i = 1; i <= n; i++)
            if(a[i] > 2)
                othersMap.put(i, a[i]);
        
        
        while(m-->0) {
            int type = scan.nextInt();
            int L = scan.nextInt();
            int R = scan.nextInt();
            if(type == 1) { // replace
                Map.Entry<Integer, Integer> curr = othersMap.ceilingEntry(L);
                while(curr != null && curr.getKey() <= R) {
                    if(tau[curr.getValue()] == 2) {
                        othersMap.remove(curr.getKey());
                        twosBIT.update(curr.getKey(), 1);
                    } else 
                        othersMap.replace(curr.getKey(), tau[curr.getValue()]);
                    
                    curr = othersMap.higherEntry(curr.getKey());
                }
                
            } else {
                long sum = (ones[R] - ones[L - 1]) + 2L * twosBIT.query(L, R);
                Map.Entry<Integer, Integer> curr = othersMap.ceilingEntry(L);
                while(curr != null && curr.getKey() <= R) {
                    sum += curr.getValue();
                    curr = othersMap.higherEntry(curr.getKey());
                }
                
                println(sum);
            }
            
        }
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
//        reader.close();
        writer.close();
    }
    static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int tmp_curChar;
        private int tmp_numChars;

        public int read() {
            if (tmp_numChars == -1)
                throw new InputMismatchException();
            if (tmp_curChar >= tmp_numChars) {
                tmp_curChar = 0;
                try {
                    tmp_numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (tmp_numChars <= 0)
                    return -1;
            }
            return buf[tmp_curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

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