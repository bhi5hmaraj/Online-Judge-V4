import java.util.*;
import java.io.*;
public class XorinTree
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer>[] adj;
	static int L[] , R[] , A[] , B[];
	static int clock;
	static int prefixXor[] , level[];
	static int V;
	static int lazy[];
	static int log(int N){
		return 31 - Integer.numberOfLeadingZeros(N);
	}
	
	static int DP[][];
	
	static void buildSparse() {
		for (int i = 1; i < DP.length; i++) {
			for (int j = 1; j <= V; j++) {
				DP[i][j] = DP[i - 1][DP[i - 1][j]];
			}
		}
	}
	
	static int LCA(int u , int v){

		if(level[v] < level[u]){
			int temp = u;
			u = v;
			v = temp;
		}
		int diff = level[v] - level[u];
		while(diff > 0){        // Bring v to the same level as u
			int log = log(diff);
			v = DP[log][v];
			diff -= (1 << log);
		}
		while(u != v){
			int i = log(level[u]);
			for(;i > 0 && DP[i][u] == DP[i][v];)
				i--;

			u = DP[i][u];
			v = DP[i][v];
		}
		
		return u;
	}
	
	static void dfs(int u , int par , int xor , int lev){
		
		L[u] = clock++;
		prefixXor[L[u]] = xor;
		DP[0][L[u]] = L[par];
		level[L[u]] = lev;
		B[L[u]] = A[u];
		for(int v:adj[u]){
			if(v != par)
				dfs(v, u, xor ^ A[v] , lev + 1);
		}
		R[u] = clock - 1;
	}
	
	static void update(int idx , int val){
		update(1, 1, V, L[idx], R[idx], val ^ A[idx]);
		A[idx] = val;
		B[L[idx]] = val;
	}
	static int query(int u , int v){
		u = L[u];
		v = L[v];
		int lca = LCA(u, v);
		return prefixXor[u] ^ prefixXor[v] ^ get(u) ^ get(v) ^ B[lca];
	}
	static int get(int idx){
		return get(1, 1, V, idx);
	}
	static void update(int n , int nl , int nr , int L , int R , int xor){
		
		int nm = (nl + nr) / 2;
		if(nl == L && nr == R){
			lazy[n] ^= xor;
			if(nl != nr){
				lazy[2*n] ^= lazy[n];
				lazy[(2*n) + 1] ^= lazy[n];
				lazy[n] = 0;
			}
		}
		else if(R <= nm)
			update(2*n, nl, nm, L, R, xor);
		else if(L > nm)
			update((2*n) + 1, nm + 1, nr, L, R, xor);
		else{
			update(2*n, nl, nm, L, nm, xor);
			update((2*n) + 1, nm + 1, nr, nm + 1, R, xor);
		}
	}
	
	static int get(int n , int nl , int nr , int idx){
		
		if(nl != nr){
			lazy[2*n] ^= lazy[n];
			lazy[(2*n) + 1] ^= lazy[n];
			lazy[n] = 0;
		}
		int nm = (nl + nr) / 2;
		if(nl == idx && nr == idx)
			return lazy[n];
		else if(idx <= nm)
			return get(2*n, nl, nm, idx);
		else
			return get((2*n) + 1, nm + 1, nr, idx);
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		int t = s1.nextInt();
		while(t-->0){
			clock = 1;
			V = s1.nextInt();
			A = s1.nextIntArrayOneBased(V);
			B = new int[V + 1];
			int E = V - 1;
			L = new int[V + 1];
			R = new int[V + 1];
			prefixXor = new int[V + 1];
			DP = new int[log(V) + 1][V + 1];
			adj = new ArrayList[V + 1];
			level = new int[V + 1];
			int size;
			for(size=1;size<V;size <<= 1)
				;
			size <<= 1;
			lazy = new int[size];
			for(int i=1;i<=V;i++)
				adj[i] = new ArrayList<>();

			while(E-->0){
				int u = s1.nextInt();
				int v = s1.nextInt();
				adj[u].add(v);
				adj[v].add(u);
			}
			
			dfs(1, 0, A[1] , 1);
			buildSparse();
			int Q = s1.nextInt();
			while(Q-->0){
				if(2 == s1.nextInt())
					out.println(query(s1.nextInt(), s1.nextInt()));
				else
					update(s1.nextInt(), s1.nextInt());
			}
			
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