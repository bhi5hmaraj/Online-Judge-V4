import java.util.*;
import java.io.*;
public class GridlandMetro
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Pair implements Comparable<Pair> {
		long coord;
		int isStart;
		Pair(long c , int s) {
			coord = c;
			isStart = s;
		}
		@Override
		public int compareTo(Pair o) {
			return coord != o.coord ? Long.compare(coord, o.coord) : Integer.compare(isStart, o.isStart); 
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		long N = s1.nextLong();
		long M = s1.nextLong();
		int K = s1.nextInt();
		HashMap<Integer , ArrayList<Pair>> track = new HashMap<>();
		for(int i=0;i<K;i++) {
			int r = s1.nextInt();
			ArrayList<Pair> arl = track.get(r);
			if(arl == null)
				arl = new ArrayList<>();
			arl.add(new Pair(s1.nextInt(), 1));
			arl.add(new Pair(s1.nextInt(), 2));
			track.put(r, arl);
		}
		long total = N * M;
		for(Map.Entry<Integer, ArrayList<Pair>> e : track.entrySet()) {
			ArrayList<Pair> arl = e.getValue();
			Collections.sort(arl);
			int cnt = 0;
			long curr = -1;
			for(Pair p : arl) {
				if(cnt == 0) 
					curr = p.coord;

				if(p.isStart == 1)
					cnt++;
				else
					cnt--;
				
				if(cnt == 0) 
					total -= (p.coord - curr + 1);
			}
		}
		
		out.println(total);
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