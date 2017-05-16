import java.util.*;
import java.io.*;
public class MancunianGoesToTheDerby
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer> adj[];
	static ArrayList<Integer> adjInv[];
	static int V;

	static class Pair {
		int u , time;
		Pair(int u , int time){
			this.u = u;
			this.time = time;
		}
	}

	static int[] modifiedDijkstra(){

		int distTo[] = new int[V + 1];
		Arrays.fill(distTo, -1);
		boolean marked[] = new boolean[V + 1];
		PriorityQueue<Pair> pq = new PriorityQueue<>((p1 , p2) -> Integer.compare(p1.time, p2.time));

		pq.add(new Pair(1, 0));
		while(!pq.isEmpty()){

			Pair curr = pq.remove();
			if(!marked[curr.u]){
				marked[curr.u] = true;
				distTo[curr.u] = curr.time;
				
				if(curr.time % 2 == 0){
					
					for(int v:adj[curr.u]){
						if(!marked[v])
							pq.add(new Pair(v, curr.time + 1));
					}
					for(int v:adjInv[curr.u]){
						if(!marked[v])
							pq.add(new Pair(v, curr.time + 2));
					}
				}
				else{
					for(int v:adjInv[curr.u]){
						if(!marked[v])
							pq.add(new Pair(v, curr.time + 1));
					}
					for(int v:adj[curr.u]){
						if(!marked[v])
							pq.add(new Pair(v, curr.time + 2));
					}
				}
			}
		}
		return distTo;
	}


	private static void solve(FastScanner s1, PrintWriter out){

		V = s1.nextInt();
		int E = s1.nextInt();
		int Q = s1.nextInt();
		adj    = (ArrayList<Integer>[]) new ArrayList[V + 1];
		adjInv = (ArrayList<Integer>[]) new ArrayList[V + 1];
		for(int i=1;i<=V;i++){
			adj[i] = new ArrayList<>();
			adjInv[i] = new ArrayList<>();
		}

		while(E-->0){
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adjInv[v].add(u);
		}
		
		int distTo[] = modifiedDijkstra();
		
		while(Q-->0){
			int start = s1.nextInt();
			out.println(distTo[V] <= start && distTo[V] != -1 ? "GGMU" : "FML");
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