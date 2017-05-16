import java.util.*;
import java.io.*;
class MFREQ
{


	/************************ SOLUTION STARTS HERE ***********************/


	static int ceil(int arr[] , int key) {
		int lo = 0 , hi = arr.length - 1 , ceil = -1;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(key <= arr[mid]) {
				hi = mid - 1;
				ceil = mid;
			}
			else
				lo = mid + 1;
		}
		return ceil;
	}

	static class SegmentTree
	{
		int tree[];
		int comp[];
		int len;
		int size;
		SegmentTree(int arr[]) 
		{
			len = arr.length;
			this.comp = arr;
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new int[size];
			build(1, 0, len - 1);
		}
		int getPosOfMax(int L , int R){
			if(L > R)
				return Integer.MIN_VALUE;
			else 
				return query(1, L, R, 0, len - 1);
		}
		int query(int node , int L , int R, int nl, int nr)
		{
			int mid = (nl + nr) / 2;
			// System.out.printf("L = %d R = %d nl = %d nr = %d \n", L , R , nl , nr);
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else {
				int left  = query(2*node, L, mid , nl , mid);
				int right = query((2*node)+1, mid+1, R , mid+1,nr);
				return comp[left] > comp[right] ? left : right;
			}
		}
		void build(int node,int L,int R)
		{
			if(L == R)
				tree[node] = L;
			else
			{
				int mid = L + ((R-L)/2);	
				build(2 * node, L, mid);
				build((2 * node) + 1, mid + 1, R);
				tree[node] = comp[tree[2*node]] > comp[tree[(2 * node) + 1]] ? tree[2*node] : tree[(2*node) + 1]; 
			}
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){ // Thanks you uva 11235 

		int N = s1.nextInt();
		int Q = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		int blocks = 1;
		for(int i=1;i<N;i++)
			blocks += (arr[i] == arr[i - 1]) ? 0 : 1;
		
		int freq[] = new int[blocks];
		int key[] = new int[blocks];
		int curr = 0 , sz = 1 ;
		for(int i=1;i<N;i++) {
			if(arr[i] != arr[i - 1]) {
				freq[curr] = sz;
				key[curr] = arr[i - 1];
				curr++;
				sz = 1;
			}
			else 
				sz++;
		}
		freq[curr] = sz;
		key[curr] = arr[N - 1];
		
		// System.out.println("freq " + Arrays.toString(freq));

		int cumuFreq[] = new int[blocks];
		cumuFreq[0] = freq[0];
		for(int i=1;i<blocks;i++)
			cumuFreq[i] = freq[i] + cumuFreq[i - 1];

		// System.out.println("cumuFreq " + Arrays.toString(cumuFreq));

		SegmentTree segmentTree = new SegmentTree(freq);
		
		while(Q-->0) {
			int L = s1.nextInt();
			int R = s1.nextInt();
			int K = s1.nextInt();
			int LL = ceil(cumuFreq, L);
			int RR = ceil(cumuFreq, R);	
			// System.out.println("L " + L + " R " + R + " LL " + LL + " RR " + RR);
			int idxInBetweenMax = segmentTree.getPosOfMax(LL + 1, RR - 1);
			
			if(LL == RR) 
				out.println(key[LL]);
			else if(cumuFreq[LL] - L + 1 >= K)	// Ambigious question
				out.println(key[LL]);
			else if(freq[RR] - (cumuFreq[RR] - R) >= K)
				out.println(key[RR]);
			else if(idxInBetweenMax >= 0 && freq[idxInBetweenMax] >= K)
				out.println(key[idxInBetweenMax]);
			else
				out.println(-1);
			
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}