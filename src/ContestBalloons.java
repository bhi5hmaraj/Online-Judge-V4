import java.util.*;
import java.io.*;
public class ContestBalloons
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Pair implements Comparable<Pair>{
		long balloons , weight;
		Pair(long b , long w) {
			balloons = b;
			weight = w;
		}
		@Override
		public int compareTo(Pair o) {
			return Long.compare(balloons, o.balloons);
		}
		@Override
		public String toString() {
			return String.format("[balloons = %d weight = %d]", balloons , weight);
		}
	}

	static int upper_bound(Pair arr[] , Pair key) {
		int ub = arr.length;
		int lo = 0 , hi = arr.length - 1;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(key.compareTo(arr[mid]) == -1) {
				ub = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ub;
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt() - 1;
		Pair me = new Pair(s1.nextLong(), s1.nextLong());

		Pair arr[] = new Pair[N];
		for(int i=0;i<N;i++)
			arr[i] = new Pair(s1.nextLong(), s1.nextLong());

		Arrays.sort(arr);
		int ub = upper_bound(arr, me); // Points at the smallest large valuex
		PriorityQueue<Long> candidates = new PriorityQueue<>();
		for(int i=ub;i<N;i++)
			candidates.add(arr[i].weight - arr[i].balloons + 1);
		
		int bestRank = candidates.size();
		
		long canGive = me.balloons - ((ub == 0) ? 0 : arr[ub - 1].balloons);
		ub--; // From now onward ub points to the place I'm currently at
		while(true) {
			// out.println("candidates " + candidates);
			// out.println("canGive " + canGive);
			// out.println("rank " + (bestRank + 1));
			while(canGive > 0 && !candidates.isEmpty()) {
				long diff = candidates.remove();
				long removed = Math.min(diff,canGive);
				diff -= removed;
				me.balloons -= removed;
				canGive -= removed;
				if(diff > 0)
					candidates.add(diff);
			}
			// out.println("deleted " + deleted + " ub " + ub + " me " + me);
			bestRank = Math.min(bestRank,candidates.size());
			if(candidates.isEmpty()) break;
			if(me.balloons == 0) break;
			// ub--;
			int next = ub;
			while(ub >= 0 && arr[ub].balloons == arr[next].balloons){					
				candidates.add(arr[ub].weight - arr[ub].balloons + 1);
				ub--;
			}

			if(ub >= 0)
				canGive = me.balloons - arr[ub].balloons;
			else
				canGive = me.balloons ;
		}
		
		out.println(bestRank + 1);

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