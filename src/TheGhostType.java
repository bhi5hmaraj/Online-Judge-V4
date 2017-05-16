import java.util.*;
import java.io.*;
public class TheGhostType
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int cnt = 0;
	
	static void permute(int idx , int N , int arr[] , boolean marked[]){
		
		if(idx > N){
			boolean flag = true;
			for(int i=1;i<=N;i++)			
				for(int j=1;j<=N;j++){
					if(i != j && (arr[i] & arr[j]) == arr[i])
						flag &= i < j;
				}
					
					
			cnt += flag ? 1 : 0;
				
		}
		else{
			for(int i=1;i<=N;i++){
				if(!marked[i]){
					marked[i] = true;
					arr[idx] = i;
					permute(idx + 1, N, arr, marked);
					marked[i] = false;
				}
			}
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		int N = s1.nextInt();
		permute(1, N, new int[N + 1] , new boolean[N + 1]);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}