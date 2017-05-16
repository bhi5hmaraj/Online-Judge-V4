/************************	      ௳  		   ************************/
import java.util.*;
import java.io.*;
public class uva_872
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer>[] adj;
	static boolean tempMarked[] , globalMarked[];
	static int inDegree[];
	static boolean source[];
	static boolean checkDAG() {
		tempMarked = new boolean[26];
		globalMarked = new boolean[26];
		for(int i=0;i<26;i++)
			if(!globalMarked[i] && dfs(i))
				return false;

		return true;
	}

	static boolean dfs(int u) {
		tempMarked[u] = true;
		for(int v : adj[u])
			if(!globalMarked[v]) {
				if(tempMarked[v] || dfs(v))
					return true;
			}
		globalMarked[u] = true;

		return false;
	}
	
	static char buffer[];
	static int sz;
	
	static void topoSort(PrintWriter out) {
		boolean base = true;
		for(boolean b : source)
			base &= !b;

		if(base){
			for(int i=0;i<sz;i++)
				out.print((i == 0 ? "" : " ") + buffer[i]);
			out.println();
		}
		else {
			for(int i=0;i<26;i++)
				if(source[i]) {
					source[i] = false;
					for(int v : adj[i]){
						inDegree[v]--;
						if(inDegree[v] == 0)
							source[v] = true;
					}

					buffer[sz++] = ((char)('A' + i));
					topoSort(out);
					sz--;
					
					source[i] = true;
					for(int v : adj[i]) {
						if(inDegree[v] == 0)
							source[v] = false;
						inDegree[v]++;
					}
				}
		}
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {
			s1.nextLine();
			String alph = s1.nextLine();
			boolean usedAplh[] = new boolean[26];
			source = new boolean[26];
			buffer = new char[26];
			sz = 0;
			inDegree = new int[26];
			for(String ch : alph.split(" "))
				usedAplh[ch.charAt(0) - 'A'] = true;

			StringTokenizer edges = new StringTokenizer(s1.nextLine());
			adj = new ArrayList[26];
			for(int i=0;i<26;i++)
				adj[i] = new ArrayList<>();
			
			
			
			while(edges.hasMoreTokens()){
				String curr = edges.nextToken();
				int from = curr.charAt(0) - 'A';
				int to = curr.charAt(2) - 'A';
				inDegree[to]++;
				adj[from].add(to);
			}

			if(!checkDAG())
				out.println("NO");
			else {
				for(int i=0;i<26;i++)
					if(usedAplh[i] && inDegree[i] == 0)
						source[i] = true;
				topoSort(out);
			}
			
			if(T > 0)
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