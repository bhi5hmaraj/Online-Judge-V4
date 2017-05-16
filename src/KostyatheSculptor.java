import java.util.*;
import java.io.*;
public class KostyatheSculptor
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Dimension {
		int a , b;
		Dimension(int a , int b){
			this.a = a;
			this.b = b;
		}
		@Override
		public boolean equals(Object obj) {
			Dimension that = (Dimension)obj;
			return  a == that.a && b == that.b;
		}
		@Override
		public int hashCode() {
			return Objects.hash(a,b);
		}
		@Override
		public String toString() {
			return "ln " + a + " bt " + b;
		}
	}

	static class Pair implements Comparable<Pair> {
		int index , h;
		Pair(int i , int k) {
			index = i;
			h = k;
		}
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(h, o.h);
		}
		@Override
		public String toString() {
			return "index " + index + " ht " + h;
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair)obj;
			return index == p.index && h == p.h;
		}
		@Override
		public int hashCode() {
			return Objects.hash(index , h);
		}
	}

	static void addToMap(int l , int b , int h , int idx , HashMap<Dimension , HashSet<Pair>> map) {
		Dimension d = new Dimension(l, b);
		HashSet<Pair> arl = map.getOrDefault(d, new HashSet<>());
		arl.add(new Pair(idx, h));
		map.put(d, arl);
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		HashMap<Dimension , HashSet<Pair>> map = new HashMap<>();
		for(int i=1;i<=N;i++) {
			int arr[] = s1.nextIntArray(3);
			Arrays.sort(arr);
			addToMap(arr[0], arr[1], arr[2], i, map);
			addToMap(arr[1], arr[2], arr[0], i, map);
			addToMap(arr[0], arr[2], arr[1], i, map);
		}

		long maxDiameter = 0;
		int pos1 = -1 , pos2 = -1;

		for(Map.Entry<Dimension, HashSet<Pair>> e : map.entrySet()) {
			ArrayList<Pair> arl = new ArrayList<>(e.getValue());
			Dimension d = e.getKey();
			Collections.sort(arl,Collections.reverseOrder());
			// out.println(d + " ==> " + arl);
			long h1 = arl.get(0).h;
			long h2 = arl.size() > 1 ? arl.get(1).h : 0;
			long diam = Math.min(Math.min(d.a,d.b),h1 + h2);
			if(diam > maxDiameter) {
				maxDiameter = diam;
				pos1 = arl.get(0).index;
				pos2 = arl.size() > 1 ? arl.get(1).index : -1;
			}
		}
		out.println(pos2 != -1 ? 2 : 1);
		out.println(pos1 + " " + ((pos2 != -1) ? pos2 : ""));
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