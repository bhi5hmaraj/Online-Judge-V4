import java.util.*;
import java.io.*;
public class Vacations
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	static int steps = 0;
	static int memo[][][][];
	static int ways(int idx, int arr[], String curr, int cntR, int cntC , int cntG){
		steps++;
		System.out.println(curr);
		if(idx >= arr.length){
			int cnt = 0;
			for(int i=0;i<arr.length;i++)
				cnt += curr.charAt(i) == 'R' ? 1 : 0;
			
			return cnt;
		}
		else{
			
			if(memo[idx][cntR][cntC][cntG] != -1){
				System.out.println(curr + " " + "hit");
				return memo[idx][cntR][cntC][cntG];
			}
				
			int value;
			
			switch(arr[idx]){
			case 0:
				value = ways(idx + 1, arr, curr + "R" ,cntR + 1,cntC , cntG);
				break;
			case 1:
				if(idx == 0 || curr.charAt(idx - 1) != 'C')
					value = ways(idx + 1, arr, curr + "C" , cntR , cntC + 1, cntG);
				else
					value = ways(idx + 1, arr, curr + "R" , cntR + 1, cntC, cntG);
				break;
			case 2:
				if(idx == 0 || curr.charAt(idx - 1) != 'G')
					value = ways(idx + 1, arr, curr + "G" , cntR, cntC, cntG + 1);
				else
					value = ways(idx + 1, arr, curr + "R" , cntR + 1, cntC , cntG);
				break;
			default:
				if(idx == 0 || curr.charAt(idx - 1) == 'R')
					value = Math.min(ways(idx + 1, arr, curr + "C" , cntR, cntC + 1,cntG),
									 ways(idx + 1, arr, curr + "G" , cntR, cntC, cntG + 1));
				else{
					if(curr.charAt(idx - 1) == 'G')
						value = ways(idx + 1, arr, curr + "C", cntR, cntC + 1, cntG);
					else
						value = ways(idx + 1, arr, curr + "G", cntR, cntC, cntG + 1);
				}
				break;
			}
			return memo[idx][cntR][cntC][cntG] = value;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		
		memo = new int[N][N][N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				for(int k=0;k<N;k++)
					Arrays.fill(memo[i][j][k], -1);
		
		out.println(ways(0, arr, "", 0, 0, 0));
		
		System.err.println("Number of Steps: " + steps);
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