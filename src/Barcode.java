import java.util.*;
import java.io.*;
public class Barcode
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final int INF = (int)(1e8);

	static HashMap<State , Integer> memo = new HashMap<>();

	static class State {
		int idx , streak1 , streak0;

		public State(int idx, int streak1, int streak0) {
			this.idx = idx;
			this.streak1 = streak1;
			this.streak0 = streak0;
		}
		@Override
		public int hashCode() {
			return Objects.hash(idx, streak1 , streak0);
		}
		@Override
		public boolean equals(Object obj) {
			State that = (State) obj;
			return idx == that.idx && streak1 == that.streak1 && streak0 == that.streak0;
		}
	}

	static int findOptimal(int idx , int col[] , int streak1 ,int streak0, int x , int y , int row /*, String curr*/){

		if(idx >= col.length){
				
			if(streak1 > 0 && streak1 >= x && streak1 <= y){
				//System.out.println(curr);
				return 0;
			}
			else if(streak0 > 0 && streak0 >= x && streak0 <= y){
				//System.out.println(curr);
				return 0;
			}
			else
				return INF;
		}
		else{

			if(streak1 > y || streak0 > y)
				return INF;

			State s = new State(idx, streak1, streak0);

			if(memo.containsKey(s))
				return memo.get(s);

			int ans;

			if((streak0 == 0 && streak1 == 0 ) || streak0 >= x || streak1 >= x)
				ans = Math.min((row - col[idx]) + findOptimal(idx + 1, col, streak1 + 1, 0, x, y, row /*, curr + 1*/),
								col[idx] + findOptimal(idx + 1, col, 0, streak0 + 1, x, y, row /*, curr + 0*/));
			else if(streak0 == 0 && streak1 < x)
				ans = (row - col[idx]) + findOptimal(idx + 1, col, streak1 + 1, 0, x, y, row /*, curr + 1*/);
			else if(streak1 == 0 && streak0 < x)
				ans = col[idx] + findOptimal(idx + 1, col, 0, streak0 + 1, x, y, row /*, curr + 0*/);
			else
				ans = INF;
			
			memo.put(s, ans);
			
			return ans;
		}

	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int M = s1.nextInt();
		int x = s1.nextInt();
		int y = s1.nextInt();

		int col[] = new int[M];
		for(int i=0;i<N;i++){
			String line = s1.nextLine();
			for(int j=0;j<M;j++){
				col[j] += line.charAt(j) == '#' ? 1 : 0;
			}	
		}

		//System.out.println(Arrays.toString(col));

		out.println(findOptimal(0, col, 0,0, x, y, N /*, ""*/));

	}
	
	static int DP[][];
	static int prefixSum[];
	// 0 ==> . , 1 ==> #
	static int getCost(int i , int j ,  int color){
		
		if(i > j || j >= M) return INF;
		
		if(color == 0)
			return prefixSum[j + 1] - prefixSum[i];
		else
			return ((j - i + 1) * N) - (prefixSum[j + 1] - prefixSum[i]);
	}
	
	static int findOpt(int idx , int color) {
		if(idx == M)
			return 0;
		else if(idx > M)
			return INF;
		else if(DP[idx][color] != -1)
			return DP[idx][color];
		else {
			int min = INF;
			for(int i=x , end = Math.min(y,M - idx);i <= end;i++)
				min = Math.min(min,getCost(idx, idx + i - 1, color) + findOpt(idx + i, color ^ 1));
			return DP[idx][color] = min;
		}
	}
	static int N , M , x , y;
	private static void solve2(FastScanner s1, PrintWriter out){
		N = s1.nextInt();
		M = s1.nextInt();
		x = s1.nextInt();
		y = s1.nextInt();
		DP = new int[M][2];
		for(int i=0;i<M;i++)
			DP[i][0] = DP[i][1] = -1;
		
		int col[] = new int[M];
		for(int i=0;i<N;i++){
			String line = s1.nextLine();
			for(int j=0;j<M;j++){
				col[j] += line.charAt(j) == '#' ? 1 : 0;
			}	
		}
		prefixSum = new int[M + 1];
		for(int i=1;i<=M;i++)
			prefixSum[i] = prefixSum[i - 1] + col[i - 1];
		
		out.println(Math.min(findOpt(0, 0),findOpt(0, 1)));
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