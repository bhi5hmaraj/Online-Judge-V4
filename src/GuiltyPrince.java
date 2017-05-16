import java.util.*;
import java.io.*;
public class GuiltyPrince
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static int R,C;
    private static char arr[][];
    private static int largestComponent;
    private static boolean marked[][];
    private static int[] x = {-1,0,1,0};
    private static int[] y = {0,1,0,-1};
    private static boolean isValid(int i,int j)
    {
	return (i >= 0 && i < R && j >= 0 && j < C && arr[i][j] == '.');
    }

    private static void floodFill(int i, int j) {
	if (!marked[i][j]) {
	    marked[i][j] = true;
	    largestComponent++;
	    for (int k = 0; k < 4; k++)   //This denotes 4 directions
		if (isValid(i + x[k], j + y[k]) && !marked[i + x[k]][j + y[k]])
		    floodFill(i + x[k], j + y[k]);
	}
    }



    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	for(int z=1;z<=t;z++)
	{
	    C = s1.nextInt();
	    R = s1.nextInt();
	    arr = new char[R][C];
	    marked = new boolean[R][C];
	    largestComponent = 0;
	    for(int i=0;i<R;i++)
		arr[i] = s1.nextLine().toCharArray();

	    int start_i=-1,start_j=-1;
	    outer:
		for (int i = 0; i < R; i++)
		    for (int j = 0; j < C; j++)
			if(arr[i][j] == '@')
			{
			    start_i = i;
			    start_j = j;
			    break outer;
			}
	    floodFill(start_i, start_j);
	    out.println("Case "+z+": "+largestComponent);
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