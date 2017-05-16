import java.util.*;
import java.io.*;
class CHSQARR_brute
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class MultiSet<T> extends TreeMap<T, Integer> {
	public void add(T key) {
	    Integer q = super.get(key);
	    if (q == null)
		super.put(key, 1);
	    else
		super.put(key, q + 1);
	}

	@Override
	public Integer remove(Object key) {
	    Integer q = super.get(key);
	    if (q != null) {
		if (q == 1)
		    super.remove(key);
		else
		    super.put((T) key, q - 1);
	    } else
		throw new NullPointerException("The specified key does not exist");

	    return q;
	}
    }


    private static void solve(FastScanner s1, PrintWriter out){

	int R = s1.nextInt();
	int C = s1.nextInt();
	int arr[][] = new int[R][];
	for(int i=0;i<R;i++)
	    arr[i] = s1.nextIntArray(C);

	int Q = s1.nextInt();
	while(Q-->0)
	{
	    int a = s1.nextInt();
	    int b = s1.nextInt();
	    int min = Integer.MAX_VALUE;
	    for(int i=0;i<=R-a;i++)
	    {
		MultiSet<Integer> set = new MultiSet<>();
		int sum = 0;
		for(int k=i;k<i+a;k++)
		{
		    for(int l=0;l<b;l++)
		    {
			sum += arr[k][l];
			set.add(arr[k][l]);
		    }
		}
		
		int time = (set.lastKey() * a * b) - sum;
		min = Math.min(min,time);
		for(int j=b;j<C;j++)
		{
		    for(int k=i;k<i+a;k++)
		    {
			sum = (sum -arr[k][j-b] + arr[k][j]);
			set.remove(arr[k][j-b]);
			set.add(arr[k][j]);
		    }
		    time = (set.lastKey() * a * b) - sum;
		    min = Math.min(min,time);
		}
	    }
	    out.println(min);
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