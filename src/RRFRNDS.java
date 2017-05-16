import java.util.*;
import java.io.*;
class RRFRNDS
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static void solve(FastScanner s1, PrintWriter out){

	int V = s1.nextInt();
	BitSet[] adj = new BitSet[V];
	for(int i=0;i<V;i++)adj[i] = new BitSet(V);

	for(int i=0;i<V;i++)
	{
	    char nbr[] = s1.nextLine().toCharArray();
	    for(int j=0;j<V;j++)
		if(nbr[j] == '1')
		    adj[i].set(j);
	}

	if(V==1)
	{
	    out.print(0);
	    return;
	}
	int sum = 0;
	for(int i=0;i<V;i++)
	    for(int j=0;j<V;j++)
		if(i != j && !adj[i].get(j) && adj[i].intersects(adj[j]))
		    sum++;
	
	out.print(sum);
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