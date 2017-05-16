import java.util.*;
import java.io.*;
public class uva_10976
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	String line = null;
	while((line = s1.nextLine()) != null)
	{
	    int k = Integer.parseInt(line);
	    String prefix = "1/"+k+" = ";
	    TreeMap<Integer,String> map = new TreeMap<>();
	    map.put(1+k, prefix + "1/"+((k*k)+k)+" + 1/"+(1+k));
	    for(int i = 2; i * i <= k * k;i++)
	    {
		if((k * k) % i == 0){
		    int x = i + k;
		    int y = ((k * k)/i) + k;
		    if(!map.containsKey(y))
			map.put(x, prefix+ "1/"+y+" + 1/"+x);
		    if(i * i != k * k)
		    {
			int other = (k * k) / i;
			x = other + k;
			y = ((k * k) / other) + k;
			if(!map.containsKey(y))
			    map.put(x, prefix+ "1/"+y+" + 1/"+x);
		    }		    
		}
	    }
	    
	    out.println(map.size());
	    for (Map.Entry<Integer,String> e : map.entrySet()) 
	    {
		out.println(e.getValue());
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