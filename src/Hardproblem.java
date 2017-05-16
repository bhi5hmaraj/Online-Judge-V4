import java.util.*;
import java.io.*;
public class Hardproblem
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final long INF = (long)(1e16);
	static long memo[][];
	
	static long findOptimal(int idx , int order[] , int rev_order[] , long cost[] ,int prev, int d){

		if(idx < order.length){
			
			if(memo[idx][d] != -1)
				return memo[idx][d];
			
			long op1 , op2;
			op1 = op2 = INF;
			if(prev > order[idx] && prev > rev_order[idx])
				return INF;

			if(order[idx] >= prev)
				op1 = findOptimal(idx + 1, order, rev_order, cost, order[idx] , 0);
			if(rev_order[idx] >= prev)
				op2 = findOptimal(idx + 1, order, rev_order, cost, rev_order[idx] , 1);

			return memo[idx][d] = Math.min(op1,Math.min(op2 + cost[idx], INF));
		}
		else 
			return 0L;
	}


	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		long cost[] = s1.nextLongArray(N);
		String arr[] = new String[N];
		String revArr[] = new String[N];
		TreeSet<String> set = new TreeSet<>();
		HashMap<String, Integer> map = new HashMap<>();
		
		memo = new long[N + 1][2];
		for(int i=0;i<=N;i++)
			memo[i][0] = memo[i][1] = -1;
		
		for(int i=0;i<N;i++){
			arr[i] = s1.nextLine();
			revArr[i] = new StringBuilder(arr[i]).reverse().toString();
			set.add(arr[i]);
			set.add(revArr[i]);
		}

		for(String s:set)
			map.put(s, map.size());

		int order[] = new int[N];
		int rev_order[] = new int[N];

		for(int i=0;i<N;i++){
			order[i] = map.get(arr[i]);
			rev_order[i] = map.get(revArr[i]);
		}
		/*
		out.println("order " + Arrays.toString(order));
		out.println("rever " + Arrays.toString(rev_order));
		 */
		long ans = findOptimal(0, order, rev_order, cost, -1 , 0);
		out.println(ans == INF ? -1 : ans);
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