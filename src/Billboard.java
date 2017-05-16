import java.util.*;
import java.io.*;
public class Billboard
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/

	static class SegmentTree  { // Implemented to store min in a range , point update and range query
		int tree[] , arr[];
		int len;
		int size;
		SegmentTree(int arr[]) { // arr should be a 1 based array
			this.arr = arr;
			len = arr.length - 1;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
			tree = new int[size];
			build(arr, 1, 1, len);
		}
		void update(int node,int idx,int val,int nl,int nr) {
			if(nl == nr && nl == idx) {
				arr[idx]   = val;
				tree[node] = val;
			}
			else {
				int mid = (nl + nr) / 2;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);

				tree[node] = Math.max(tree[2*node],tree[(2 * node) + 1]);
			}
		}
		void update(int idx , int val){
			update(1, idx, val, 1, len);
		}
		int query(int L , int R){
			return query(1, L, R, 1, len);
		}
		int query(int node , int L , int R, int nl, int nr) {
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}
		void build(int arr[],int node,int L,int R) {
			if(L == R)
				tree[node] = arr[L];
			else
			{
				int mid = L + ((R-L)/2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				tree[node] = Math.max(tree[2*node] , tree[(2 * node) + 1]);
			}
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		 int R = s1.nextInt();
		 int C = s1.nextInt();
		 int N = s1.nextInt();
		 
		 int len = Math.min(R,N);
		 int arr[] = new int[len + 1];
		 Arrays.fill(arr, C);
		 
		 SegmentTree segTree = new SegmentTree(arr);
		 while(N-->0) {
			 int width = s1.nextInt();
			 int lo = 1 , hi = len;
			 int lb = len + 1;
			 while(lo <= hi) {
				 int mid = (lo + hi) / 2;
				 int prefixMax = segTree.query(1, mid);
				 if(width > prefixMax)
					 lo = mid + 1;
				 else {
					 hi = mid - 1;
					 lb = mid;
				 }
			 }
			 
			 if(lb <= len) {
				 out.println(lb);
				 segTree.update(lb, arr[lb] - width);
			 }
			 else
				 out.println(-1);
		 }
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	static String fileName = "billboard";
	
	public static void main(String []args) throws IOException {
		InputStream  inputStream  = (System.getProperty("ONLINE_JUDGE") != null) ? (new FileInputStream(fileName  + ".in"))  : System.in;
		OutputStream outputStream = (System.getProperty("ONLINE_JUDGE") != null) ? (new FileOutputStream(fileName + ".out")) : System.out;
		FastScanner in  = new FastScanner(inputStream);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)), false); 
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