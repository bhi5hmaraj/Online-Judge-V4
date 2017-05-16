import java.util.*;
import java.io.*;

public class BearandFriendshipCondition {

    /************************ SOLUTION STARTS HERE ***********************/

    static ArrayList<Integer>[] adj;
    static boolean              marked[];
    static int                  id[];
    static ArrayList<Integer>   size;

    static int computeSize(int u, int idx) {
        marked[u] = true;
        id[u] = idx;
        int sz = 1;
        for (int v : adj[u])
            if (!marked[v])
                sz += computeSize(v, idx);

        return sz;
    }

    static boolean check(int u, int sz) {
        marked[u] = true;
        boolean flag = adj[u].size() == sz - 1;
        for (int v : adj[u])
            if (!marked[v])
                flag &= check(v, sz);

        return flag;
    }

    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out) {

        int V = s1.nextInt();
        int E = s1.nextInt();

        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();

        while (E-- > 0) {
            int u = s1.nextInt();
            int v = s1.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        marked = new boolean[V + 1];
        id = new int[V + 1];
        size = new ArrayList<>();      
        
        for (int i = 1; i <= V; i++)
            if (!marked[i])
                size.add(computeSize(i, size.size()));

        marked = new boolean[V + 1];
        boolean flag = true;
        for (int i = 1; i <= V; i++)
            if (!marked[i])
                flag &= check(i, size.get(id[i]));

        out.println(flag ? "YES" : "NO");
    }

    /************************ SOLUTION ENDS HERE ************************/

    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        solve(in, out);
        in.close();
        out.close();
    }
    
    static class FastScanner{
        BufferedReader reader;
        StringTokenizer st;
        FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;}	
        String next()
        {while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}		    
        st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
        String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}	    	  	
        int    nextInt()   {return Integer.parseInt(next());}
        long   nextLong()  {return Long.parseLong(next());}		
        double nextDouble(){return Double.parseDouble(next());}
        char   nextChar()  {return next().charAt(0);}
        int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
        long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
        int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
        long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
	    void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
    }
	
	/************************ TEMPLATE ENDS HERE ************************/
}