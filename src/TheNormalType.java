import java.util.*;
import java.io.*;
public class TheNormalType
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	static int count(int arr[] , int i , int j){
		HashSet<Integer> set = new HashSet<>();
		for(int k=i;k<=j;k++)
			set.add(arr[k]);
		
		return set.size();
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		
		int cnt = 0;
		int total = count(arr, 0, N-1);
		
		for(int i=0;i<N;i++)
			for(int j=i;j<N;j++)
				cnt += (count(arr, i, j) == total) ? 1 : 0;
		
		out.println(cnt);
	}
	
	private static void solve2(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		HashSet<Integer> unique = new HashSet<>();
		for(int a:arr)
			unique.add(a);
		int distinct = unique.size();
		TreeSet<Integer> set = new TreeSet<>();
		HashMap<Integer,Integer> map = new HashMap<>();
		long cnt = 0;
		for(int i=N-1;i>=0;i--){
			if(map.size() == distinct){
				cnt += (N - set.last());
				set.remove(map.get(arr[i]));
				map.put(arr[i], i);
				set.add(i);
			}
			else {
				if(map.containsKey(arr[i]))
					set.remove(map.get(arr[i]));
				map.put(arr[i], i);
				set.add(i);
			}
		}
		cnt += (N - set.last());
		out.println(cnt);
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