import java.util.*;
import java.io.*;
public class Maze
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int x[] = {-1 , 0 , 1 , 0}; //up , right , down and left 
	static int y[] = {0 , 1 , 0 , -1};
	
	static int N , M;
	static boolean isValid(int i, int j){
		return i >= 0 && i < N && j >= 0 && j < M;
	}
	static int get1D(int i , int j){
		return (i * M) + j;
	}
	static ArrayList<Integer>[] adj;
	static boolean marked[];
	static int cnt = 0;
	static void dfs(int u ,int total){
		cnt++;
		if(cnt > total)
			return;
		
		marked[u] = true;
		
		for(int v:adj[u])
			if(!marked[v])
				dfs(v, total);
		
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		M = s1.nextInt();
		int K = s1.nextInt();
		adj = new ArrayList[N * M];
		char arr[][] = new char[N][];
		int root = -1;
		for(int i=0;i<N;i++)
			arr[i] = s1.nextLine().toCharArray();
		
		for(int i=0;i<(N*M);i++)
			adj[i] = new ArrayList<>();
		
		int S = 0;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(arr[i][j] == '.'){
					S++;
					int u = get1D(i, j);
					root = u;
					for(int k=0;k<4;k++){
						if(isValid(i+x[k], j+y[k]) && arr[i+x[k]][j+y[k]] == '.'){
							int v = get1D(i + x[k], j + y[k]);
							adj[u].add(v);
							adj[v].add(u);
						}
					}
				}
			}	
		}
		
		if(root != -1){
			marked = new boolean[N*M];
			dfs(root, S - K);
			for(int i=0;i<N;i++){
				for(int j=0;j<M;j++){
					if(arr[i][j] == '.' && !marked[get1D(i, j)])
						arr[i][j] = 'X';
				}	
			}
		}
		
		for(int i=0;i<N;i++)
			out.println(new String(arr[i]));
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