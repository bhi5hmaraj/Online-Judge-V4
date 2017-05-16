import java.util.*;
import java.io.*;
public class IceSkating
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    static ArrayList<Integer> adj[];
    static boolean marked[];
    
    static void dfs(int u){
	marked[u] = true;
	for(int v:adj[u])
	    if(!marked[v])
		dfs(v);
    }
	
    
    
    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	adj = (ArrayList<Integer>[]) new ArrayList[2010];
	marked = new boolean[2010];
	for(int i=1;i<=2000;i++)
	    adj[i] = new ArrayList<>();
	
	int N = s1.nextInt();
	while(N-->0){
	    int u = s1.nextInt();
	    int v = s1.nextInt();
	    adj[u].add(1000 + v);
	    adj[1000 + v].add(u);
	}
	int cnt = 0;
	for(int i=1;i<=2000;i++){
	    if(adj[i].size() != 0 && !marked[i]){
		cnt++;
		dfs(i);
	    }
	}
	
	out.println(cnt - 1);
    }


    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
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
	int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
	long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
	int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
	long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
	void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
    }

    /************************ TEMPLATE ENDS HERE ************************/
}