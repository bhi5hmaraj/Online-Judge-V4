import java.util.*;
import java.io.*;
public class TaxiPlease
{

    
    /************************ SOLUTION STARTS HERE ***********************/
    
    
    static class Pair implements Comparable<Pair> {
	
	int id;
	long S,J;
	Pair(int id,long S,long J){
	    this.id = id;
	    this.S = S;
	    this.J = J;
	}
	
	@Override
	public int compareTo(Pair o) {
	    return Long.compare(S, o.S);	    
	}
	
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int M = s1.nextInt();
	long time[] = new long[M+1];
	Pair arr[] = new Pair[N];
	for(int i=0;i<N;i++)
	    arr[i] = new Pair(i, s1.nextLong(), s1.nextLong());
	
	int ans[] = new int[N];
	Arrays.sort(arr);
	
	for(Pair p : arr){
	    long S = p.S;
	    long J = p.J;
	    int index = 1;
	    for(;index<=M;index++){
		if(time[index] <= S){
		    time[index] = S + J;
		    break;
		}
	    }
	    
	    ans[p.id] = index > M ? -1 : index ;
	}
	
	for(int a:ans)
	    out.print(a + " ");

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