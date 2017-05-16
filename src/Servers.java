import java.util.*;
import java.io.*;
public class Servers
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Server implements Comparable<Server>{
		int id , timeFree;
		Server(int idd , int t) {
			id = idd;
			timeFree = t;
		}
		@Override
		public int compareTo(Server o) {
			return Integer.compare(id, o.id);
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int Q = s1.nextInt();
		
		Server arr[] = new Server[N];
		for(int i=1;i<=N;i++)
			arr[i - 1] = (new Server(i, 0));
		
		while(Q-->0) {
			int t = s1.nextInt();
			int k = s1.nextInt();
			int d = s1.nextInt();
			int sum = 0;
			PriorityQueue<Server> need = new PriorityQueue<>();
			for(Server s : arr)
				if(s.timeFree <= t)
					need.add(s);
			
			if(need.size() < k)
				out.println(-1);
			else {
				while(k-->0) {
					Server curr = need.remove();
					curr.timeFree = t + d;
					sum += curr.id;
				}
				out.println(sum);
			}
			
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}