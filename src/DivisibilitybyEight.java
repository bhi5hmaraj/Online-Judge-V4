import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class DivisibilitybyEight
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static HashMap<String,HashMap<Integer,String>> memo = new HashMap<>();
	static boolean cache[][];

	static void isPossible(int idx,int sum,String num,int choosed){
		if(!cache[sum][idx]){
			if(choosed > 0)
				cache[sum][idx] = true;
			if(idx < num.length()){
				isPossible(idx + 1, sum, num, choosed);
				isPossible(idx + 1, (((sum * 10) % 8) + ((num.charAt(idx) - '0') % 8)) % 8, num,choosed + 1);
			}
		}
	}

	
	static String findWay(String curr, String num,int idx){
		if(idx >= num.length()){
			if(curr.isEmpty())
				return null;
			else
				return new BigInteger(curr).mod(BigInteger.valueOf(8)).equals(BigInteger.ZERO) ? curr : null;
		}
		else{
			HashMap<Integer,String> mp = memo.get(curr);
			if(mp != null && mp.containsKey(idx)){
				return mp.get(idx);
			}
			if(mp == null)
				mp = new HashMap<>();
			
			String s1 = findWay(curr + num.charAt(idx), num, idx + 1);
			if(s1 != null){
				mp.put(idx, s1);
				memo.put(curr, mp);
				return s1;
			}
			String s2 = findWay(curr, num, idx + 1);
			if(s2 != null){
				mp.put(idx, s2);
				memo.put(curr, mp);
				return s2;
			}
			mp.put(idx, null);
			memo.put(curr, mp);
			return null;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String num = s1.nextLine();
		cache = new boolean[8][num.length() + 1];
		isPossible(0, 0, num, 0);

		boolean flag = false;
		for(boolean b:cache[0])
			flag |= b;
		
		if(!flag)
			out.println("NO");
		else{
			out.println("YES");
			out.println(findWay("", num, 0));
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