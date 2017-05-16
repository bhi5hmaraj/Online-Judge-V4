import java.util.*;
import java.io.*;
public class Thor
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Pair {
		int index , type;
		Pair(int index , int type){
			this.index = index;
			this.type = type;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int Q = s1.nextInt();
		int unRead = 0;
		int cnt = 0;
		boolean removed[] = new boolean[Q + 10];
		ArrayDeque<Integer>[] app = new ArrayDeque[N + 1];
		for(int i=1;i<=N;i++)
			app[i] = new ArrayDeque<>();
		
		ArrayDeque<Pair> queue = new ArrayDeque<>();
		while(Q-->0){
			int choice = s1.nextInt();
			if(choice == 1){
				unRead++;
				int type = s1.nextInt();
				app[type].add(cnt);
				queue.add(new Pair(cnt, type));
				cnt++;
			}
			else if(choice == 2){
				int x = s1.nextInt();
				unRead -= app[x].size();
				for(int pos:app[x])
					removed[pos] = true;
				app[x].clear();
			}
			else{
				Pair p = queue.pollFirst();
				int read = 0;
				int t = s1.nextInt();
				while(p != null && p.index < t){
					if(!removed[p.index]){
						app[p.type].remove();
						removed[p.index] = true;
						read++;
					}
					p = queue.pollFirst();
				}
				
				if(p != null)
					queue.addFirst(p);
				
				unRead -= read;
			}
			
			out.println(unRead);
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