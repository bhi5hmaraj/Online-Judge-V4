import java.util.*;
import java.io.*;
public class ShilandRoundNumbers
{


    /************************ SOLUTION STARTS HERE ***********************/


    static class FenwickTree
    {
	private int BIT[];
	private int len;
	public FenwickTree(int arr[] , int len) {

	    this.len = len;
	    BIT = new int[len + 10];
	    for(int i=1;i<=len;i++)
		update(i, arr[i]);
	    
	}
	
	int getSum(int end){
	    int sum = 0;
	    for(;end > 0 ; end -= (end & ((~end) + 1)))
		sum += BIT[end];
	    
	    return sum;
	}
	
	int getSum(int L , int R){
	    return getSum(R) - getSum(L - 1);
	}
	
	void update(int idx , int inc){
	    for(int i = idx;i <= len ; i += (i & ((~i) + 1)))
		BIT[i] += inc;
	}
    }

    
    static int isRound(long N){
	String num = String.valueOf(N);
	return N >= 0 && num.charAt(0) == num.charAt(num.length() - 1) ? 1 : 0;
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int Q = s1.nextInt();
	int arr[] = new int[N + 1];
	for(int i=1;i<=N;i++)
	    arr[i] = isRound(s1.nextLong());
	
	FenwickTree bit = new FenwickTree(arr, N);
	while(Q-->0){
	    if(s1.nextInt() == 1)
		out.println(bit.getSum(s1.nextInt(), s1.nextInt()));
	    else{
		
		int pos = s1.nextInt();
		int flag = isRound(s1.nextLong());
		if((flag ^ arr[pos]) == 1){
		    bit.update(pos, flag == 1 ? 1 : -1);
		    arr[pos] = flag;
		}
		    
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