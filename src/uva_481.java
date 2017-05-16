import java.util.*;
import java.io.*;
public class uva_481
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	ArrayList<Integer> arl = new ArrayList<>();
	String in = null;
	while((in = s1.nextLine()).length() != 0)
	    arl.add(Integer.parseInt(in));
	/*
	int arr[] = new int[arl.size()];
	for(int i=0;i<arr.length;i++)
	    arr[i] = arl.get(i);
	*/
	int[] arr = arl.stream().mapToInt(Integer::intValue).toArray();	
	int dp[] = new int[arr.length];
	int prev[] = new int[arr.length];
	
	Arrays.fill(prev, -1);
	for(int i=0;i<arr.length;i++)
	{
	    for(int j=0;j<i;j++)
	    {
		if(dp[j] >= dp[i] && arr[j] < arr[i])
		{
		    dp[i] = dp[j];
		    prev[i] = j;
		}
	    }
	    dp[i]++;
	}
	
	int max_len = 0;
	int pos = -1;
	for(int i=0;i<arr.length;i++)
	{
	    if(dp[i] >= max_len)
	    {
		max_len = dp[i];
		pos = i;
	    }
	}
	
	ArrayDeque<Integer> stk = new ArrayDeque<>();
	while(pos != -1)
	{
	    stk.push(arr[pos]);
	    pos = prev[pos];
	}
	
	out.println(max_len + "\n-");
	stk.forEach(n -> out.println(n));
    }

    private static void solve2(FastScanner s1, PrintWriter out){
	
	TreeSet<Integer> set = new TreeSet<>();
	
	String in = null;
	while((in = s1.nextLine()).length() != 0)
	{
	    int num = Integer.parseInt(in);
	    Integer ceil = set.ceiling(num);
	    if(ceil != null)
		set.remove(ceil);
	    
	    set.add(num);
	}
	
	out.println(set.size() + "\n-");
	set.forEach(n -> out.println(n));
	
	
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve2(in, out);
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