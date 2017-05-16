import java.util.*;
import java.io.*;
public class MemoryandTrident
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int x[] = {0 , 1 , 0 , -1}; //up , right , down and left 
	static int y[] = {1 , 0 ,-1 ,  0};
	static final int INF = (int)(1e7);
	static class Pair {
		int xx , yy;
		Pair(int xx , int yy){
			this.xx = xx;
			this.yy = yy;
		}
		@Override
		public boolean equals(Object obj) {
			Pair that = (Pair) obj;
			return xx == that.xx && yy == that.yy;
		}
		@Override
		public int hashCode() {
			return Objects.hash(xx , yy);
		}
	}
	
	static HashMap<Pair , Integer>[] memo;
	
	static int findOpt(int idx , int xx , int yy , int move[]){
		if(idx >= move.length)
			return (xx == 0 && yy == 0) ? 0 : INF;
		else {
			
			Pair key = new Pair(xx, yy);
			if(memo[idx].containsKey(key))
				return memo[idx].get(key);
			
			int min = findOpt(idx + 1, xx + x[move[idx]], yy + y[move[idx]], move);
			for(int i=0;i<4;i++)
				if(i != move[idx])
					min = Math.min(min,1 + findOpt(idx + 1, xx + x[i], yy + y[i], move));
			
			memo[idx].put(key, min);
			
			return min;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		String seq = s1.nextLine();
		int len = seq.length();
		memo = new HashMap[len];
		int move[] = new int[len];
		for(int i=0;i<len;i++){
			memo[i] = new HashMap<>();
			switch(seq.charAt(i)){
			case 'L':
				move[i] = 3;
				break;
			case 'D':
				move[i] = 2;
				break;
			case 'R':
				move[i] = 1;
				break;
			case 'U':
				move[i] = 0;
				break;
			}
		}
		
		int ans = findOpt(0, 0, 0, move);
		out.println(ans == INF ? -1 : ans);
	}
	
	private static void solve2(FastScanner s1, PrintWriter out){
		String seq =s1.nextLine();
		int len = seq.length();
		int L = 0 , R = 0 , U = 0 , D = 0;
		for(int i=0;i<len;i++){
			switch(seq.charAt(i)){
			case 'L':
				L++;
				break;
			case 'D':
				D++;
				break;
			case 'R':
				R++;
				break;
			case 'U':
				U++;
				break;
			}
		}
		int diff1 = Math.abs(L - R);
		int diff2 = Math.abs(U - D);
		if((diff1 % 2 == 0 && diff2 % 2 == 0) || (diff1 % 2 == 1 && diff2 % 2 == 1))
			out.println((diff1 + diff2) / 2);
		else
			out.println(-1);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}