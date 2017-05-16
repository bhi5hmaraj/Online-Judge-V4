import java.util.*;
import java.io.*;
public class uva_10459
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Integer>[] adj;
	static ArrayDeque<Integer> diameter;
	static int[] distFromDiameter , posInDiameter , prev;
	static int farthest , maxDist,diam;
	
	static void dfs(int u , int par , int len) {
		if(len > maxDist) {
			maxDist = len;
			farthest = u;
		}
		prev[u] = par;
		for(int v : adj[u])
			if(v != par)
				dfs(v, u, len + 1);
	}
	
	static void findDiameter() {
		maxDist = 0;
		dfs(1, -1, 0);
		int start = farthest;
		maxDist = 0;
		dfs(start, -1, 0);
		int end = farthest;
		diameter = new ArrayDeque<>();
		diam = maxDist;
		while(end != -1) {
			diameter.push(end);
			posInDiameter[end] = maxDist--;
			end = prev[end];
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line;
		while((line = s1.nextLine()) != null) {
			int V = Integer.parseInt(line);
			adj = new ArrayList[V + 1];
			for(int i=1;i<=V;i++) {
				adj[i] = new ArrayList<>(); 
				// Arrays.stream(s1.nextIntArray(s1.nextInt())).forEach(adj[i]::add);
				for(int a : s1.nextIntArray(s1.nextInt())) adj[i].add(a);
			}
			distFromDiameter = new int[V + 1];
			posInDiameter = new int[V + 1];
			prev = new int[V + 1];
			findDiameter();
			
			// out.println("Diameter " + diameter);

			boolean marked[] = new boolean[V + 1];
			int headInDiameter[] = new int[V + 1];
			ArrayDeque<Integer> queue = new ArrayDeque<>(diameter);
			for(int u : diameter) {
				marked[u] = true;
				headInDiameter[u] = u;
			}
			while(!queue.isEmpty()) {
				int u = queue.remove();
				for(int v : adj[u])
					if(!marked[v]) {
						marked[v] = true;
						distFromDiameter[v] = distFromDiameter[u] + 1;
						headInDiameter[v] = headInDiameter[u];
						queue.add(v);
					}
			}
			
			int height[] = new int[V + 1];
			int min = V + 1;
			int max = 0;
			
			for(int i=1;i<=V;i++) {
				height[i] = distFromDiameter[i] + Math.max(posInDiameter[headInDiameter[i]],diam - posInDiameter[headInDiameter[i]]);
				max = Math.max(max,height[i]);
				min = Math.min(min,height[i]);
			}
			
			// out.println("height " + Arrays.toString(height));
			
			out.print("Best Roots  :");
			for(int i = 1;i<=V;i++)
				if(height[i] == min) 
					out.print(" " + i);
			out.println();
			out.print("Worst Roots :");
			for(int i=1;i<=V;i++)
				if(height[i] == max)
					out.print(" " + i);
			out.println();
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