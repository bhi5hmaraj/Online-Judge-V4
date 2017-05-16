import java.util.*;
import java.io.*;
public class PyramidofGlasses
{


    /************************ SOLUTION STARTS HERE ***********************/


    private static boolean isValid(int i,int j,int n){
	return i >=0 && i < n && j >=0 && j <n;
    }

    private static void printArray(double arr[][],int n){

	for(int i=0;i<n;i++)
	    System.out.println(Arrays.toString(arr[i]));
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int t = s1.nextInt();
	double arr[][] = new double[n][n];
	for (int i = 1; i <= t; i++) {

	    arr[0][0] += 1.0;
	    for (int j = 0; j < n; j++) {
		for (int k = 0; k <= j; k++) {
		    if (arr[j][k] > 1.0) {
			double overflow = arr[j][k] - 1.0;
			arr[j][k] = 1.0;
			if (isValid(j + 1, k, n))
			    arr[j + 1][k] += overflow / 2.0;
			if (isValid(j + 1, k + 1, n))
			    arr[j + 1][k + 1] += overflow / 2.0;
		    }
		}
	    }

	}

	int cnt = 0;
	for (int j = 0; j < n; j++) {
	    for (int k = 0; k <= j; k++) {
		cnt += arr[j][k] == 1.0 ? 1 : 0;
	    }
	}

	out.print(cnt);
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