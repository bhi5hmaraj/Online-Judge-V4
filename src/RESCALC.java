import java.util.*;
import java.io.*;
class RESCALC
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0)
		{
			int N = s1.nextInt();
			int points[] = new int[N + 1];
			for(int i=1;i<=N;i++){
				int M = s1.nextInt();
				int freq[] = new int[7];
				for(int j=0;j<M;j++)
					freq[s1.nextInt()]++;
				points[i] = M;
				int nonZero = 0;
				for(int a:freq)
					nonZero += a > 0 ? 1 : 0;
				while(nonZero > 3){
					int min = Integer.MAX_VALUE;
					// System.out.println("i " + i + Arrays.toString(freq));
					for(int j=1;j<=6;j++)
						if(freq[j] > 0)
							min = Math.min(min,freq[j]);
					
					points[i] += (min *  (nonZero == 4 ? 1 : (nonZero == 5 ? 2 : 4)));
					for(int j=1;j<=6;j++)
						freq[j] -= min;
					nonZero = 0;
					for(int a:freq)
						nonZero += a > 0 ? 1 : 0;
						
						//System.out.println("i " + i + Arrays.toString(freq));
				}
				
			}
			int max = 0;
			for(int a:points)
				max = Math.max(max,a);
			int max_cnt = 0 , id = -1;
			for(int i=1;i<=N;i++){
				if(points[i] == max){
					max_cnt++;
					id = i;
				}
			}
			out.println(max_cnt > 1 ? "tie" : ((id == 1) ? "chef" : id) );
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