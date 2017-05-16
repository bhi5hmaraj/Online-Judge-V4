import java.util.*;
import java.io.*;
public class GenerateaString
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final long INF = (long)(1e18);
	static long memo[];
	static long findOpt(int idx ,long x , long y ){
		
		if(memo[idx] != -1)
			return memo[idx];
		else{
			long ans = INF;
			if(idx % 2 == 1) // min ( add , double and remove )
				ans = Math.min(x + findOpt(idx - 1, x, y),y + x + findOpt((idx + 1) / 2, x, y));
			else
				ans = Math.min(x + findOpt(idx - 1, x, y),y + findOpt(idx / 2, x, y));
			
			return memo[idx] = ans;
		}
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		long x = s1.nextLong();
		long y = s1.nextLong();
		memo = new long[N + 1];
		Arrays.fill(memo, -1);
		memo[1] = x;
		memo[0] = INF;
		out.println(findOpt(N, x, y ));
		/*
		long dp[] = new long[N + 1];
		dp[1] = x;
		for(int i=2;i<=N;i++){
			if(i % 2 == 1)
				dp[i] = Math.min(dp[i - 1] + x,x + y + dp[(i + 1) / 2]);
			else
				dp[i] = Math.min(dp[i - 1] + x,y + dp[i / 2]);
		}
		out.println(dp[N]);
		*/
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
  
	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new GenerateaString().run();
			}
		}, "Increase Stack", 1L << 27).start();

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