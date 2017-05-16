import java.util.*;
import java.io.*;
class FRJUMP_hybrid
{


    /************************ SOLUTION STARTS HERE ***********************/

    static final double epsilon = 1e-8;

    static class ModMath {
	static final long mod = (long) (1e9) + 7L; // Default

	static long sub(long a, long b) {
	    long sub = ((a % mod) - (b % mod)) % mod;
	    return sub < 0 ? mod + sub : sub;
	}

	static long mul(long a, long b) {
	    return ((a % mod) * (b % mod)) % mod;
	}

	static long add(long a, long b) {
	    return ((a % mod) + (b % mod)) % mod;
	}

	static long div(long a, long b) {
	    return mul(a, modInverse(b));
	}

	static long modInverse(long n) { // Fermat's little theorem
	    return modPow(n, mod - 2L);
	}

	static long modPow(long a, long b) { // squared exponentiation
	    if (b == 0L || a == 1L)
		return 1L;
	    else if (b == 1L)
		return a;
	    else {
		if ((b & 1L) == 0L) // Checking whether b is even (fast)
		    return modPow((a * a) % mod, b >> 1);
		else
		    return (a * modPow((a * a) % mod, b >> 1)) % mod;
	    }
	}
    }
    private static int logToFirstDigit(double log_prod){

	double mantissa = log_prod - (int)(log_prod);
	double ans = Math.pow(10.0, mantissa);
	if(Math.abs(Math.round(ans) - ans) < epsilon)
	    ans = Math.round(ans);
	int toInt = (int)(ans);
	return toInt < 10 ? toInt : 1; //Sometimes its getting rounded of to 10 !!
    }
    
    private static ArrayList<String> solveHelper(int N,long arr[],int Q,String query[]){

	ArrayList<String> ans = new ArrayList<>();
	for(int t = 0;t<Q;t++){
	    String split[] = query[t].split(" ");
	    int choice = Integer.parseInt(split[0]);
	    if(choice == 1){
		arr[Integer.parseInt(split[1])] = Long.parseLong(split[2]);
	    }
	    else{
		long p = 1;
		int R = Integer.parseInt(split[1]);
		double log_prod = 0.0;
		for(int i=0;1 + (i * R) <= N;i++){
		    log_prod += Math.log10(arr[1 + (i * R)]);
		    p = ModMath.mul(p , arr[1 + (i * R)]);
		}
		ans.add(logToFirstDigit(log_prod)+ " " + p);
	    }
	}
	
	return ans;

    }
    
    
    private static ArrayList<String> solve(int N,long ar[],int Q,String query[]){

	long start = System.nanoTime();
	
	long arr[] = Arrays.copyOf(ar, ar.length);
	
	double logOfprod[] = new double[N + 1]; //for each R
	long modOfprod[] = new long[N+1];
	Arrays.fill(modOfprod, 1);
	@SuppressWarnings("unchecked")
	ArrayList<Integer>[] RstoChange = (ArrayList<Integer>[]) new ArrayList[N + 1];
	for(int i=1;i<=N;i++)
	    RstoChange[i] = new ArrayList<>();

	for(int r = 1;r <= N;r++)
	{
	    for(int index = 1;index <= N ;index += r)
	    {
		logOfprod[r] += Math.log10(arr[index]);
		modOfprod[r]  = ModMath.mul(modOfprod[r], arr[index]);
		if(index != 1)
		    RstoChange[index].add(r);
	    }
	}


	long prevValOf1[] = new long[N+1];
	Arrays.fill(prevValOf1, arr[1]);
	ArrayList<String> ans = new ArrayList<>();
	long stop = System.nanoTime();
	
	long time = (stop-start);
	for(int t=0;t<Q;t++)
	{
	    start = System.nanoTime();
	    String split[] = query[t].split(" ");
	    int choice = Integer.parseInt(split[0]);
	    if(choice == 1)
	    {
		int pos = Integer.parseInt(split[1]);
		long newVal = Integer.parseInt(split[2]);
		long oldVal = arr[pos];
		arr[pos] = newVal;		
		if(pos != 1)
		{
		    for(int j=0,sz=RstoChange[pos].size();j<sz;j++)
		    {
			int i = RstoChange[pos].get(j);
			logOfprod[i] = (logOfprod[i] - Math.log10(oldVal)) + Math.log10(newVal);
			modOfprod[i] = ModMath.mul(ModMath.div(modOfprod[i], oldVal),newVal);
		    }
		}

	    }
	    else
	    {
		int r = Integer.parseInt(split[1]);	
		logOfprod[r] = logOfprod[r] - Math.log10(prevValOf1[r]) + Math.log10(arr[1]);
		modOfprod[r] = ModMath.mul(ModMath.div(modOfprod[r], prevValOf1[r]), arr[1]);
		prevValOf1[r] = arr[1];
		ans.add(logToFirstDigit(logOfprod[r]) + " " + modOfprod[r]);
	    }
	    stop = System.nanoTime();
	    time += ((stop-start));
	    if((time/1e9) >= 1.7){
		return solveHelper(N, ar, Q, query);
	    }
	}
	return ans;
    }




    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    static String outputFile = "fast_FRJUMP.txt";
    
    public static void main(String []args) throws IOException {
	FastScanner s1  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	int N = s1.nextInt();
	long arr[] = s1.nextLongArrayOneBased(N);
	int Q = s1.nextInt();
	String query[] = new String[Q];
	for(int i=0;i<Q;i++)
	    query[i] = s1.nextLine();
	ArrayList<String> ans = solve(N,arr,Q,query);
	for(String s : ans)
	    out.println(s);
	s1.close();
	out.close();
    } 

/*

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
	PrintWriter out = 
		new PrintWriter(outputFile); 
	solve(in, out);
	in.close();
	out.close();
    }    
    */
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