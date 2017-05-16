import java.util.*;
import java.io.*;
public class PalindromeTransformation
{


    /************************ SOLUTION STARTS HERE ***********************/


    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int pos = s1.nextInt();
	int zero_pos = pos - 1;
	String str = s1.nextLine();
	int cnt = 0;

	int dir = pos <= n / 2 ? 1 : -1;
	int first = -1 , last = -1;

	for(int i = dir == 1 ? 0 : n - 1; (dir == 1) ? i < n / 2 : (n % 2 == 0) ? i >= n / 2 : i > n/2; i += dir){

	    if(str.charAt(i) != str.charAt(n - i -1)){
		first = first == -1 ? i : first;
		last = i;
	    }

	}

	if(first == -1 && last == -1){
	    out.println(0);
	    return;
	}

	if(dir == -1){
	    zero_pos = n - zero_pos - 1;
	    first = n - first - 1;
	    last = n - last - 1;
	}
	
	//out.println("zero_pos " + zero_pos + " first " + first + " last " + last); 
	
	if(first >= zero_pos)
	    cnt = last - zero_pos;
	else if(last <= zero_pos)
	    cnt = zero_pos - first;
	else
	    cnt = (2 * Math.min(zero_pos - first,last - zero_pos)) + Math.max(zero_pos - first,last - zero_pos);
	
	//out.println(cnt);
	
	for(int i=0;i<(n / 2);i++){
	    int diff = Math.abs(str.charAt(i) - str.charAt(n - i - 1));
	    cnt += Math.min(diff,26 - diff);
	}

	out.println(cnt);
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