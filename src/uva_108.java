import java.util.*;
import java.io.*;
public class uva_108
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static int sumOfRegion(int prefixSum[][], int x1, int y1, int x2, int y2) {
        int entire = prefixSum[x2][y2];
        int W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
        int N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
        int NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;
        return entire - N - W + NW;
    }

    private static void solve(FastScanner s1, PrintWriter out) {

        String n = null;
        while ((n = s1.nextLine()) != null) {
            int N = Integer.parseInt(n);
            int arr[][] = new int[N][];
            for (int i = 0; i < N; i++)
                arr[i] = s1.nextIntArray(N);

            int prefixSum[][] = new int[N][N];
            for (int i = 0; i < N; i++)
                prefixSum[i][0] = arr[i][0];

            for (int i = 0; i < N; i++)
                for (int j = 1; j < N; j++)
                    prefixSum[i][j] = prefixSum[i][j - 1] + arr[i][j];

            for (int i = 1; i < N; i++)
                for (int j = 0; j < N; j++)
                    prefixSum[i][j] += prefixSum[i - 1][j];

            int maxSum = Integer.MIN_VALUE;

            for (int R = 1; R <= N; R++)
                for (int C = 1; C <= N; C++)
                    for (int i = 0; i <= N - R; i++)
                        for (int j = 0; j <= N - C; j++)
                            maxSum = Math.max(maxSum, sumOfRegion(prefixSum, i, j, i + R - 1, j + C - 1));

            out.println(maxSum);

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