import java.util.*;
import java.io.*;
public class RustAndMurderer
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static HashSet<Integer>[] adj;
    private static boolean marked[];
    private static int V;

    private static int[] bfs(int start){

	ArrayDeque<Integer> queue = new ArrayDeque<>();
	int distTo[] = new int[V+1];
	queue.add(start);
	marked[start] = true;
	int remain = V - 1;
	while(!queue.isEmpty()){

	    int u = queue.remove();	    
	    for(int i=1;i<=V;i++){
		if(!adj[u].contains(i) && !marked[i]){
		    queue.add(i);
		    marked[i] = true;
		    distTo[i] = distTo[u] + 1;		    
		    remain--;
		    if(remain == 0)
			return distTo;
		     
		}
	    }
	}
	return distTo;
    }

    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0){

	    V = s1.nextInt();
	    int E = s1.nextInt();
	    adj = (HashSet<Integer>[])new HashSet[V+1];
	    for(int i=1;i<=V;i++)adj[i] = new HashSet<>();
	    marked = new boolean[V+1];
	    while(E-->0){
		int u = s1.nextInt();
		int v = s1.nextInt();
		adj[u].add(v);
		adj[v].add(u);
	    }

	    for(int d:bfs(s1.nextInt()))
		out.print(d != 0 ? (d + " ") : "");

	    out.println();
	}

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