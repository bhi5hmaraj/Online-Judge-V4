import java.util.*;
import java.io.*;
public class Evaluation
{


	/************************ SOLUTION STARTS HERE ***********************/

	private static HashMap<String , HashSet<String>> adj;
	private static HashSet<String> visitedGlobal;
	private static HashSet<String> visitedTemp;
	private static HashSet<String> all;
	private static boolean dfs(String u){

		visitedGlobal.add(u);
		visitedTemp.add(u);
		if(adj.containsKey(u)) {
			for(String v:adj.get(u)){

				if(visitedTemp.contains(v))
					return true;

				if(!visitedGlobal.contains(v)){
					if(dfs(v))
						return true;
				}

			}
		}
		visitedTemp.remove(u);
		return false;

	}


	static void parseGraph(String[] lines) {

		for(String str : lines) {

			String v = str.substring(0, str.indexOf('='));
			String u[] = str.substring(str.indexOf('(') + 1, str.indexOf(')')).split(",");
			/*System.out.println(" u[] " + Arrays.toString(u));
			System.out.println("v " + v);*/
			all.add(v);
			for(String from : u) {
				all.add(from);
				HashSet<String> set = adj.get(from);
				if(set == null)
					set = new HashSet<>();
				set.add(v);
				adj.put(from, set);
			}
		}

	}

	private static void solve(FastScanner s1, PrintWriter out){

		int t = s1.nextInt();
		for(int T = 1 ; T <= t ; T++) {

			adj = new HashMap<>();
			visitedGlobal = new HashSet<>();
			visitedTemp = new HashSet<>();
			all = new HashSet<>();
			int N = s1.nextInt();
			String lines[] = new String[N];
			for(int i=0;i<N;i++)
				lines[i] = s1.nextLine();
			parseGraph(lines);
			/*
			for(Map.Entry<String, HashSet<String>> e : adj.entrySet()){
				System.out.println(e.getKey() + "==>" + e.getValue());
			}
			*/
			if(dfs(""))
				out.println("Case #"+T+": BAD");
			else {
				boolean flag = true;
				for(String s : all)
					flag &= visitedGlobal.contains(s);
				out.println("Case #"+T+": " + (flag ? "GOOD" : "BAD"));
			}
		}

	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/ new FileInputStream("input.txt"));
		PrintWriter out = 
				new PrintWriter(/*new BufferedWriter(new OutputStreamWriter(System.out)), false*/ "out.txt"); 
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