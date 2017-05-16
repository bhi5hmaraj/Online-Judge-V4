import java.util.*;
import java.io.*;
public class MikeandShortcuts
{


    /************************ SOLUTION STARTS HERE ***********************/


    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int arr[] = s1.nextIntArrayOneBased(N);
	int distTo[] = new int[N+1];
	ArrayDeque<Integer> queue = new ArrayDeque<>();
	boolean marked[] = new boolean[N+1];
	queue.add(1);
	marked[1] = true;
	while(!queue.isEmpty()){
	    int u = queue.remove();
	    int adjacent[] = new int[]{arr[u] , u - 1 , u + 1};
	    for(int v:adjacent){
		if(v >= 1 && v <= N && !marked[v]){
		    marked[v] = true;
		    distTo[v] = distTo[u] + 1;
		    queue.add(v);
		}
	    }
	}

	for(int i=1;i<=N;i++)
	    out.print(distTo[i] + " ");

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