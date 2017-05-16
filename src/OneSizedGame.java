import java.util.*;
import java.io.*;
public class OneSizedGame
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			int arr[] = s1.nextIntArray(N);
			ArrayList<Integer> curr = new ArrayList<>();
			for(int a : arr) curr.add(a);
			
			while(curr.size() > 1) {
				int minPos = 0;
				// out.println("curr " + curr);
				for(int i=0,sz = curr.size();i < sz;i++)
					minPos = curr.get(i) < curr.get(minPos) ? i : minPos;
				
				int times = (curr.get(minPos) / (minPos + 1)) + 1;
				
				ArrayList<Integer> temp = new ArrayList<>();
				for(int a : curr)
					if(a - ((minPos + 1) * times) >= 0)
						temp.add(a - ((minPos + 1) * times));
				
				curr = temp;
			}
			
			out.println(curr.size() == 1 ? "Ladia" : "Kushagra");
		}
		
	}
	
	static class Pair implements Comparable<Pair> {
		int index ;
		long val;
		Pair(int index , int val){
			this.index = index;
			this.val = val;
		}
		
		@Override
		public int compareTo(Pair o) {
			return Long.compare(val, o.val);
		}
	}
	
	static class FenwickTree {  // In this variant of BIT each query is **NOT** a cumulative sum , it is the actual freq
		// Point Query and Range update
		int tree[];
		int len;
		FenwickTree(int len) {
			this.len = len;
			tree = new int[len + 10];
		}
		void update(int idx , int val) {
			for(;idx <= len;idx += (idx & -idx))
				tree[idx] += val;
		}
		int query(int idx) {
			int sum = 0;
			for(;idx > 0;idx -= (idx & -idx))
				sum += tree[idx];

			return sum;
		}
	}
	private static void solve2(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			Pair arr[] = new Pair[N];
			for(int i=0;i<N;i++)
				arr[i] = new Pair(i + 1, s1.nextInt());
			
			Arrays.sort(arr);
			
			FenwickTree BIT = new FenwickTree(N);
			
			long sum = 0;
			for(int i=0;i<N-1;i++) {
				// out.printf("Before sum = %d arr[%d] = %d \n", sum , i + 1, arr[i].val);
				if(arr[i].val - sum >= 0) {
					arr[i].val -= sum;
					int newIndex = arr[i].index - BIT.query(arr[i].index);
					sum += ( arr[i].val + (newIndex - (arr[i].val % newIndex)) );
				}
				BIT.update(arr[i].index, 1); // silly mistake !! 
				
				// out.printf("After sum = %d arr[%d] = %d \n", sum ,i + 1, arr[i].val);
			}
			
			out.println(arr[N - 1].val - sum >= 0 ? "Ladia" : "Kushagra");
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