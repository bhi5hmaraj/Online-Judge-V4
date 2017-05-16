import java.util.*;
import java.io.*;
public class SantaClausandaPalindrome
{


	/************************ SOLUTION STARTS HERE ***********************/


	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int k = s1.nextInt();
		int n = s1.nextInt();
		HashMap<String , Integer> map = new HashMap<>();
		String str[] = new String[k];
		PriorityQueue<Integer> pq[] = new PriorityQueue[k];
		for(int i=0;i<k;i++)
			pq[i] = new PriorityQueue<>((i1 , i2) -> Integer.compare(i2, i1));
		for(int i=0;i<k;i++) {
			String line = s1.next();
			int val = s1.nextInt();
			Integer idx = map.get(line);
			if(idx == null) {
				idx = map.size();
				str[idx] = line;
				map.put(line, idx);
			}
			pq[idx].add(val);
		}

		int sz = map.size();
		boolean marked[] = new boolean[sz];
		int rev[] = new int[sz];
		// out.println(Arrays.toString(str));
		// out.println(map);
		for(int i=0;i<sz;i++) {
			Integer idx = map.get(new StringBuilder(str[i]).reverse().toString());
			if(idx == null)
				rev[i] = -1;
			else 
				rev[i] = idx;
		}
		// out.println("rev " + Arrays.toString(rev));
		int maxBeauty = 0;
		int maxToAdd = 0;
		for(int i=0;i<sz;i++) {
			if(!marked[i] && rev[i] >= 0) {
				if(rev[i] == i) {
					while(pq[i].size() > 1) {
						int first = pq[i].remove();
						int sec = pq[i].remove();
						if(first >= 0 && sec >= 0)
							maxBeauty += first + sec;
						else if(first > 0 && sec < 0){
							if(first + sec > 0) { 
								maxBeauty += first + sec;
								maxToAdd = Math.max(maxToAdd,-sec);
							}
							else if(first + sec <= 0)
								maxToAdd = Math.max(maxToAdd,first);
							
							break;
						}
					}
					
					if(pq[i].size() == 1 && pq[i].peek() > 0)
						maxToAdd = Math.max(maxToAdd,pq[i].peek());
					
				}
				else {
					
					while(!pq[i].isEmpty() && !pq[rev[i]].isEmpty() && (pq[i].peek() + pq[rev[i]].peek()) > 0)
						maxBeauty += pq[i].remove() + pq[rev[i]].remove();
					
					marked[i] = true;
					marked[rev[i]] = true;
				}
			}
		}
		
		maxBeauty += maxToAdd;
		
		out.println(maxBeauty);

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