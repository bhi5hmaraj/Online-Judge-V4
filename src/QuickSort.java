import java.util.*;
import java.io.*;
public class QuickSort
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int N , arr[];
	static void swap(int i , int j) {
		if(i != j) {
			arr[i] ^= arr[j];
			arr[j] ^= arr[i];
			arr[i] ^= arr[j];
		}
	}
	
	
	
	static int partition(int L , int R) {
		// median of 3 method
		int poss[][] = {{L , arr[L]} , {R , arr[R]} , {(L + R) / 2 , arr[(L + R) / 2]}};
		Arrays.sort(poss, (p1 , p2) -> p1[1] - p2[1]);
		// Feeling lazy to enumerate all possibilities
		int piv = poss[1][0];
		swap(L, piv);
		int pivot = arr[L];
		// System.out.println("Pivot = " + pivot + " before partition " + Arrays.toString(arr));
		int lptr = L + 1;
		for(int i=L+1;i<=R;i++) 
			if(arr[i] <= pivot) 
				swap(i, lptr++);

		swap(L, --lptr);
		// System.out.println("after partiton " + Arrays.toString(arr));
		return lptr;
	}
	static int sort(int L , int R) {
		if(L >= R)
			return 0;
		else {
			int comp = R - L;
			int piv = partition(L, R);
			comp += sort(L, piv - 1) + sort(piv + 1, R);
			return comp;
		}
	}
	static boolean AC(){
		for(int i=1;i<N;i++)
			if(arr[i] < arr[i - 1])
				return false;
		
		return true;
	}
	private static void solve(FastScanner s1, PrintWriter out){

		N = (int) 1e4;
		arr = s1.nextIntArray(N);
		
		out.println(sort(0, N - 1));
		
		if(!AC()) throw new RuntimeException("Array not sorted");
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/new FileInputStream("QuickSortInput.txt"));
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