import java.util.*;
import java.io.*;
public class Favoritesequence
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	private static void solve(FastScanner s1, PrintWriter out){

		HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
		HashMap<Integer , Integer> inDegree = new HashMap<>();
		
		int N = s1.nextInt();
		while(N-->0) {
			int K = s1.nextInt();
			int arr[] = s1.nextIntArray(K);
			if(!inDegree.containsKey(arr[0]))
				inDegree.put(arr[0], 0);
			for(int i=0;i<K-1;i++) {
				ArrayList<Integer> arl = adj.getOrDefault(arr[i], new ArrayList<>());
				arl.add(arr[i + 1]);
				adj.put(arr[i], arl);
				inDegree.put(arr[i + 1], inDegree.getOrDefault(arr[i + 1], 0) + 1);
			}
			if(!adj.containsKey(arr[K - 1]))
				adj.put(arr[K - 1], new ArrayList<>());
		}
		
		// out.println(adj);
		// out.println(inDegree);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(Map.Entry<Integer, Integer> e : inDegree.entrySet())
			if(e.getValue().intValue() == 0)
				pq.add(e.getKey());
		
		 // out.println(pq);
		
		while(!pq.isEmpty()) {
			int u = pq.remove();
			out.print(u + " ");
			for(int v : adj.get(u)) {
				inDegree.put(v, inDegree.get(v) - 1);
				if(inDegree.get(v).intValue() == 0)
					pq.add(v);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}