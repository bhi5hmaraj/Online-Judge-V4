import java.util.*;
import java.io.*;
public class uva_11951
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static long sumOfRegion(long prefixSum[][],int x1,int y1,int x2,int y2)
    {
	long entire = prefixSum[x2][y2];
	long W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
	long N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
	long NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;		
	return entire - N - W + NW;
    }


    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	for(int z=1;z<=t;z++)
	{
	    int R = s1.nextInt();
	    int C = s1.nextInt();
	    long K = s1.nextLong();
	    long arr[][] = new long[R][];
	    for (int i = 0; i < R; i++)
		arr[i] = s1.nextLongArray(C);

	    long prefixSum[][] = new long[R][C];
	    for (int i = 0; i < R; i++)
		prefixSum[i][0] = arr[i][0];

	    for (int i = 0; i < R; i++)
		for (int j = 1; j < C; j++)
		    prefixSum[i][j] = prefixSum[i][j - 1] + arr[i][j];

	    for (int i = 1; i < R; i++)
		for (int j = 0; j < C; j++)
		    prefixSum[i][j] += prefixSum[i - 1][j];

	    int maxArea  = Integer.MIN_VALUE;
	    long minCost = Long.MAX_VALUE;
	    long cost;
	    boolean flag = false;
	    for (int r = 1; r <= R; r++)
		for (int c = 1; c <= C; c++)
		    for (int i = 0; i <= R - r; i++)
			for (int j = 0; j <= C - c; j++)
			{
			    cost = sumOfRegion(prefixSum, i, j, i + r - 1, j + c - 1);
			    if(cost <= K)
			    {
				flag = true;
				int area = r * c;
				if(area > maxArea)
				{
				    maxArea = area;
				    minCost = cost;
				}
				else if(area == maxArea)
				    minCost = Math.min(minCost,cost);
			    }
			}


	    if(flag)
		out.println("Case #"+z+": "+maxArea+" " + minCost);
	    else
		out.println("Case #"+z+": 0 0");
	}

    }

    private static void solve2(FastScanner s1, PrintWriter out){

	for(int z = 1, t = s1.nextInt(); z<= t;z++)
	{
	    int R = s1.nextInt();
	    int C = s1.nextInt();
	    long K = s1.nextLong();
	    long arr[][] = new long[R][C];

	    for (int i = 0; i < R; i++)
		for (int j = 0; j < C; j++)
		    arr[i][j] = i != 0 ? s1.nextLong() + arr[i - 1][j] : s1.nextLong();


		    long minCost = Long.MAX_VALUE;
		    int maxArea = 0;
		    for(int i=0;i<R;i++)
		    {
			for(int j=i;j<R;j++)
			{
			    int start = 0;
			    long cost = 0;
			    for(int k=0;k<C;k++)
			    {
				if(i == 0)
				    cost += arr[j][k];
				else
				    cost += (arr[j][k] - arr[i - 1][k]);

				while(start < C && cost > K)
				{
				    if(i == 0)
					cost -= arr[j][start];
				    else
					cost -= (arr[j][start] - arr[i -1][start]);

				    start++;
				}

				int area = (j - i + 1) * (k - start + 1);
				if(area > maxArea)
				{
				    maxArea = area;
				    minCost = cost;
				}
				else if(area == maxArea)
				    minCost = Math.min(minCost,cost);
			    }
			}
		    }

		  if(maxArea == 0)
		      out.println("Case #"+z+": 0 0");
		  else
		      out.println("Case #"+z+": "+maxArea+" " + minCost);

	}

    }


    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve2(in, out);
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