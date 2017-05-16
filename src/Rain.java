import java.util.*;
import java.io.*;
public class Rain
{


    /************************ SOLUTION STARTS HERE ***********************/

    static int R , C;
    static int H[][] , W[][];
    static boolean isValid(int i , int j){
	return i >= 0 && i < R && j >= 0 && j < C;
    }

    static boolean isEdge(int i , int j){
	return i == 0 || i == R - 1 || j == 0 || j == C - 1;
    }

    static int dx[] = {0,1, 0,-1};
    static int dy[] = {1,0,-1, 0};


    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	for(int test = 1;test <= t; test++)
	{
	    R = s1.nextInt();
	    C = s1.nextInt();
	    H = new int[R][];
	    for(int i=0;i<R;i++)	
		H[i] = s1.nextIntArray(C);

	    W = new int[R][C];
	    for(int i=0;i<R;i++)
		for(int j=0;j<C;j++){
		    W[i][j] = isEdge(i, j) ? H[i][j] : Integer.MAX_VALUE;
		}

	    for(int z = 1 ; z <= (R * C) ; z++){

		for(int i=0;i<R;i++){
		    
		    for(int j=0;j<C;j++){
			
			if(!isEdge(i, j)){
			    int min = Integer.MAX_VALUE;
			    for(int k = 0;k<4;k++){
				int x = i + dx[k];
				int y = j + dy[k];
				min = Math.min(min,W[x][y]);
			    }
			    W[i][j] = Math.max(min,H[i][j]);
			}
			
		    }
		}
	    }

	    int total = 0;
	    for(int i=0;i<R;i++)
		for(int j=0;j<C;j++)
		    total += W[i][j] - H[i][j];

	    out.println("Case #"+test+": "+total);

	}

    }



    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	//PrintWriter out = new PrintWriter("out.txt");
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