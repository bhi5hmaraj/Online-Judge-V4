import java.util.*;
import java.io.*;
class Deduplicatingfiles
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    private static int hash(String str)
    {
	int hash = 0;
	for(int i=0,len=str.length();i<len;i++)
	    hash ^= (int)str.charAt(i);
	
	return hash;
    }
    private static <Key> void frequency(Map<Key, Integer> mp, Key k) {
	Integer query = mp.get(k);
	mp.put(k, query == null ? 1 : query + 1);
    }
    private static void solve(FastScanner s1, PrintWriter out){

	
	while(true)
	{
	    int t = s1.nextInt();
	    if(t == 0)
		return;
	    
	    HashMap<Integer,Integer> freq = new HashMap<>();
	    HashSet<String> set = new HashSet<>();
	    while(t-->0)
	    {
		String str = s1.nextLine();
		frequency(freq, hash(str));
		set.add(str);
	    }
	    
	    
	    long collisions = 0;
	    for (Map.Entry<Integer,Integer> e : freq.entrySet()) 
	    {
		long f = e.getValue().longValue();
		collisions += ((f - 1) * (f - 2));
	    }
	    out.println(set.size() + " " + collisions);
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