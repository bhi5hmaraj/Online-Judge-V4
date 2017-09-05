import java.util.*;
import java.io.*;
public class LA_5108 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static double pt[][];
    
    static double areaOfTri(int a , int b , int c) {
        double t1 = pt[a][0] * (pt[b][1] - pt[c][1]);
        double t2 = pt[b][0] * (pt[c][1] - pt[a][1]);
        double t3 = pt[c][0] * (pt[a][1] - pt[b][1]);
        return Math.abs((t1 + t2 + t3) / 2.0);
    }
    
    static int BLOCK_SIZE;
    
    static class Query implements Comparable<Query> {
        int L , R;
        int id;
        public Query(int l, int r, int id) {
            L = l;
            R = r;
            this.id = id;
        }
        @Override
        public String toString() {
            return String.format("[L = %d R = %d]", L , R);
        }
        @Override
        public int compareTo(Query o) {
            int b1 = L / BLOCK_SIZE;
            int b2 = o.L / BLOCK_SIZE;
            if(b1 != b2)
                return b1 - b2;
            else
                return R - o.R;
        }
    }
    
    static double areaOfPoly(int start , int end) {
        double area = 0;
        int prev = end;
        for(int i = start; i <= end; i++) {
            area += (pt[prev][0] + pt[i][0]) * (pt[prev][1] - pt[i][1]);
            prev = i;
        }
        return Math.abs(area / 2.0);
    }
    
    
    private static void solve() {
        
        int N , Q;
        ArrayList<String> buffer = new ArrayList<>();
        while((N = nextInt()) != 0) {
            Q = nextInt();
            pt = new double[N][2];
            BLOCK_SIZE = 1 + (int) Math.sqrt(N);
            
            for(int i = 0; i < N; i++) {
                pt[i][0] = nextDouble();
                pt[i][1] = nextDouble();
            }
            Query queries[] = new Query[Q];
            for(int i = 0; i < Q; i++) 
                queries[i] = new Query(nextInt(), nextInt(), i);
            
            Arrays.sort(queries);
            double totalArea = areaOfPoly(0, N - 1);
            double currArea = areaOfPoly(queries[0].L, queries[0].R);
            
            double ans[] = new double[Q];
            ans[queries[0].id] = Math.min(currArea , totalArea - currArea);
            int moLeft = queries[0].L;
            int moRight = queries[0].R;
            
            
            for(int i = 1; i < Q; i++) {
                Query q = queries[i];
                while(moLeft < q.L) 
                    currArea -= areaOfTri(moLeft, ++moLeft, moRight);
                
                while(moLeft > q.L)
                    currArea += areaOfTri(moLeft, --moLeft, moRight);
                
                while(moRight < q.R)
                    currArea += areaOfTri(moLeft, moRight, ++moRight);
                
                while(moRight > q.R)
                    currArea -= areaOfTri(moLeft, moRight, --moRight);
                
                ans[q.id] = Math.min(currArea , totalArea - currArea);
            }
            
            StringBuilder sb = new StringBuilder();
            for(double a : ans)
                sb.append(String.format("%.1f\n", a));
            buffer.add(sb.toString());
        }
        
        for(int i = 0; i < buffer.size(); i++)
            print((i > 0 ? "\n" : "") + buffer.get(i));
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("LA_5108_input.txt"));
//        System.setOut(new PrintStream("out.txt"));
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
//        long start = System.nanoTime();
        solve();
//        System.err.println("Time Elapsed : " + (System.nanoTime() - start) / 1e9);
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