import java.util.*;
import java.io.*;
public class BeautifulPaintings
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class MultiSet <T> extends TreeMap<T,Integer>
    {
	public void add(T key)
	{
	    Integer q = super.get(key);
	    if(q == null)
		super.put(key, 1);
	    else
		super.put(key, q+1);
	}
	@Override
	public Integer remove(Object key) {
	    Integer q = super.get(key);
	    if(q != null)
	    {
		if(q == 1)
		    super.remove(key);
		else
		    super.put((T)key, q-1);
	    }
	    else
		throw new NullPointerException("The specified key does not exist");

	    return (Integer)key;
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	MultiSet<Integer> set = new MultiSet<>();
	int n =s1.nextInt();
	for(int i=1;i<=n;i++)
	    set.add(s1.nextInt());

	int ans[] = new int[n];
	int j = 0;
	while(j < n)
	{
	    Integer f = null;
	    if(j > 0)
	    {
		f = set.higherKey(ans[j-1]);
	    }
	    
	    if((j == 0 || f == null) &&(j < n-1))
	    {
		ans[j] = set.remove(set.firstKey());
		Integer ceil = set.higherKey(ans[j]);
		if(ceil == null)
		    ans[j+1] = set.remove(ans[j]);
		else
		    ans[j+1] = set.remove(ceil);
		j+=2;
	    }
	    else
	    {
		ans[j++] = f!=null?set.remove(f):set.remove(set.firstKey());
	    }
	}
	if(n % 2 == 1 && !set.isEmpty())
	    ans[n-1] = set.remove(set.firstKey());


	//System.out.println(Arrays.toString(ans));
	int cnt = 0;
	for(int i=1;i<n;i++)
	    cnt += (ans[i] > ans[i-1])?1:0;
	out.print(cnt);
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