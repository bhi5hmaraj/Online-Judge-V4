import java.util.*;
import java.io.*;
class BennyAndTheBrokenOdometer
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    private static HashMap<Integer,Integer> cache = new HashMap<>();
    
    private static int count(int N){
	
	Integer hit = cache.get(N);
	if(hit != null)
	    return hit;
	
	if(N < 3){
	    cache.put(N, N);
	    return N;
	}
	else if(N < 10){
	    cache.put(N, N-1);
	    return N - 1;
	}
	else {
	    
	    int powOfTen = 1;
	    while(powOfTen * 10 <= N)
		powOfTen *= 10;
	    
	    int msb = N / powOfTen;
	    int cnt;
	    if(msb == 3)
		cnt = count((3 * powOfTen) - 1);
	    else
		cnt = (count(msb) * count(powOfTen-1)) + count(msb) + count(N % powOfTen);

	    cache.put(N, cnt);
	    return cnt;	    
	}
	
    }
    
    
    private static void solve1(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	int MAX = (int)	1e7;
	int cnt[] = new int[MAX+1];
	
	for(int i=1;i<=MAX;i++)
	    cnt[i] = String.valueOf(i).indexOf('3') >= 0? 1 : 0;
	for(int i=1;i<=MAX;i++)
	    cnt[i] += cnt[i-1];
	while(t-->0)
	{
	    int n = s1.nextInt();
	    out.println(n - cnt[n]);
	}

    }

    private static void solve2(FastScanner s1, PrintWriter out){

   	int t = s1.nextInt();
   	while(t-->0)
   	    out.println(count(s1.nextInt()));
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