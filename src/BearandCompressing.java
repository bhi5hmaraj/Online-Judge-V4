import java.util.*;
import java.io.*;
public class BearandCompressing
{

    
    /************************ SOLUTION STARTS HERE ***********************/
    
    private static HashMap<Character,ArrayList<String>> mp;
    private static int numberOfWays(String curr,int len)
    {

	if(curr.length() == len)
	    return 1;
	else
	{
	    ArrayList<String> possible = mp.get(curr.charAt(0));
	    if(possible == null)
		return 0;
	    else
	    {
		int ways = 0;
		for(String s:possible)
		    ways += numberOfWays(s + curr.substring(1), len);
		return ways;
	    }
	}
    }

    
    private static void solve(FastScanner s1, PrintWriter out){

	int len = s1.nextInt();
	int q = s1.nextInt();
	mp = new HashMap<>();
	while(q-->0)
	{
	    String str = s1.next();
	    char ch = s1.nextChar();
	    ArrayList<String> arl = mp.get(ch);
	    if(arl == null)
		arl= new ArrayList<>();
	    arl.add(str);
	    mp.put(ch, arl);
	}
	//out.println(mp);
	out.print(numberOfWays("a", len));
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