import java.util.*;
import java.io.*;
public class CardTrick
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static long DPTotal[] , DPFavour[];
	
	static int possible[] = {2,3,4,5,6,7,8,9,10,10,10,10,11};
	
	static int getVal(String s) {
		if(s.length() == 2)
			return 10;
		else if(Character.isDigit(s.charAt(0)))
			return s.charAt(0) - '0';
		else {
			if(s.charAt(0) == 'A')
				return 11;
			else
				return 10;
		}
	}
	
	static long totalWays(int idx , int end) {
		
		if(idx >= end)
			return 1;
		else if(DPTotal[idx] != -1)
			return DPTotal[idx];
		else {

			DPTotal[idx] = 0;
			for(int move : possible)
				DPTotal[idx] += totalWays(idx + move, end);
			
			return DPTotal[idx];
		}
		
	}
	
	static long favourableWays(int idx , int end) {
		if(idx >= end)
			return 0;
		else if(DPFavour[idx] != -1)
			return DPFavour[idx];
		else {
			DPFavour[idx] = 0;
			for(int move : possible)
				DPFavour[idx] += favourableWays(idx + move, end);
			
			return DPFavour[idx];
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line = null;
		while((line = s1.nextLine()) != null) {
			
			String sp[] = line.split(" ");
			int N = Integer.parseInt(sp[0]);
			int M = Integer.parseInt(sp[1]);
			String vals[] = s1.nextLine().split(" ");
			int parsed[] = new int[N];
			for(int i=0;i<N;i++)
				parsed[i] = getVal(vals[i]);
			int len = M - 1;
			for(int i=0;i<N-1;i++)
				len += parsed[i];
			len++;
			int orgKnown[] = new int[len];
			int ptr = M - 1;
			for(int i=0;i<N;i++) {
				orgKnown[ptr] = parsed[i];
				ptr += parsed[i];
			}

			System.out.println("Parsed " + Arrays.toString(orgKnown));
			
			double prob = 0.0;
			DPFavour = new long[len];
			DPTotal = new long[len];
			
			for(int i=0;i<len;i++) {
				DPFavour[i] = orgKnown[i] > 0 ? 1 : -1;
				DPTotal[i] =  orgKnown[i] > 0 ? 1 : -1;
			}
			
			for(int j=0;j<Math.min(len,10);j++) 
				prob += (0.1 * ((double)(favourableWays(j, len)) / (double)(totalWays(j, len))));
			
			System.out.println("Favour " + Arrays.toString(DPFavour));
			System.out.println("Total " + Arrays.toString(DPTotal));
			
			/*			
			for(int i=len >= 10 ? 0 : len - 10;i<last;i++) {
				long deno = 0;
				DPTotal = new long[len + i];
				for(int j=0;j<DPTotal.length;j++)
					DPTotal[j] = j < len ? (orgKnown[j] > 0 ? 1 : -1) : -1;
				
				for(int j=0;j<DPTotal.length;j++)
					deno += totalWays(j, len + i);
				
				System.out.println("total = " + deno);
				
				prob += ((double)nume / (double)deno);
				
			
			*/
			
			System.out.println(prob);
		}
		
	}
	
	private static void solve2(FastScanner s1, PrintWriter out){
		
		String line = null;
		while((line = s1.nextLine()) != null) {
			
			String sp[] = line.split(" ");
			int N = Integer.parseInt(sp[0]);
			int M = Integer.parseInt(sp[1]);
			String vals[] = s1.nextLine().split(" ");
			int parsed[] = new int[N];
			for(int i=0;i<N;i++)
				parsed[i] = getVal(vals[i]);
			int len = M - 1;
			for(int i=0;i<N-1;i++)
				len += parsed[i];
			len++;
			int known[] = new int[len];
			int ptr = M - 1;
			for(int i=0;i<N;i++) {
				known[ptr] = parsed[i];
				ptr += parsed[i];
			}

			// System.out.println("Parsed " + Arrays.toString(known));
			
			double prob[] = new double[len];
			for(int i=0;i<len;i++)
				if(known[i] > 0)
					prob[i] = 1.0;
			
			for(int i=len-1;i >= 0;i--) {
				if(known[i] == 0) {
					for(int card : possible)
						if(i + card < len)
							prob[i] += ((1.0/13.0) * prob[i + card]); // one out of 13 cards and either one can lead to a desired solution
				}
			}
			
			double totalProb = 0.0;
			for(int i=0;i < Math.min(len,10);i++)
				totalProb += ((1.0/10.0) * prob[i]);
			
			out.println(totalProb);
			
		}
		
		
	}
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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