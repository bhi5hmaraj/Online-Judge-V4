import java.util.*;
import java.io.*;
public class TreeRequests  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static ArrayList<int[]>[] queries;
    static ArrayList<Integer>[] map;
    
    static char alph[];
    static boolean ans[];
    static int depth[];
    
    static void preprocess(int u) {
        depth[u] = 1;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(u);
        while(!queue.isEmpty()) {
            int curr = queue.remove();
            for(int v : adj[curr]) {
                depth[v] = depth[curr] + 1;
                queue.add(v);
            }
        }
    }
    
    static void dfs(int u) {
        
        if(marked[u])
            throw new RuntimeException("Cycle found");
        
        marked[u] = true;
        
        int maxPos = adj[u].size() > 0 ? adj[u].get(0) : -1;
        for(int i = 0; i < adj[u].size(); i++) {
            int v = adj[u].get(i);
            dfs(v);
            maxPos = map[v].size() > map[maxPos].size() ? v : maxPos;
        }
        
        
        for(int i = 0; i < adj[u].size(); i++) {
            int v = adj[u].get(i);
            int offset = map[maxPos].size() - map[v].size();
            if(v != maxPos) 
                for(int j = 0; j < map[v].size(); j++)
                    map[maxPos].set(offset + j, map[maxPos].get(offset + j) ^ map[v].get(j));
        }
        map[u] = maxPos < 0 ? new ArrayList<>() : map[maxPos];
        map[u].add(1 << (alph[u] - 'a'));

        sizeCnt++;
        if(map[u].size() == 0)
            newARL++;    
        
        for(int q[] : queries[u]) 
            ans[q[1]] = !(q[0] >= depth[u] && q[0] < depth[u] + map[u].size()) || 
                        Integer.bitCount(map[u].get(map[u].size() - (q[0] - depth[u]) - 1)) <= 1;
        
    }
    
    static int MAX = (int) 5e5 ;
    static boolean flag = false;
    static boolean marked[];
    static int sizeCnt = 0 , newARL = 0;
    
    private static void solve() {
        
        FasterScanner scan = new FasterScanner();
        
        int V = scan.nextInt();
        int Q = scan.nextInt();
        queries = new ArrayList[V];
        adj = new ArrayList[V];
        map = new ArrayList[V];
        marked = new boolean[V];
        
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            queries[i] = new ArrayList<>();
        }

        for(int i = 1; i < V; i++) 
            adj[scan.nextInt() - 1].add(i);
        
        alph = scan.nextLine().toCharArray();
        ans = new boolean[Q];
        depth = new int[V];
        
        preprocess(0);
        
        while(Q-->0)
            queries[scan.nextInt() - 1].add(new int[]{scan.nextInt() , Q});   // height , qNo

        dfs(0);
        for(int i = ans.length - 1; i >= 0; i--)
            println(ans[i] ? "Yes" : "No");
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new TreeRequests().run();
            }
        }, "Increase Stack", 1 << 27).start();

    }

    void run(){ 
        /*
         * You failed me fast scanner :(
         */
        // reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        // st     = null;
        solve();
        // reader.close();
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

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
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

        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }

    /************************ TEMPLATE ENDS HERE ************************/
    
}