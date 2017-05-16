import java.util.*;
import java.io.*;
class ICPC16F
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0)
		{
			int N = s1.nextInt();
			int M = s1.nextInt();
			int d = s1.nextInt();
			int D = s1.nextInt();
			ArrayList<Integer>[] left  = new ArrayList[N];
			ArrayList<Integer>[] right = new ArrayList[N];
			for(int i=0;i<N;i++){
				left[i] = new ArrayList<>();
				right[i] = new ArrayList<>();
			}
			int l = 0 , r = 0;
			while(M-->0) {
				left[l].add(r);
				right[r].add(l);
				l = (l + 1) % N;
				if(l == 0)
					r = (r + 2) % N;
				else
					r = (r + 1) % N;
			}
			
			boolean flag = true;
			for(int i=0;i<N;i++)
				flag &= (left[i].size() >= d && left[i].size() <= D) && (right[i].size() >= d && right[i].size() <= D); 
			if(flag) {
				for(int i=0;i<N;i++)
					for(int j : left[i])
						out.println((i + 1) + " " + (j + 1));
			}
			else
				out.println(-1);
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