import java.util.*;
import java.io.*;
public class SearchEngine
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final char START = 'a';
	static final char END = '{';
	
	static class Node {
		int max;
		char ch;
		Node adj[];
		Node(char ch){
			this.ch = ch;
			if(ch != END)
				adj = new Node[27];
		}
		@Override
		public String toString() {
			return "ch " + ch + " max " + max;
		}
	}
	
	static class Trie {
		
		Node root;
		Trie(String arr[] , int weight[]){
		
			root = new Node('*');
			for(int i=0;i<weight.length;i++)
				insert(arr[i], 0, arr[i].length(), root, weight[i]);
		
		}
		void insert(String str , int idx , int len , Node curr , int wt){
			if(idx < len){
				Node adj = curr.adj[str.charAt(idx) - START];
				if(adj == null){
					adj = new Node(str.charAt(idx));
					curr.adj[str.charAt(idx) - START] = adj;
				}
				adj.max = Math.max(adj.max,wt);
				insert(str, idx + 1, len, adj, wt);
			}
		}
		
		int findMax(String str){
			
			Node curr = root;
			for(int i=0,len = str.length();i < len && curr != null;i++)
				 curr = curr.adj[str.charAt(i) - START];
			
			return curr != null ? curr.max : -1;
		}
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int Q = s1.nextInt();
		
		String arr[] = new String[N];
		int weight[] = new int[N];
		String end = String.valueOf(END);
		for(int i=0;i<N;i++){
			arr[i] = s1.next().concat( end );
			weight[i] = s1.nextInt();
		}
		
		Trie trie = new Trie(arr, weight);
		
		while(Q-->0)
			out.println(trie.findMax(s1.nextLine()));
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