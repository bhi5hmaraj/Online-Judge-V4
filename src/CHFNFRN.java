import java.util.*;
import java.io.*;
class CHFNFRN
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){ // Slow solution ( O(2^N) )
		
		int t = s1.nextInt();
		while(t-->0)
		{
			int N = s1.nextInt();
			int M = s1.nextInt();
			int mask = (1 << N) - 1;
			if(N <= 30){
				int adj[] = new int[N];
				for(int i=0;i<N;i++)
					adj[i] = 1 << i;
				while(M-->0){
					int u = s1.nextInt() - 1;
					int v = s1.nextInt() - 1;
					adj[u] |= (1 << v);
					adj[v] |= (1 << u);
				}
				/*
				out.println("Graph");
				for(int i=0;i<N;i++)
					out.println(i + " ==> " + Integer.toBinaryString(adj[i]));
				*/
				boolean flag = false;
				
				for(int subset = 1 ; subset < (1 << N) ; subset++){
					boolean cond = true;
					int fliped = mask ^ subset;
					for(int i=0;i<N;i++){
						if((subset & (1 << i)) != 0)
							cond &= (subset & adj[i]) == subset;
						else
							cond &= (fliped & adj[i]) == fliped;
					}
					if(cond){
						flag = true;
						break;
					}
				}
				
				out.println(flag ? "YES" : "NO");
			}
			else
				throw new RuntimeException("Work in progress");
		}
		
	}
	
	private static void solve2(FastScanner s1, PrintWriter out){  // Flawed , necessary but not sufficient condition
		
		int t = s1.nextInt();
		while(t-->0){
			
			int N = s1.nextInt();
			int M = s1.nextInt();
			BitSet adj[] = new BitSet[N + 1];
			for(int i=1;i<=N;i++){
				adj[i] = new BitSet(N + 1);
				adj[i].set(i);
			}
			while(M-->0){
				int u = s1.nextInt();
				int v = s1.nextInt();
				adj[u].set(v);
				adj[v].set(u);
			}
			boolean flag = true;
			for(int i=1;i<=N;i++){
				// out.println("i " + i);
				BitSet notCon = ((BitSet)adj[i].clone());
				notCon.flip(1, N + 1);
				// out.println("Not conn " + notCon);
				BitSet mask = new BitSet(N + 1);
				mask.set(1, N + 1);
				for(int j=1;j<=N;j++){
					if(notCon.get(j))
						mask.and(adj[j]);
				}
				// out.println("mask " + mask);
				mask.and(notCon);
				if(!mask.equals(notCon)){
					flag = false;
					break;
				}
			}
			
			out.println(flag ? "YES" : "NO");
		}
		
	}
	
	static ArrayList<Integer> invAdj[];
	static boolean color[] , marked[];
	static boolean isBipartite(int start){
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		marked[start] = true;
		while(!queue.isEmpty()){
			int curr = queue.remove();
			for(int v : invAdj[curr]){
				if(marked[v]){
					if(color[v] == color[curr])
						return false;
				}
				else {
					marked[v] = true;
					color[v] = !color[curr];
					queue.add(v);
				}
			}
		}
		
		return true;
	}
/*	
	static boolean isBipartite(int u){
		marked[u] = true;
		boolean flag = true;
		for(int v:invAdj[u]){
			if(marked[v])
				flag &= (color[v] != color[u]);
			else {
				color[v] = !color[u];
				flag &= isBipartite(v);
			}
		}
		return flag;
	}
*/	
	@SuppressWarnings("unchecked")
	private static void solve3(FastScanner s1, PrintWriter out){ // Fast bfs O(V + E)
		int t = s1.nextInt();
		while(t-->0){
			int N = s1.nextInt();
			int M = s1.nextInt();
			HashSet<Integer> adj[] = new HashSet[N + 1];
			invAdj = new ArrayList[N + 1];
			color = new boolean[N + 1];
			marked = new boolean[N + 1];
			for(int i=1;i<=N;i++){
				adj[i] = new HashSet<>();
				invAdj[i] = new ArrayList<>();
			}
			while(M-->0){
				int u = s1.nextInt();
				int v = s1.nextInt();
				adj[u].add(v);
				adj[v].add(u);
			}
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(j != i){
						if(!adj[i].contains(j))
							invAdj[i].add(j);
					}
				}
			}
			
			boolean flag = true;
			for(int i=1;i<=N;i++){
				if(!marked[i] && !isBipartite(i)){
					flag = false;
					break;
				}
			}
			
			out.println(flag ? "YES" : "NO");
		}
	}
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve3(in, out);
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