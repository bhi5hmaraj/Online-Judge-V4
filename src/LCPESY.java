import java.util.*;
import java.io.*;
class LCPESY
{


    /************************ SOLUTION STARTS HERE ***********************/

    public static HashMap<Character,Integer> freqOfChar(String str)
    {
	HashMap<Character,Integer> mp = new HashMap<>();
	for(int i=0,len=str.length();i<len;i++)
	{
	    char k = str.charAt(i);
	    Integer query = mp.get(k);
	    mp.put(k, query == null ? 1 : query + 1);	    	    
	}
	return mp;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    HashMap<Character,Integer> a = freqOfChar(s1.nextLine());
	    HashMap<Character,Integer> b = freqOfChar(s1.nextLine());
	    int cnt = 0;
	    for (Map.Entry<Character,Integer> e : a.entrySet()) 
	    {
		if(b.containsKey(e.getKey()))
		    cnt += Math.min(e.getValue(),b.get(e.getKey()));
	    }
	    out.println(cnt);
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