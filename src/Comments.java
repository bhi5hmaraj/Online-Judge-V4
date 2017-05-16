import java.util.*;
import java.io.*;
public class Comments
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Node {
		String str;
		int lev;
		Node(String str , int lev) {
			this.str = str;
			this.lev = lev;
		}
	}
	
	static ArrayList<Node> comments;
	
	static int createTree(StringTokenizer st , int level) {
		String name = st.nextToken();
		int child = Integer.parseInt(st.nextToken());
		comments.add(new Node(name, level));
		int max = level;
		while(child-->0)
			max = Math.max(max,createTree(st, level + 1));
		
		return max;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String str = s1.nextLine();
		StringTokenizer st = new StringTokenizer(str, ",");
		comments = new ArrayList<>();
		int maxDepth = 0;
		while(st.hasMoreTokens())
			maxDepth = Math.max(maxDepth,createTree(st, 1));
		
		Collections.sort(comments, (n1 , n2) -> Integer.compare(n1.lev, n2.lev));
		out.println(maxDepth);
		int curr = 1;
		for(Node n : comments) {
			if(n.lev == curr)
				out.print(n.str + " ");
			else {
				out.println();
				out.print(n.str + " ");
				curr = n.lev;
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}