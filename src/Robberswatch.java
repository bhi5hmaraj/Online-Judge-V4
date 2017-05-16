import java.util.*;
import java.io.*;
public class Robberswatch
{


    /************************ SOLUTION STARTS HERE ***********************/


    static int ct = 0;
    static ArrayList<String> arl = new ArrayList<>();
    static void permute(String curr, int index, String remain , int size)
    {
	if(index == size)
	    arl.add(curr);
	else
	{
	    for(int i=0,len = remain.length();i < len;i++)
		permute(curr + remain.charAt(i), index + 1, remain.substring(0, i) + remain.substring(i+1),size);
	}
    }

    static int log7(int n)
    {
	int cnt = 0;
	if(n == 0)
	    return 1;
	while(n > 0){
	    n /= 7;
	    cnt++;
	}

	return cnt;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int m = s1.nextInt();

	int logn = log7(n - 1);
	int logm = log7(m - 1);
	int min = Math.min(logm,logn);
	if(logm+logn > 7)
	    out.println(0);
	else
	{
	    int max_len = logm + logn;
	    permute("", 0, "0123456", max_len);
	    int cnt = 0;
	    for(String s : arl)
	    {
		int left = Integer.parseInt(s.substring(0, logn), 7);
		int right = Integer.parseInt(s.substring(logn), 7);
		cnt += left <= n - 1 && right <= m - 1 ? 1 : 0;		
	    }
	    out.print(cnt);
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