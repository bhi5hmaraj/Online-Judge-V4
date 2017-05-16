import java.util.*;
import java.io.*;
public class HackingCypher
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		String in = s1.nextLine();
		int N = in.length();
		int arr[] = new int [N];		
		for(int i=0;i<N;i++)
			arr[i] = in.charAt(i) - '0';

		long a = s1.nextLong();
		long b = s1.nextLong();

		long remA[] = new long[N];
		long remB[] = new long[N];
		remA[0] = arr[0] % a;
		remB[N - 1] = arr[N - 1] % b;
		long pow = 10;
		for (int i = 1; i < N; i++, pow = (pow * 10) % b) {
			remA[i] = (((remA[i - 1] * 10) % a) + (arr[i] % a)) % a;
			remB[N - i - 1] = (((remB[N - i] % b) + ((arr[N - i - 1] * pow) % b))) % b;
		}

		for (int i = 0; i < N; i++) {
			if (i - 1 >= 0 && arr[i] != 0 && remB[i] == 0 && remA[i - 1] == 0) {
				out.println("YES");
				out.println(in.substring(0, i));
				out.println(in.substring(i));
				return;
			}
		}
		
		out.println("NO");
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