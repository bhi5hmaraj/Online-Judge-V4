import java.util.*;
import java.io.*;
public class MishaandForest
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Pair {
		int a , b;
		Pair(int a , int b){
			this.a = a;
			this.b = b;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		Pair arr[] = new Pair[N];
		ArrayList<Pair> edges = new ArrayList<>();
		for(int i=0;i<N;i++)
			arr[i] = new Pair(s1.nextInt(), s1.nextInt());

		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for(int i=0;i<N;i++)
			if(arr[i].a == 1)
				queue.add(i);

		while(!queue.isEmpty()){
			int curr = queue.remove();
			if(arr[curr].a > 0){
				Pair parent = arr[arr[curr].b];
				parent.b ^= curr;
				parent.a--;
				edges.add(new Pair(curr, arr[curr].b));
				if(parent.a == 1)
					queue.add(arr[curr].b);
			}
		}

		out.println(edges.size());
		for(Pair p:edges)
			out.println(p.a + " " + p.b);
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