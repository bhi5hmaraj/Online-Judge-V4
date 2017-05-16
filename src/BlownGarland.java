import java.util.*;
import java.io.*;
public class BlownGarland
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Pair implements Comparable<Pair>{
		int idx;
		TreeSet<Integer> ways;
		Pair(int idx , TreeSet<Integer> set) {
			this.idx = idx;
			this.ways = set;
		}
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.ways.size(), o.ways.size());
		}
		@Override
		public String toString() {
			return String.format("idx = %d ways = %s", idx , ways.toString());
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		char str[] = s1.nextLine().toCharArray();
		int N = str.length;
		char poss[] = {'R', 'B' , 'Y' , 'G'};
		ArrayList<Pair> arl = new ArrayList<>();

		for(int i=0;i<N;i++) {
			if(str[i] == '!') {
				Pair p = new Pair(i, new TreeSet<>());
				for(char ch : poss)
					p.ways.add(ch - 'A');

				for(int j=Math.max(0,i - 3);j <= Math.min(N - 1,i + 3);j++)
					p.ways.remove(str[j] - 'A');

				arl.add(p);
			}
		}
		
		// out.println(arl);
		
		boolean marked[] = new boolean[N];
		int cnt[] = new int[26];

		for(int i=0;i<arl.size();i++) {
			Collections.sort(arl);
			System.out.println(arl);
			int j = 0;
			while(marked[arl.get(j).idx]) j++;

			int ch = arl.get(j).ways.first();
			marked[arl.get(j).idx] = true;
			int idx = arl.get(j).idx;
			for(Pair p : arl)
				if(p.idx >= Math.max(0,idx - 3) && p.idx <= Math.min(N - 1,idx + 3))
					p.ways.remove(ch);

			cnt[ch]++;
		}

		for(char ch :poss)
			out.print(cnt[ch - 'A'] + " ");
	}

	private static void solve2(FastScanner s1, PrintWriter out){
		char str[] = s1.nextLine().toCharArray();
		int cnt[] = new int[26];
		for(int i=0;i<str.length;i++) {
			if(str[i] == '!') {
				boolean flag = false;
				for(int j=i;j<str.length;j+=4)
					if(str[j] != '!') {
						flag = true;
						cnt[str[j] - 'A']++;
						break;
					}
				
				if(!flag) {
					for(int j=i;j >= 0;j -= 4)
						if(str[j] != '!') {
							cnt[str[j] - 'A']++;
							break;
						}
				}
			}
		}
		char poss[] = {'R', 'B' , 'Y' , 'G'};
		for(char c : poss)
			out.print(cnt[c - 'A'] + " ");
		
	}

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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