import java.util.*;
import java.io.*;
public class BearandDisplayedFriends
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair>
    {
	int index,value;
	Pair(int i,int v)
	{
	    index = i;
	    value = v;
	}
	@Override
	public int compareTo(Pair o) {
	    return this.value - o.value;
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int K = s1.nextInt();
	int Q = s1.nextInt();
	int arr[] = s1.nextIntArrayOneBased(N);
	boolean isDisp[] = new boolean[N + 1];
	PriorityQueue<Pair> pq = new PriorityQueue<>();
	while(Q-->0)
	{
	    int ch = s1.nextInt();
	    int id = s1.nextInt();
	    switch(ch)
	    {
	    case 1:
		isDisp[id] = true;
		pq.add(new Pair(id, arr[id]));
		if (pq.size() > K)
		{
		    Pair p = pq.remove();
		    isDisp[p.index] = false;
		}
		break;
	    case 2:
		out.println(isDisp[id] ? "YES" : "NO");
	    }
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