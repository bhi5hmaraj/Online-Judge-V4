import java.util.*;
import java.io.*;
public class InverseRMQ
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static boolean remove(TreeMap<Integer , Integer> map , int key) {
		if(map.containsKey(key)) {
			int freq = map.get(key);
			if(freq == 1)
				map.remove(key);
			else
				map.put(key, freq - 1);
			
			return true;
		}
		else
			return false;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArray((2 * N) - 1);
		if(N == 1) {
			out.println("YES");
			out.println(arr[0]);
			return;
		}
		TreeMap<Integer,Integer> multiSet = new TreeMap<>();
		for(int a : arr)
			multiSet.put(a, multiSet.getOrDefault(a, 0) + 1);
		
		if(multiSet.size() < N)
			out.println("NO");
		else {
			int ptr = arr.length - N;
			int cnt = 0;
			for(Map.Entry<Integer, Integer> e : multiSet.entrySet()) {
				if(ptr < arr.length)
					arr[ptr++] = e.getKey();
				else
					cnt += e.getValue();
			}
			for(int i=(arr.length-2)/2;i >= 0;i--)
				arr[i] = Math.min(arr[(2*i) + 1],arr[(2*i) + 2]);
			
			HashMap<Integer , Integer> need = new HashMap<>();
			for(int a : arr)
				need.put(a, need.getOrDefault(a, 0) + 1);
			for(Map.Entry<Integer, Integer> e : need.entrySet())
				cnt += (multiSet.get(e.getKey()) - e.getValue());
			if(cnt == 0){
				out.println("YES");
				for(int a : arr)
					out.print(a + " ");
			}
			else
				out.println("NO");
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