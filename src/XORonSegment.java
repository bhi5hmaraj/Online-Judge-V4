import java.util.*;
import java.io.*;
public class XORonSegment
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class SegmentTree
	{
		int tree[];
		int size;
		boolean lazy[];
		int len;
		SegmentTree(boolean arr[]) //ar is one based indexing
		{
			len = arr.length - 1;	    
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new int[size];
			lazy = new boolean[size];
			build(arr, 1, 1, len);

		}
		boolean isInternal(int node){
			return 2*node < size && (2*node)+1 < size;
		}
		int query(int node , int L , int R, int nl, int nr)
		{
			if(isInternal(node)){
				lazy[2*node] ^=   lazy[node];
				lazy[(2*node)+1] ^=  lazy[node];
			}
			int size = nr - nl + 1;
			int mid = (nl + nr) / 2;
			tree[node] = lazy[node] ? size - tree[node]:tree[node];
			lazy[node] = false;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return query(2*node, L, mid , nl , mid) +  query((2*node)+1, mid+1, R , mid+1,nr);
		}
		
		void upd(int node,int size){
			if(isInternal(node)){ 
				lazy[2*node] ^= lazy[node];
				lazy[(2*node)+1] ^=  lazy[node];
			}
			tree[node] = lazy[node] ? size - tree[node]: tree[node];
			lazy[node] = false;
		}
		
		int update(int node,int L, int R,int nl,int nr)
		{
			if(L == nl && R == nr)
			{
				if(isInternal(node)){   //Not a leaf node
					lazy[2*node] ^= true;
					lazy[(2*node)+1] ^=  true;
				}
				tree[node] = lazy[node] ? tree[node] : (nr - nl + 1) - tree[node];
				lazy[node] = false;
				return tree[node];
			}
			int mid = (nl + nr) / 2;
			upd(node , nr - nl + 1);
			upd(2 * node , mid - nl + 1);
			upd((2 * node) + 1 , nr - mid);
			int left = tree[2*node], right = tree[(2*node) + 1];
			if(R <= mid)
				left = update(2*node, L, R , nl , mid);
			else if(L > mid)
				right = update((2*node) + 1, L, R , mid + 1, nr);
			else
			{
				left = update(2*node, L, mid , nl , mid);
				right = update((2*node)+1, mid+1, R , mid+1,nr);
			}
			return tree[node] = left + right;
		}

		void build(boolean arr[],int node,int L,int R)
		{
			if(L == R)
				tree[node] = arr[L] ? 1 : 0;
			else
			{
				int mid = L + ((R-L)/2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				tree[node] = tree[2*node] + tree[(2 * node) + 1];
			}
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		final int MAX_BIT = 20;
		int N = s1.nextInt();
		int arr[] = s1.nextIntArrayOneBased(N);
		int Q = s1.nextInt();
		
		SegmentTree segTree[] = new SegmentTree[MAX_BIT];
		for(int i=0;i<MAX_BIT;i++){
			boolean b[] = new boolean[N + 1];
			for(int j=1;j<=N;j++)
				b[j] = (arr[j] & (1 << i)) != 0;
			segTree[i] = new SegmentTree(b);
		}
		
		while(Q-->0){
			if(s1.nextInt() == 1){
				int L = s1.nextInt();
				int R = s1.nextInt();
				long sum = 0;
				for(int i=0;i<MAX_BIT;i++)
					sum += ((long)segTree[i].query(1, L, R , 1 , N) * (1L << i));
				out.println(sum);
			}
			else{
				int L = s1.nextInt();
				int R = s1.nextInt();
				int xor = s1.nextInt();
				for(int i=0;i<MAX_BIT;i++)
					if((xor & (1 << i)) != 0)
						segTree[i].update(1, L, R , 1 , N);
			}
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