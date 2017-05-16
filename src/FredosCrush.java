import java.util.*;
import java.io.*;
public class FredosCrush
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer>[] adj;
	static int localMax;
	static int maxLen;
	static boolean DP[][];
	static int V;
	static int calcSum(int N) {
		int s = 0;
		for(int j=1;j<=V;j++)
			if((N & (1 << j)) != 0)
				s += j;

		return s;
	}
	static int gcd(int big, int small) {
		int b = Math.max(big, small);
		int s = Math.min(big, small);
		return (s == 0) ? b : gcd(s, b % s);
	}

	static void dfs(int u , int marked ,int len) {
		if(!DP[marked][u]) {
			// System.out.println(curr);
			DP[marked][u] = true;
			if(len > maxLen) {
				maxLen = len;
				localMax = calcSum(marked);
			}
			else if(len == maxLen)
				localMax = Math.max(localMax,calcSum(marked));
			for(int v : adj[u])
				if((marked & (1 << v)) == 0)
					dfs(v, marked | (1 << v) , len + 1);
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {

			V = s1.nextInt();
			int E = s1.nextInt();
			int sum[] = new int[V + 1];
			adj = new ArrayList[V + 1];
			for(int i=1;i<=V;i++)
				adj[i] = new ArrayList<>();
			while(E-->0) {
				int u = s1.nextInt();
				int v = s1.nextInt();
				adj[u].add(v);
				adj[v].add(u);
			}

			sum = new int[V + 1];
			for(int i=1;i<=V;i++) {
				DP = new boolean[1 << (V + 2)][V + 1];
				localMax = 0;
				maxLen = 0;
				dfs(i, 1 << i , 1);
				sum[i] = localMax;
			}
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i=1;i<=V;i++) {
				min = Math.min(min,sum[i]);
				max = Math.max(max,sum[i]);
			}
			int gcd = gcd(min, max);
			//System.out.println(Arrays.toString(sum));
			out.println((max / gcd) + " " + (min / gcd));
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