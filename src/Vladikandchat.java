import java.util.*;
import java.io.*;
public class Vladikandchat
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static boolean known[] , canBe[][];
	static int assign[] , M , N;
	static Boolean memo[][]; 
	static boolean backtrack(int idx , int prev) {
		if(idx == M)
			return true;
		else if(known[idx])
			return memo[idx][prev] = assign[idx] == prev ? false : backtrack(idx + 1, assign[idx]);
		else if(memo[idx][prev] != null)
			return memo[idx][prev];
		else {
			for(int i=0;i<N;i++) 
				if(canBe[idx][i] && prev != i && backtrack(idx + 1, i)) {
					assign[idx] = i;
					return memo[idx][prev] = true;
				}
			return memo[idx][prev] = false;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		
		while(T-->0) {
			N = s1.nextInt();
			HashMap<String, Integer> users = new HashMap<>();
			String invUser[] = new String[N];
			for(int i=0;i<N;i++) {
				invUser[i] = s1.next();
				users.put(invUser[i], users.size());
			}
			M = s1.nextInt();
			canBe = new boolean[M][N];
			memo = new Boolean[200][200];
			for(boolean b[] : canBe)
				Arrays.fill(b, true);
			
			known = new boolean[M];
			String line[] = new String[M];
			assign = new int[M];
			for(int i=0;i<M;i++) {
				line[i] = s1.nextLine();
				int pos = line[i].indexOf(':');
				String sender = line[i].substring(0, pos);
				if(sender.equals("?")) {
					String text = line[i].substring(pos + 1);
					StringTokenizer st = new StringTokenizer(text, " |.|,|!|?");
					while(st.hasMoreTokens()) {
						String token = st.nextToken();
						// out.println("parsed users in line " + i + " is " + token);
						if(users.containsKey(token))
							canBe[i][users.get(token)] = false;
					}
				}
				else {
					known[i] = true;
					assign[i] = users.get(sender);
				}
			}
			
			if(!backtrack(0, 199))
				out.println("Impossible");
			else {
				for(int i=0;i<M;i++) {
					int pos = line[i].indexOf(':');
					out.println(invUser[assign[i]] +":"+ line[i].substring(pos + 1));
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