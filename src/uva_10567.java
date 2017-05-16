import java.util.*;
import java.io.*;
public class uva_10567
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	String str = s1.nextLine();
	HashMap<Character,TreeSet<Integer>> pos = new HashMap<>();
	for(int i=0,len=str.length();i<len;i++){
	    
	    TreeSet<Integer> set = pos.get(str.charAt(i));
	    if(set == null)
		set = new TreeSet<>();
	    
	    set.add(i);
	    pos.put(str.charAt(i), set);
	}
	
	int Q = s1.nextInt();
	while(Q-->0)
	{
	    String query = s1.nextLine();
	    boolean flag = true;	    
	    int start = -1,end = -1, curr = -1;
	    
	    for(int i=0,len=query.length();i<len;i++)
	    {
		TreeSet<Integer> set = pos.get(query.charAt(i));
		if(set == null) { flag = false; break; }
		
		Integer ceil = set.higher(curr);
		if(ceil == null) { flag = false; break; }
		
		if(i == 0)
		    start = ceil;
		if(i == len - 1)
		    end = ceil;
		
		curr = ceil;
	    }
	    
	    if(flag)
		out.println("Matched " + start + " " + end);
	    else
		out.println("Not matched");
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