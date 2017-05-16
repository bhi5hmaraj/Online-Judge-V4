import java.util.*;
import java.io.*;
public class LittleMonkandSwaps
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int[] ans , sort ;
	static int N, ptr = 1;
	static void rec(int node) {
		if(node <= N) {
			rec(2*node);
			ans[node] = sort[ptr++];
			rec(2*node + 1);
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		N = s1.nextInt();
		int[] arr = s1.nextIntArrayOneBased(N);
		sort = new int[N + 1];
		System.arraycopy(arr, 0, sort, 0, N + 1);
		Arrays.sort(sort);
		ans = new int[N + 1];
		rec(1);
		int cnt = 0;
		int MAX = (int)1e5;
		int perm[] = new int[MAX + 1];
		for(int i=1;i<=MAX;i++) perm[i] = i;
		for(int i=1;i<=N;i++)
			perm[ans[i]] = arr[i];

		boolean marked[] = new boolean[MAX + 1];
		for(int i=1;i<=MAX;i++) {
			int len = 0;
			int curr = i;
			while(!marked[curr]) {
				marked[curr] = true;
				len++;
				curr = perm[curr];
			}
			
			cnt += len > 0 ? len - 1 : 0;
		}
		// System.out.println("ans " + Arrays.toString(ans));
		// System.out.println("arr " + Arrays.toString(arr));
		out.println(cnt);
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