import java.util.*;
import java.io.*;
public class LargestCycle
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static ArrayList<Integer>[] adj;
    private static boolean globalMarked[];
    private static boolean tempMarked[];
    private static int inTime[];
    private static int MAX_LEN = -1;


    private static void dfs(int u,int time)
    {
	globalMarked[u] = true;
	tempMarked[u] = true;
	inTime[u] = time;	
	for(int v:adj[u])
	{
	    if(tempMarked[v])  //Found a cycle
	    {
		int cycle_len = time - inTime[v] + 1;
		MAX_LEN = Math.max(MAX_LEN,cycle_len);
	    }
	    else if(!globalMarked[v])
	    {
		dfs(v, time + 1);
	    }

	}
	tempMarked[u] = false;
    }


    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	int V = s1.nextInt();	
	adj = (ArrayList<Integer>[]) new ArrayList[V];
	for(int i=0;i<V;i++)
	    adj[i] = new ArrayList<>();
	globalMarked = new boolean[V];
	tempMarked   = new boolean[V];
	inTime 	     = new int[V];
	for(int i=0;i<V;i++){
	    int to = s1.nextInt();
	    if(to != -1)
		adj[i].add(to);
	}

	for(int i=0;i<V;i++)
	    if(!globalMarked[i])
		dfs(i, 0);

	out.print(MAX_LEN);

    }



    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String[] args) throws IOException {
	new Thread(null, new Runnable() {
	    public void run() {
		new LargestCycle().run();
	    }
	}, "Increase Stack", 1L << 25).start();

    }

    void run(){	

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