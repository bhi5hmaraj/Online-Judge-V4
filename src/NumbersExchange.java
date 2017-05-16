import java.util.*;
import java.io.*;
public class NumbersExchange
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int M = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		
		HashSet<Integer> evens = new HashSet<>();
		HashSet<Integer> odds = new HashSet<>();
		for(int a : arr)
			if(a % 2 == 0)
				evens.add(a);
			else
				odds.add(a);
		
		if(evens.size() == N / 2 && odds.size() == N / 2) {
			out.println(0);
			Arrays.stream(arr).forEach(a -> out.print(a + " "));
		}
		else if(evens.size() >= N / 2) {
			ArrayList<Integer> toBeReplaced = new ArrayList<>();
			HashSet<Integer> blacklist = new HashSet<>();
			HashSet<Integer> filter[] = new HashSet[2];
			filter[0] = new HashSet<>();
			filter[1] = new HashSet<>();
			for(int i=0;i<N;i++) {
				int j = arr[i] % 2;
				if(filter[j].contains(arr[i]) || filter[j].size() == N / 2)
					toBeReplaced.add(i);
				else
					filter[j].add(arr[i]);
				
				if(arr[i] % 2 == 1 && arr[i] <= M)
					blacklist.add(arr[i]);
			}
			
			int nextOdd = 1; 
			for(int i : toBeReplaced) {
				while(nextOdd <= M && blacklist.contains(nextOdd)) nextOdd += 2;
				
				if(nextOdd > M) {
					out.println(-1);
					return;
				}
				else
					arr[i] = nextOdd;
				nextOdd += 2;
			}
			
			out.println(toBeReplaced.size());
			Arrays.stream(arr).forEach(a -> out.print(a + " "));
		}
		else if(odds.size() >= N / 2) {
			ArrayList<Integer> toBeReplaced = new ArrayList<>();
			HashSet<Integer> blacklist = new HashSet<>();
			HashSet<Integer> filter[] = new HashSet[2];
			filter[0] = new HashSet<>();
			filter[1] = new HashSet<>();
			for(int i=0;i<N;i++) {
				int j = arr[i] % 2;
				if(filter[j].contains(arr[i]) || filter[j].size() == N / 2)
					toBeReplaced.add(i);
				else
					filter[j].add(arr[i]);
				
				if(arr[i] % 2 == 0 && arr[i] <= M)
					blacklist.add(arr[i]);
			}
			
			// out.println("replace " + toBeReplaced);
			
			int nextEven = 2; 
			for(int i : toBeReplaced) {
				while(nextEven <= M && blacklist.contains(nextEven)) nextEven += 2;
				
				if(nextEven > M) {
					out.println(-1);
					return;
				}
				else
					arr[i] = nextEven;
				nextEven += 2;
			}
			
			out.println(toBeReplaced.size());
			Arrays.stream(arr).forEach(a -> out.print(a + " "));
		}
		else {
			ArrayList<Integer> toBeReplaced = new ArrayList<>();
			HashSet<Integer> oddBlacklist = new HashSet<>();
			HashSet<Integer> evenBlacklist = new HashSet<>();
			HashSet<Integer> filter = new HashSet<>();
			int oddRem = N / 2;
			for(int i=0;i<N;i++) {
				if(filter.contains(arr[i]))
					toBeReplaced.add(i);
				else {
					if(arr[i] % 2 == 1)
						oddRem--;
					
					filter.add(arr[i]);
				}
				
				if(arr[i] <= M) {
					if(arr[i] % 2 == 0)
						evenBlacklist.add(arr[i]);
					else
						oddBlacklist.add(arr[i]);
				}
			}
			
			int nextOdd = 1 , nextEven = 2;
			// out.println("replace " + toBeReplaced);
			for(int i : toBeReplaced) {
				if(oddRem > 0) {
					while(nextOdd <= M && oddBlacklist.contains(nextOdd)) nextOdd += 2;
					
					if(nextOdd > M) {
						out.println(-1);
						return;
					}
					else
						arr[i] = nextOdd;
					nextOdd += 2; 
					oddRem--;
				}
				else {
					while(nextEven <= M && evenBlacklist.contains(nextEven)) nextEven += 2;
					
					if(nextEven > M) {
						out.println(-1);
						return;
					}
					else
						arr[i] = nextEven;
					nextEven += 2;
				}
			}
			out.println(toBeReplaced.size());
			Arrays.stream(arr).forEach(a -> out.print(a + " "));
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