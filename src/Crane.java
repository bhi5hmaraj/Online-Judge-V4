import java.util.*;
import java.io.*;
public class Crane
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int arr[] , N , pos[];

	static void swap(int L , int R) {
		int M = (L + R) / 2;
		for(int i=L;i<=M;i++) {
			int temp1 = arr[i];
			int temp2 = pos[arr[i]];
			pos[arr[i]] = pos[arr[M + 1 + i - L]];
			arr[i] = arr[M + 1 + i - L];
			pos[arr[M + 1 + i - L]] = temp2;
			arr[M + 1 + i - L] = temp1;
		}
		// System.out.println("after swap L " + L + " " + R + " " + Arrays.toString(arr));
	}

	static ArrayList<String> steps;

	static void rec(int L , int R , int curr) {

		if(L < R) {
			if((R - L + 1) % 2 == 1) {
				if(pos[curr] == R) {
					steps.add((R - 1) + " " + R);
					swap(R - 1, R);
				}
				R--;
			}
			int M = (L + R) / 2;
			if(pos[curr] > M) {
				swap(L, R);
				steps.add(L + " " + R);
			}
			rec(L, M, curr);
		}
	}


	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			arr = s1.nextIntArrayOneBased(N);
			pos = new int[N + 1];
			for(int i=1;i<=N;i++)
				pos[arr[i]] = i;
			
			steps = new ArrayList<>();
			for(int i =1;i <= N;i++) 
				rec(i, N, i);
			
			out.println(steps.size());
			for(String s : steps)
				out.println(s);
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