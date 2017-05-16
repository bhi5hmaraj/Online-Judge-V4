import java.util.*;
import java.io.*;
public class VasyaandString
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    private static int rank(int arr[],int key,boolean inclusive){
	
	int cnt = 0;
	int lo = 0;
	int hi = arr.length-1;
	int mid;
	while(lo <= hi){
	    mid = (lo + hi) / 2;
	    if(inclusive){
		if(key >= arr[mid]){
		    cnt += (mid - lo + 1);
		    lo = mid + 1;
		}
		else
		    hi = mid - 1;
	    }
	    else{
		if(key <= arr[mid]){
		    hi = mid -1;
		}
		else{
		    cnt += (mid - lo + 1);
		    lo = mid + 1;
		}
	    }
	}
	return cnt;
    }
    
    
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int k = s1.nextInt();
	int prefix1[] = new int[n+1]; //Number of 'a'
	int prefix2[] = new int[n+1];
	String str = s1.nextLine();
	for(int i=0;i<n;i++){
	    if(str.charAt(i) == 'a')
		prefix1[i+1] = 1;
	    else
		prefix2[i+1] = 1;
	}
	for(int i=1;i<=n;i++){
	    prefix1[i] += prefix1[i-1];
	    prefix2[i] += prefix2[i-1];
	}
	/*
	System.out.println(Arrays.toString(prefix1));
	System.out.println(Arrays.toString(prefix2));
	*/
	int max = Integer.MIN_VALUE;
	for(int i=0;i<n;i++){
	    max = Math.max(max,Math.max(rank(prefix1, prefix1[i]+k, true) - i - 1, rank(prefix2, prefix2[i]+k, true) - i - 1));
	}
	
	out.print(max);
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