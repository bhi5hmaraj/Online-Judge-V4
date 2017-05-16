import java.util.*;
import java.io.*;
public class BearandVowels
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    private static boolean isVowel(char ch)
    {
	switch(ch)
	{
	case 'a':case 'e':case 'i':case 'o':case 'u':case 'y':return true;
	}
	return false;
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt(); 
	while(t-->0)
	{
	    char arr[] = s1.nextLine().toCharArray();
	    boolean flag = false;
	    int vow = 0;
	    for(char ch:arr)
		vow += isVowel(ch) ? 1 : 0;
	    
	    if(vow < (arr.length - vow))
	    {
		out.println("hard");
		continue;
	    }
	    else
	    {
		for(int i=2;i<arr.length;i++)
		    flag |= (!isVowel(arr[i]) && !isVowel(arr[i-1]) && !isVowel(arr[i-2]));
		
		out.println(flag ? "hard" : "easy");
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