import java.util.*;
import java.io.*;
public class uva_929
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int dx[]={1,0,-1,0};static int dy[]={0,1,0,-1}; 
	
	static int R , C;
	static boolean isValid(int i , int j) { return i >= 0 && i < R && j >= 0 && j < C; }
	
	static int grid[][];
	static boolean marked[][];
	static class Pair implements Comparable<Pair>{
		int x , y , dist;
		Pair(int x , int y , int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Pair o) {
			if(dist != o.dist)
				return dist - o.dist;
			else if(x != o.x)
				return x - o.x;
			else
				return y - o.y;
		}
	}
	static int dijkstra(Pair start) {  // Dependency adj (adjacency list of type Edge) , vertex count V 
		//PriorityQueue<Pair> pq = new PriorityQueue<>((p1 , p2) -> Integer.compare(p1.dist, p2.dist));
		// TreeSet<Pair> pq = new TreeSet<>(); // TreeSet is slow 
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(start);
		while (!pq.isEmpty()) {
			Pair min = pq.remove();
			// Pair min = pq.pollFirst();
			int x = min.x;
			int y = min.y;
			if(!marked[x][y]){
				marked[x][y] = true;
				if(x == R - 1 && y == C - 1)
					return min.dist;
				for(int i=0;i<4;i++)
					if (isValid(x+dx[i], y+dy[i]) && !marked[x+dx[i]][y+dy[i]])
						pq.add(new Pair(x+dx[i], y+dy[i], min.dist + grid[x+dx[i]][y+dy[i]]));
			}
		}

		return Integer.MAX_VALUE;
	}
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			R = s1.nextInt();
			C = s1.nextInt();
			grid = new int[R][];
			marked = new boolean[R][C];
			for(int i=0;i<R;i++)
				grid[i] = s1.nextIntArray(C);
			
			out.println(dijkstra(new Pair(0, 0, grid[0][0])));
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