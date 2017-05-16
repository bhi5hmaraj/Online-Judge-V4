import java.util.*;
import java.io.*;
public class CaesarsLegions
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final int MOD = (int) 1e8;
	
	static class State{
		int idx , F , H , streakF, streakH;

		public State(int idx, int f, int h, int streakF, int streakH) {
			this.idx = idx;
			F = f;
			H = h;
			this.streakF = streakF;
			this.streakH = streakH;
		}
		@Override
		public int hashCode() {
			return Objects.hash(idx , F , H , streakF , streakH);
		}
		@Override
		public boolean equals(Object obj) {
			State that = (State) obj;
			return idx == that.idx && F == that.F && H == that.H && streakF == that.streakF && streakH == that.streakH;
		}
	}
	
	static HashMap<State, Integer> memo = new HashMap<>();
	static int steps = 0;
	static int count(int idx , int F , int H , int streakF, int streakH , int k1 , int k2 , int len){
		steps++;
		if(idx >= len){
			return streakF > k1 || streakH > k2 ? 0 : 1;
		}
		else{
			
			if(streakF > k1 || streakH > k2)
				return 0;
			
			State s = new State(idx, F, H, streakF, streakH);
			
			if(memo.containsKey(s))
				return memo.get(s);
			
			int w1 = 0 , w2 = 0;
			if(F > 0)
				w1 = count(idx + 1, F - 1, H, streakF + 1, 0, k1, k2, len );
			if(H > 0)
				w2 = count(idx + 1, F, H - 1, 0, streakH + 1, k1, k2, len );
			
			int ans = ( (w1 % MOD) + (w2 % MOD) ) % MOD;
			memo.put(s, ans);
			return ans;
		}

	}

	private static void solve(FastScanner s1, PrintWriter out){

		int F = s1.nextInt();
		int H = s1.nextInt();
		int k1 = s1.nextInt();
		int k2 = s1.nextInt();
		
		out.println(count(0, F, H, 0, 0, k1, k2, F + H));

		//out.println("STEPS " + steps);
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