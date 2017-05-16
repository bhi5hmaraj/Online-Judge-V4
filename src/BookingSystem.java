import java.util.*;
import java.io.*;
public class BookingSystem
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class MultiSet<T> extends TreeMap<T, Queue<Integer>> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -280519091270016431L;

		public void add(T key,int index) {
			Queue<Integer> q = super.get(key);
			if (q == null)
				q = new ArrayDeque<>();
			q.add(index);
			super.put(key, q);
		}

		public Integer removeKey(T key) {
			
			if(key == null)
				return null;
			
			Queue<Integer> q = super.get(key);
			if (q != null) {
				int index = q.remove();
				if(q.size() == 0)
					super.remove(key);
				else
					super.put(key, q);
				
				return index;
			} else
				return null;
			
		}
	}
	
	static class Pair {
		int cost , capacity , index;
		Pair(int index,int capacity , int cost){
			this.index = index;
			this.cost = cost;
			this.capacity = capacity;
		}
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		Pair arr[] = new Pair[N];
		for(int i=0;i<N;i++)
			arr[i] = new Pair(i + 1, s1.nextInt(), s1.nextInt());
		
		int K = s1.nextInt();
		Arrays.sort(arr, (p1 , p2) -> Integer.compare(p2.cost, p1.cost));
		
		MultiSet<Integer> mSet = new MultiSet<>();
		
		for(int i=1;i<=K;i++)
			mSet.add(s1.nextInt(), i);
		
		ArrayList<Integer> arl1 = new ArrayList<>();
		ArrayList<Integer> arl2 = new ArrayList<>();
		int totalCost = 0;
		
		for(int i=0;i<N;i++){
			Integer index = mSet.removeKey((mSet.ceilingKey(arr[i].capacity)));
			if(index != null){
				totalCost += arr[i].cost;
				arl1.add(arr[i].index);
				arl2.add(index);
			}
		}
		
		out.println(arl1.size() + " " + totalCost);
		for(int i=0,len = arl1.size();i < len;i++)
			out.println(arl1.get(i) + " " + arl2.get(i));
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