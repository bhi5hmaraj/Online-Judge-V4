import java.util.*;
import java.io.*;
public class Subway
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static HashMap<String , Integer> map;
	static ArrayList<Integer>[] adj;
	
	static int line[];
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			
			s1.nextLine();
			String stops[] = s1.nextLine().substring(7).split(", ");
			String lines[] = s1.nextLine().substring(7).split(", ");
			
			// System.out.println(Arrays.toString(stops));
			// System.out.println(Arrays.toString(lines));
			int V = stops.length;
			line = new int[V];
			adj = new ArrayList[V];
			for(int i=0;i<V;i++) adj[i] = new ArrayList<>();
			map = new HashMap<>();
			for(String s : stops)
				map.put(s, map.size());
			
			// System.out.println(map);
			
			for(int i=0;i<lines.length;i++) {
				String route[] = s1.nextLine().substring(8 + lines[i].length()).split(", ");
				// System.out.println(Arrays.toString(route));
				for(int j=0;j<route.length-1;j++) {
					int u = map.get(route[j]);
					int v = map.get(route[j + 1]);
					line[u] = line[v] = i;
					adj[u].add(v);
					adj[v].add(u);
				}
			}
			
			String from = s1.nextLine().substring("Johny lives at ".length());
			String to = s1.nextLine().substring("Michelle lives at ".length());
			int start = map.get(from);
			int end = map.get(to);
			
			
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