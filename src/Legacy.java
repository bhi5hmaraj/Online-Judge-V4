import java.util.*;
import java.io.*;
public class Legacy {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Edge {
        int to;
        long cost;
        Edge(int v , long c) {
            to = v;
            cost = c;
        }
    }
    
    static ArrayList<ArrayList<Edge>> adj;
    static ArrayList<int[]> child;
    static int map[];
    
    static int initGraph(int l , int r) {
        if(l == r) {
            int node = adj.size();
            adj.add(new ArrayList<>());
            map[l] = node;
            return node;
        } else {
            int m = (l + r) / 2;
            int left = initGraph(l, m);
            int right = initGraph(m + 1, r);
            int top = adj.size();
            adj.add(new ArrayList<>(Arrays.asList(new Edge(left, 0) , new Edge(right, 0))));
            int bottom = adj.size();
            adj.add(new ArrayList<>());
            adj.get(left + 1).add(new Edge(bottom, 0));
            adj.get(right + 1).add(new Edge(bottom, 0));
            child.add(new int[]{left , right});
            return top;
        }
    }
    
    static void modifyGraph(int node , int nl , int nr , int l , int r , int planet , long cost , boolean top) {
        if(nl == l && nr == r) {
            if(top) 
                adj.get(map[planet]).add(new Edge(node, cost));
            else
                adj.get(node + 1).add(new Edge(map[planet], cost));
        } else {
            
        }
    }
    
    private static void solve() {
        
        
        
        
        
        
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