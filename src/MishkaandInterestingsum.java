import java.util.*;
import java.io.*;
public class MishkaandInterestingsum
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int BIT[];
	static int N;

	static void update(int idx,int val){		
		for(;idx <= N;idx += (idx & ((~idx) + 1)))
			BIT[idx] ^= val;
	}
	static int xorSum(int idx){
		int xor_sum = 0;
		for(;idx > 0;idx -= (idx & ((~idx) + 1)))
			xor_sum ^= BIT[idx];

		return xor_sum;
	}
	static int xorSum(int L , int R){
		return xorSum(L - 1) ^ xorSum(R);
	}
	static class Pair {
		int index , L;
		Pair(int index , int L){
			this.index = index;
			this.L = L;
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){

		N = s1.nextInt();
		BIT = new int[N + 1];
		int arr[] = s1.nextIntArrayOneBased(N);
		int prefixXor[] = new int[N + 1];
		HashMap<Integer,Integer> last = new HashMap<>();
		for(int i=1;i<=N;i++)
			prefixXor[i] = arr[i] ^ prefixXor[i - 1];
		int Q = s1.nextInt();
		
		HashMap<Integer, ArrayList<Pair>> query = new HashMap<>();
		for(int i=0;i<Q;i++){
			int L = s1.nextInt();
			int R = s1.nextInt();
			ArrayList<Pair> arl = query.get(R);
			if(arl == null)
				arl = new ArrayList<>();
			arl.add(new Pair(i, L));
			query.put(R, arl);
		}

		int ans[] = new int[Q];

		for(int i=1;i<=N;i++){
			if(last.containsKey(arr[i]))
				update(last.get(arr[i]), arr[i]);

			update(i, arr[i]);
			last.put(arr[i], i);
			if(query.containsKey(i)){
				for(Pair p: query.get(i))
					ans[p.index] = prefixXor[i] ^ prefixXor[p.L - 1] ^ xorSum(p.L, i);
			}
		}
		
		for(int a:ans)
			out.println(a);
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