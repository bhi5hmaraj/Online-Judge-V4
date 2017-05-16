import java.util.*;
import java.io.*;
public class ConnectedCellinaGrid
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    private static int R,C;
    private static boolean marked[][];
    private static int arr[][];
    private static int dx[] = {1,0,-1,0,-1,1,1,-1};
    private static int dy[] = {0,-1,0,1,1,1,-1,-1};
    private static boolean isValid(int i,int j)
    {
	return i>=0 && i<R && j>=0 && j<C && arr[i][j] == 1;
    }
    private static int dfs(int i,int j)
    {
	if(!isValid(i, j))
	    return 0;
	marked[i][j] = true;
	int cnt = 1;
	for(int k=0;k<8;k++)
	    if(isValid(i+dx[k], j+dy[k]) && !marked[i+dx[k]][j+dy[k]])
		cnt += dfs(i+dx[k],j+dy[k]);
	
	return cnt;
    }
    private static void solve(FastScanner s1, PrintWriter out){

	R = s1.nextInt();
	C = s1.nextInt();
	arr = new int[R][C];
	marked = new boolean[R][C];
	for(int i=0;i<R;i++)
	    arr[i] = s1.nextIntArray(C);
	
	int max = Integer.MIN_VALUE;
	for(int i=0;i<R;i++)
	    for(int j=0;j<C;j++)
		if(!marked[i][j])
		    max = Math.max(max,dfs(i, j));
	
	out.println(max);
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