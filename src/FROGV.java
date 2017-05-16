import java.util.*;
import java.io.*;
class FROGV
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair>
    {
	int index,val;
	Pair(int i,int v)
	{
	    index = i;
	    val = v;
	}
	@Override
	public int compareTo(Pair o) {
	    return Integer.compare(val, o.val);
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int K = s1.nextInt();
	int P = s1.nextInt();
	Pair arr[] = new Pair[N];
	for (int i = 0; i < N; i++)
	    arr[i] = new Pair(i + 1, s1.nextInt());
	Arrays.parallelSort(arr);
	int ID[] = new int[N + 1];
	int id = 1;
	ID[arr[0].index] = id;
	for (int i = 1; i < N; i++)
	    ID[arr[i].index] = arr[i].val - arr[i - 1].val <= K ? id : ++id;

	while (P-- > 0)
	    out.println(ID[s1.nextInt()] == ID[s1.nextInt()] ? "Yes" : "No");
	
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