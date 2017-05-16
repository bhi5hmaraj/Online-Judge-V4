import java.util.*;
import java.io.*;
public class MagicPowder
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair>
    {
	int a,b,num,index;
	Pair(int a,int b,int index)
	{
	    this.a = a;
	    this.b = b;
	    num = b / a;
	    this.index = index;
	}
	Pair(){

	}
	@Override
	public int compareTo(Pair o) {
	    return num != o.num ?Integer.compare(num, o.num):Integer.compare(index, o.index);
	}
    }
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

	int N = s1.nextInt();
	int K = s1.nextInt();
	int a[] = s1.nextIntArray(N);
	int b[] = s1.nextIntArray(N);
	MultiSet<Pair> mSet = new MultiSet<>();
	for(int i=0;i<N;i++)
	    mSet.add(new Pair(a[i], b[i],i));

	while(K > 0){
	    Pair curr = mSet.firstKey();
	    mSet.remove(curr);
	    int need = (curr.a * (curr.num + 1)) - curr.b;
	    if(need <= K){
		curr.b += need;
		curr.num = curr.b / curr.a;
	    }
	    K -= need;		
	    mSet.add(curr);
	}

	out.print(mSet.firstKey().num);
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