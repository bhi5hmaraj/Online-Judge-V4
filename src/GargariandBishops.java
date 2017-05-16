import java.util.*;
import java.io.*;
public class GargariandBishops
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	


	private static void solve(FastScanner s1, PrintWriter out){
		
		HashMap<Integer,Long> d1 = new HashMap<>();
		HashMap<Integer,Long> d2 = new HashMap<>();
		int N = s1.nextInt();
		long grid[][] = new long[N][N];
		for(int i=0;i<N;i++)
			grid[i] = s1.nextLongArray(N);
		
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++){
				d1.put(i - j, d1.getOrDefault(i - j, 0L) + grid[i][j]);
				d2.put(i + j, d2.getOrDefault(i + j, 0L) + grid[i][j]);
			}
		
		long max[] = new long[2];
		max[0] = max[1] = Long.MIN_VALUE;
		int pos[][] = new int[2][2];		
		int idx = 0;
		for(int i=0;i<N;i++){
			idx = N % 2 == 0 ? i % 2 : idx;
			for(int j=0;j<N;j++){
				long sum = d1.get(i - j) + d2.get(i + j) - grid[i][j];
				if(sum > max[idx]){
					max[idx] = sum;
					pos[idx][0] = i;
					pos[idx][1] = j;
				}
				idx ^= 1;
			}
		}
		
		out.println(max[0] + max[1]);
		for(int i=0;i<4;i++)
			out.print(++pos[i/2][i%2] + " ");
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