import java.util.*;
import java.io.*;
public class SummingPieces
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final long mod = (long)1e9 + 7;
	static final int modInt = (int)1e9 + 7;
	static long sub(long a, long b) {return ((a % mod) - (b % mod) + mod) % mod;}
	static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
	static long add(long a, long b) {return ((a % mod) + (b % mod)) % mod;}

	static long arr[];
	static long total = 0;
	static int cnt = 0;
	static int pow2[];
	static {  // Calculate the powers of 2
		int max = (int)(1e6);
		pow2 = new int[max + 1];
		pow2[0] = 1;
		for(int i=1;i <= max;i++)
			pow2[i] = (pow2[i - 1] << 1) % modInt;
	}
	static HashMap<Integer,HashMap<String , Integer>> freq;
	static HashMap<Integer ,HashMap<Integer,Integer>> game;
	static HashMap<Integer,HashMap<Sub,Integer>> subFreq;
	static class Sub {   // Wrapper class for holding the end points of a  sub array
		int s , e , len;
		Sub(int s , int e) {
			this.s = s;
			this.e = e;
			this.len = e - s + 1;
		}
		@Override
		public boolean equals(Object obj) {
			Sub o = (Sub) obj;
			return this.s == o.s && this.e == o.e;
		}
		@Override
		public int hashCode() {
			return Objects.hash(s,e);
		}
		@Override
		public String toString() {
			return "[" + s + ", " + e +"]";
		}
	}
	static void rec(int idx , long sum , HashSet<Sub> set) {
		if(idx >= arr.length) {
			total = add(total, sum);
			for(Sub s : set) {
				HashMap<Sub , Integer> hm = subFreq.getOrDefault(s.len, new HashMap<>());
				hm.put(s, hm.getOrDefault(s, 0) + 1);
				subFreq.put(s.len, hm);
			}
		}
		else {
			long curr = 0;
			for(int i=idx;i<arr.length;i++) {
				curr = add(curr, arr[i]);
				// System.out.println("start " + idx + " end " + i);
				// String key = "start " + idx + " end " + i;
				Sub key = new Sub(idx, i);
				set.add(key);
				rec(i + 1, add(sum, mul(curr, i - idx + 1)) , set);
				set.remove(key);
			}
		}
	}
	static void research(int N) {
		arr = new long[N];
		subFreq  = new HashMap<>();
		Arrays.fill(arr, 1);
		cnt = 0;
		rec(0, 0 , new HashSet<>());
		System.out.println("Array used : " + Arrays.toString(arr));
		for(Map.Entry<Integer , HashMap<Sub , Integer>> e : subFreq.entrySet())
			System.out.println(e.getKey() + " ==> " + e.getValue());

		System.out.println("SUM " + total);

	}
	private static void solve(FastScanner s1, PrintWriter out){
		int N = s1.nextInt();
		// research(N);
		arr = s1.nextLongArray(N);

		long prefix[] = new long[N + 1];
		for(int i=1;i<=N;i++)
			prefix[i] = prefix[i - 1] + arr[i - 1];
		long sum = mul(prefix[N], N);  // Last sum (whole subarray)
		if(N >= 2) {
			sum = add(sum, mul(N - 1, add(prefix[N - 1] - prefix[0], prefix[N] - prefix[1])));
		}
		if(N >= 3) {
			long DP[] = new long[((N-2)/2) + 1]; // One based index
			int DP_len = DP.length - 1;
			long tempSum = 0;
			for(int i=1;i<=(N-2);i++) {
				
				sum = add(sum, mul(add(prefix[i] - prefix[0], prefix[N] - prefix[N - i]), mul(i, pow2[N - i - 1])));
				if((N - 2) % 2 == 1 && i == (((N - 2) / 2) + 1))
					tempSum = add(tempSum, sub(prefix[i + 1], prefix[i]));
				else {
					if (i <= (((N - 2) / 2))) {
						tempSum = add(tempSum, sub(prefix[N - i], prefix[i]));
						DP[i] = tempSum;
					}
					else {
						if((N - 2) % 2 == 0)
							tempSum = DP[DP_len - (i - DP_len) + 1];
						else
							tempSum = DP[DP_len - (i - DP_len - 2)];
					}
				}
				sum = add(sum, mul(tempSum, mul(i, pow2[N - i - 2])));
			}
		}

		out.println(sum);
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