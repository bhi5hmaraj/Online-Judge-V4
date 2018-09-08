import java.util.*;
import java.io.*;

public class Gambling{
	
	
	
	/************************ SOLUTION STARTS HERE ************************/
	

	static void shuffleArray(long[] array) {
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			long temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

	
	private static void solve() {
		
		int n = nextInt();
		long A[] = nextLongArray(n);
		long B[] = nextLongArray(n);
		
		shuffleArray(A);
		shuffleArray(B);
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		long sumA = 0, sumB = 0;
		boolean turn = true;
		
		for(int i = n - 1, j = n - 1; i >= 0 || j >= 0;) {
			
			if(turn) {	// A's turn
				if(i < 0) 
					j--;
				else if(j < 0 || A[i] >= B[j])
					sumA += A[i--];
				else if(A[i] < B[j])
					j--;
			}
			else {
				
				if(j < 0)
					i--;
				else if(i < 0 || B[j] >= A[i])
					sumB += B[j--];
				else if(A[i] > B[j])
					i--;
				
			}
			
			turn = !turn;
		}
		
		println(sumA - sumB);
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE **********************/
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
		st     = null;
		solve();
		reader.close();
		writer.close();
	}
	
	static BufferedReader reader;
	static PrintWriter    writer;
	static StringTokenizer st;
	
	static String next()
	{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
	st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
	static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
	static int    nextInt()   {return Integer.parseInt(next());}
	static long   nextLong()  {return Long.parseLong(next());}     
	static double nextDouble(){return Double.parseDouble(next());}
	static char   nextChar()  {return next().charAt(0);}
	static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
	static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
	static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
	static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
	static void   print(Object o)  { writer.print(o);  }
	static void   println(Object o){ writer.println(o);}
	
	/************************ TEMPLATE ENDS HERE ************************/
	
}