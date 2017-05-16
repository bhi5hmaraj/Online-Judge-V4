import java.util.*;
import java.io.*;
public class NotSoSimpleFunction
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		
		HashMap<Integer , Long> cntGCD = new HashMap<>();
		
		HashMap<Integer , Long> currSuffix = new HashMap<>();
		for(int a : arr) {
			
			HashMap<Integer , Long> temp = new HashMap<>();
			for(Map.Entry<Integer, Long> e : currSuffix.entrySet()) {
				int newGCD = gcd(e.getKey(), a);
				temp.put(newGCD, temp.getOrDefault(newGCD, 0L) + e.getValue());
			}
			temp.put(a, temp.getOrDefault(a, 0L) + 1);
			
			for(Map.Entry<Integer, Long> e : temp.entrySet())
				cntGCD.put(e.getKey(), cntGCD.getOrDefault(e.getKey(), 0L) + e.getValue());
			
			currSuffix = temp;
			// out.println("temp " + temp);
		}
		// out.println("cntGcd "  + cntGCD);
		
		TreeSet<Integer> set = new TreeSet<>();
		for(Map.Entry<Integer, Long> e : cntGCD.entrySet())
			set.add(e.getKey());
		
		long prefixSum[] = new long[set.size() + 1];
		TreeMap<Integer , Integer> map = new TreeMap<>();
		int ptr = 1;
		for(int a : set) {
			prefixSum[ptr] = (prefixSum[ptr - 1] + cntGCD.get(a));
			map.put(a, ptr);
			ptr++;
		}
		
		
		int Q = s1.nextInt();
		while(Q-->0) {
			int L = s1.nextInt();
			int R = s1.nextInt();
			Map.Entry<Integer, Integer> left = map.ceilingEntry(L);
			Map.Entry<Integer, Integer> right = map.floorEntry(R);
			if(left == null || right == null || left.getValue() > right.getValue())
				out.println(0);
			else
				out.println(prefixSum[right.getValue()] - prefixSum[left.getValue() - 1]);
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