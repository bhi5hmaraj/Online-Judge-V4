import java.util.*;
import java.io.*;
class FindMedian
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	PriorityQueue<Integer> left = new PriorityQueue<>(new Comparator<Integer>() {
	    @Override
	    public int compare(Integer o1, Integer o2) {
		return o2 - o1;
	    }
	});
	PriorityQueue<Integer> right = new PriorityQueue<>();	
	int l_size = 0,r_size = 0;
	while(t-->0)		    
	{
	    int n = s1.nextInt();
	    l_size = left.size();
	    r_size = right.size();
	    if(l_size == 0)
	    {
		left.add(n);				
	    }
	    else if(r_size == 0)
	    {
		int l_max = left.poll();
		if(n < l_max)
		{
		    right.add(l_max);
		    left.add(n);
		}
		else
		{
		    right.add(n);
		    left.add(l_max);
		}
	    }
	    else
	    {
		if(l_size <= r_size)
		{
		    int r_min = right.poll();
		    if(n > r_min)
		    {
			left.add(r_min);
			right.add(n);
		    }
		    else
		    {
			left.add(n);
			right.add(r_min);
		    }
		}
		else
		{
		    int l_max = left.poll();
		    if(n < l_max)
		    {
			right.add(l_max);
			left.add(n);
		    }
		    else
		    {
			right.add(n);
			left.add(l_max);
		    }
		}
	    }
	    l_size = left.size();
	    r_size = right.size();
	    double median = 0.0;
	    if(l_size > r_size)
		median = left.peek();
	    else if(r_size > l_size)
		median = right.peek();
	    else
	    {
		int l = left.peek();
		int r = right.peek();
		median = (double)(l+r)/2.0;		
	    }
	    out.printf("%.1f\n", median);
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