import java.util.*;
import java.io.*;
public class XeniaandBitOperations
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int M = s1.nextInt();
	int arr[] = s1.nextIntArray(1 << N);
	int tree[] = new int[1 << (N + 1)];
	System.arraycopy(arr, 0, tree, tree.length >> 1, arr.length);
	
	boolean flip  = true;
	for(int i = 1 << (N - 1), j = (tree.length - 1) >> 1;i >= 1; i >>= 1, flip = !flip){
	    
	    for(int t = 1;t <= i; t++ , j--){
		if(flip)
		    tree[j] = tree[2 * j] | tree[(2 * j) + 1];
		else
		    tree[j] = tree[2 * j] ^ tree[(2 * j) + 1];
	    }
	    
	}
	
	while(M-->0){
	    int idx = ((tree.length - 1) >> 1) + s1.nextInt();
	    tree[idx] = s1.nextInt();
	    flip = true;
	    for(int i=idx >> 1;i >= 1;i >>= 1 , flip = !flip){
		if(flip)
		    tree[i] = tree[2 * i] | tree[(2 * i) + 1];
		else
		    tree[i] = tree[2 * i] ^ tree[(2 * i) + 1];
	    }
	    
	    out.println(tree[1]);
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