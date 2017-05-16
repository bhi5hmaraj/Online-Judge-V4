import java.util.*;
import java.io.*;
public class Voting
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		char str[] = s1.nextLine().toCharArray();

		TreeSet<Integer> Rs = new TreeSet<>();
		TreeSet<Integer> Ds = new TreeSet<>();
		for(int i=0;i<N;i++)
			if(str[i] == 'R')
				Rs.add(i);
			else
				Ds.add(i);

		boolean marked[] = new boolean[N];
		while(true) {
			for(int i=0;i<N;i++) {

				if(Rs.isEmpty()) {
					out.println("D");
					return;
				}
				if(Ds.isEmpty()) {
					out.println("R");
					return;
				}

				if(!marked[i]) {
					if(str[i] == 'R') {
						Integer next = Ds.higher(i);
						next = next == null ? Ds.higher(-1) : next;
						Ds.remove(next);
						marked[next] = true;
					}
					else if(str[i] == 'D') {
						Integer next = Rs.higher(i);
						next = next == null ? Rs.higher(-1) : next;
						Rs.remove(next);
						marked[next] = true;
					}
				}
			}
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