import java.util.*;
import java.io.*;
public class FixaTree
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int parent[];
	static boolean markedGlobal[];
	static boolean markedTemp[];
	static ArrayList<Integer> toChange;
	
	static void dfs(int u){
		
		markedGlobal[u] = true;
		markedTemp[u] = true;
		if(!markedTemp[parent[u]]){
			if(!markedGlobal[parent[u]])
				dfs(parent[u]);
		}
		else
			toChange.add(u);
		markedTemp[u] = false;
	}
	private static void solve(FastScanner s1, PrintWriter out){

		int N 		 = s1.nextInt();
		parent 		 = s1.nextIntArrayOneBased(N);
		markedGlobal = new boolean[N + 1];
		markedTemp 	 = new boolean[N + 1];
		toChange	 = new ArrayList<>();
		
		for(int i=1;i<=N;i++)
			if(!markedGlobal[i])
				dfs(i);
		
		int first = -1;		
		for(int u:toChange)
			if(parent[u] == u){
				first = u;
				break;
			}
		
		int toChangeCnt = toChange.size() - 1 + (first == -1 ? 1 : 0);
		first = first == -1 ? toChange.get(0) : first;
		for(int u:toChange)
			parent[u] = first;
		
		out.println(toChangeCnt);
		for(int i=1;i<=N;i++)
			out.print(parent[i] + " ");
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