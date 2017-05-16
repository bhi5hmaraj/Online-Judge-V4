import java.util.*;
import java.io.*;
public class RandomizedMinCutAssignment
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class DSU {
		private int parent[];
		private int size[];
		private int cnt;

		DSU(int length) {
			this.cnt = length;
			parent = new int[length + 10];
			size = new int[length + 10];
			Arrays.fill(size, 1);
			for (int i = 0; i < parent.length; i++)
				parent[i] = i;
		}

		int root(int p) {
			return (parent[p] == p) ? p : (parent[p] = root(parent[p]));
		}

		int sizeOf(int p) {
			return size[root(p)];
		}

		boolean connected(int u, int v) {
			return root(u) == root(v);
		}

		int components() {
			return cnt;
		}

		void union(int u, int v) {
			if (!connected(u, v)) {
				cnt--;
				int rootU = root(u);
				int rootV = root(v);
				if (size[rootU] < size[rootV]) {
					parent[rootU] = rootV;
					size[rootV] += size[rootU];
				} else {
					parent[rootV] = rootU;
					size[rootU] += size[rootV];
				}
			}
		}
	}
	
	static class Edge {
		int u , v;
		Edge(int u , int v) {
			this.u = u;
			this.v = v;
		}
		@Override
		public String toString() {
			return String.format("[%d --- %d]", u , v);
		}
	}
	
	static class KargerRandomizedMinCut {
		private Edge arr[];
		private int V , E;
		private int TRIALS;
		private int minCut;
		private ArrayList<Edge> minCutEdges;
		void shuffle() {
			Random random = new Random();
			for (int i = E- 1; i > 0; i--) {
				int index = random.nextInt(i + 1);
				Edge temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;
			}
		}
		public KargerRandomizedMinCut(Edge arr[] ,int V, int TRIALS) {
			this.arr = arr;
			this.TRIALS = TRIALS;
			this.V = V;
			E = arr.length;
		}
		
		int getMinCut() {
			minCut = Integer.MAX_VALUE;
			while(TRIALS-->0) {
				DSU dsu = new DSU(V);
				ArrayList<Edge> possibleMinCutEdges = new ArrayList<>();
				shuffle();
				int ptr = 0;
				while(dsu.components() > 2) {
					dsu.union(arr[ptr].u, arr[ptr].v);
					ptr++;
				}
				
				int cut = E - ptr;
				while(ptr < E){
					if(dsu.connected(arr[ptr].u, arr[ptr].v))
						cut--;
					else
						possibleMinCutEdges.add(arr[ptr]);
					ptr++;
				}
				
				if(cut < minCut) {
					minCut = cut;
					minCutEdges = possibleMinCutEdges;
				}
			}
			return minCut;
		}
		
		ArrayList<Edge> getMinCutEdges() {
			return minCutEdges;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
/*		
		int V = s1.nextInt();
		int E = s1.nextInt();
		Edge arr[] = new Edge[E];
		for(int i=0;i<E;i++)
			arr[i] = new Edge(s1.nextInt(), s1.nextInt());
		
*/
		int V = 200;
		boolean marked[][] = new boolean[V + 1][V + 1];
		ArrayList<Edge> arl = new ArrayList<>();
		for(int i=1;i<=V;i++) {
			int adj[] = Arrays.stream(s1.nextLine().split("\t")).mapToInt(Integer::parseInt).toArray();
			for(int j=1;j<adj.length;j++)
				if(!marked[i][adj[j]] && !marked[adj[j]][i]) {
					marked[i][adj[j]] = true;
					arl.add(new Edge(i, adj[j]));
				}
		}
		
		Edge arr[] = new Edge[arl.size()];
		for(int i=0;i<arr.length;i++)
			arr[i] = arl.get(i);
		
		long start = System.nanoTime();
		KargerRandomizedMinCut minCut = new KargerRandomizedMinCut(arr, V, V * V); // V^2 has 36% chance of failing
		// Recomended trials = V^2 ln V , probability of failing = 1 / V
		out.println(minCut.getMinCut());
		long stop = System.nanoTime();
		out.println(minCut.getMinCutEdges());

		out.println("Time : " + ((stop - start) / 1e9));
	
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/new FileInputStream("kargerMinCut.txt"));
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