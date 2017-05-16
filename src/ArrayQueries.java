import java.util.*;
import java.io.*;
class ArrayQueries
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class SegmentTree //Seg tree is one based but the array and the interval that it holds are zero based
	{
		int tree[];
		int len; // len of array
		SegmentTree(int ar[]) 
		{
			len = ar.length;	    
			tree = new int[3 * len];
			build(1, 0, len-1, ar);
		}
		int build(int index,int start,int end,int ar[])
		{
			if(start == end)
				return tree[index] = ar[start];
			else
			{
				int mid = (start+end)/2;
				return tree[index] = Math.min(build(2*index, start, mid, ar),build((2*index)+1, mid+1, end, ar));		
			}
		}
		int query(int L,int R) //L and R are one based
		{
			return query(1, 0, len-1, L-1, R-1);
		}
		int query(int index,int start,int end,int l,int r)
		{
			int mid = (start+end)/2;
			if (start == l && end == r)
				return tree[index];
			else if(end < l || start > r)
				return Integer.MAX_VALUE;
			else if(r <= mid)
				return query(2*index, start, mid, l, r);
			else if(l > mid)
				return query((2*index)+1, mid+1, end, l, r);
			else
				return Math.min(query(2 * index,start,mid,l,mid),(query((2 * index) + 1,mid+1,end,mid+1,r)));
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int t = s1.nextInt();
		for(int z=1;z<=t;z++)
		{
			s1.nextLine();
			int N = s1.nextInt();
			int Q = s1.nextInt();
			int arr[] = s1.nextIntArray(N);	   
			SegmentTree segTree = new SegmentTree(arr);
			out.println("Case "+z+":");
			while(Q-->0)
				out.println(segTree.query(s1.nextInt(), s1.nextInt()));
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