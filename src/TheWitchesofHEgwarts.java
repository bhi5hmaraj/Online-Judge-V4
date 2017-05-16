import java.util.*;
import java.io.*;
public class TheWitchesofHEgwarts
{


	/************************ SOLUTION STARTS HERE ***********************/

	static HashMap<Integer,Integer> memo;
	static int findMin(int N) { // Uses a lot of memory
		if(N == 1)
			return 0;
		else if(memo.containsKey(N))
			return memo.get(N);
		else{
			int ans =  1 + Math.min(Math.min(N % 2 == 0 ? findMin(N / 2) : Integer.MAX_VALUE,N % 3 == 0 ?findMin(N / 3) : Integer.MAX_VALUE),findMin(N - 1));
			memo.put(N, ans);
			return ans;
		}
	}
	
	static int findMinSteps(int N) {
		
		HashMap<Integer,Integer> dist = new HashMap<>();
		dist.put(N, 0);
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		while(!queue.isEmpty()) {
			int u = queue.remove();
			int d = dist.get(u);
			if(u == 1)
				return d;
			else {
				if(!dist.containsKey(u - 1)) {
					dist.put(u - 1, d + 1);
					queue.add(u - 1);
				}
				if(u % 3 == 0 && !dist.containsKey(u / 3)) {
					dist.put(u / 3, d + 1);
					queue.add(u / 3);
				}
				if(u % 2 == 0 && !dist.containsKey(u / 2)) {
					dist.put(u / 2, d + 1);
					queue.add(u / 2);
				}
			}
		}

		return 0;
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) 
			out.println(findMinSteps(s1.nextInt()));

	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new TheWitchesofHEgwarts().run();
			}
		}, "Increase Stack", 1 << 27).start();

	}

	void run(){	
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