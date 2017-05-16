import java.util.*;
import java.io.*;
public class TwoStrings
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int countLessThanOrEqual(int arr[] , int lo , int hi , int key) {
		// System.out.printf("less arr = %s lo = %d , hi = %d , key = %d ", Arrays.toString(arr) , lo , hi , key);
		int cnt = 0;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(key >= arr[mid]) {
				cnt += mid - lo + 1;
				lo = mid + 1;
			}
			else
				hi = mid - 1;
		}
		// System.out.println("cnt " + cnt);
		return cnt;
	}

	static int countGreaterThanOrEqual(int arr[] , int lo , int hi , int key) {
		// System.out.printf("greater arr = %s lo = %d , hi = %d , key = %d ", Arrays.toString(arr) , lo , hi , key);
		int cnt = 0;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(arr[mid] >= key) {
				cnt += hi - mid + 1;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		// System.out.println("cnt " + cnt);
		return cnt;
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){ // Wrong Greedy solution !!!

		String str1 = s1.nextLine();
		String str2 = s1.nextLine();

		int N = str1.length();
		int M = str2.length();

		String str1Rev = new StringBuilder(str1).reverse().toString();
		String str2Rev = new StringBuilder(str2).reverse().toString();

		TreeSet<Integer>[] pos    = new TreeSet[26];
		TreeSet<Integer>[] posRev = new TreeSet[26];

		for(int i=0;i<26;i++) {
			pos[i] = new TreeSet<>();
			posRev[i] = new TreeSet<>();
		}

		for(int i=0;i<N;i++) {
			pos[str1.charAt(i) - 'a'].add(i);
			posRev[str1Rev.charAt(i) - 'a'].add(i);
		}

		StringBuilder ans = new StringBuilder();
		int[] left = new int[N];
		int[] right = new int[N];
		int lSz = 0 , rSz = 0;
		Integer curr = -1;
		int end = -1;
		
		for(int i=0;i<M;i++) {
			char ch = str2.charAt(i);
			curr = pos[ch - 'a'].higher(curr);
			if(curr == null)
				break;
			else {
				left[lSz++] = curr;
				end = i;
			}
		}

		curr = -1;
		for(int i=0;i<M - end - 1;i++) {
			char ch = str2Rev.charAt(i);
			curr = posRev[ch - 'a'].higher(curr);
			if(curr == null)
				break;
			else 
				right[rSz++] = (N - curr - 1);
		}

		for(int i=0;i < rSz / 2;i++) { // Reverse right array
			int temp = right[i];
			right[i] = right[rSz - i - 1];
			right[rSz - i - 1] = temp;
		}

		// System.out.println("left " + Arrays.toString(left));
		// System.out.println("right " + Arrays.toString(right));

		int lStart = 0 , rStart = 0;

		while(lSz > 0 && rSz - rStart > 0 && left[lSz - 1] >= right[rStart]) {
			if(countLessThanOrEqual(right, rStart, rSz - 1, left[lSz - 1]) > countGreaterThanOrEqual(left, lStart, lSz - 1, right[rStart]))
				lSz--;
			else  
				rStart++;
		}

		for(int i=lStart;i<lSz;i++) ans.append(str1.charAt(left[i]));
		for(int i=rStart;i<rSz;i++) ans.append(str1.charAt(right[i]));


		out.println(ans.length() == 0 ? "-" : ans);

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