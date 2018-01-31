import java.util.*;

import FindCycles.FastScanner;

import java.io.*;
public class Substring {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    private static ArrayList<Integer>[] adj;
    private static boolean visitedGlobal[];
    private static boolean visitedTemp[];

    private static boolean dfs(int u){

        visitedGlobal[u] = true;
        visitedTemp[u] = true;
        for(int v:adj[u]){

            if(visitedTemp[v])
                return true;

            if(!visitedGlobal[v]){
                if(dfs(v))
                    return true;
            }

        }
        visitedTemp[u] = false;
        return false;

    }

    private static boolean isCyclic(int V){

        for(int i=1;i<=V;i++){

            if(!visitedGlobal[i]){      
                if(dfs(i))
                    return true;
            }

        }

        return false;

    }

    @SuppressWarnings("unchecked")
    private static void solve(){

        int V = nextInt();
        int E = nextInt();
        adj = (ArrayList<Integer>[]) new ArrayList[V+1];
        for(int i=1;i<=V;i++)adj[i] = new ArrayList<>();
        
        char str[] = nextLine().toCharArray();
        
        visitedGlobal = new boolean[V + 1];
        visitedTemp = new boolean[V + 1];
        while(E-->0)
            adj[nextInt()].add(nextInt());
        
        if(isCyclic(V)) {
            println(-1);
            return;
        }
        
        // Now a DAG
        
        
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