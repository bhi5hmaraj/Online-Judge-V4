import java.util.*;
import java.io.*;
public class TheOneWithTheQueries
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	HashMap<Character,TreeSet<Integer>> map = new HashMap<>();
	char str[] = s1.nextLine().toCharArray();
	int len = str.length;
	for(int i=0;i<len;i++)
	{
	    TreeSet<Integer> set = map.get(str[i]);	
	    if(set == null)
		set = new TreeSet<>();
	    set.add(i);
	    map.put(str[i], set);
	}
	
	int q = s1.nextInt();
	while(q-->0)
	{
	    char check[] = s1.nextLine().toCharArray();
	    boolean flag = true;
	    for(char ch:check)
		if(map.get(ch) == null)
		    flag = false;
	    
	    if(!flag)
	    {
		out.println("No");
		continue;
	    }
	    
	    Integer start = map.get(check[0]).first();

	    
	    for(int i=1;i<check.length;i++)
	    {
		Integer ceil = map.get(check[i]).higher(start);
		if(ceil == null)
		{
		    flag = false;
		    break;
		}
		else
		    start = ceil;
	    }
	    out.println(flag?"Yes":"No"	);
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