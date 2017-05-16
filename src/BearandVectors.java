import java.util.*;
import java.io.*;
public class BearandVectors
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static boolean isPerpendicular(long... val)
    {
	return ((val[0] * val[2]) + (val[1] * val[3])) == 0;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	long vector[][] = new long[N][2];
	for(int i=0;i<N;i++)
	{
	    vector[i] = s1.nextLongArray(2);
	    if(vector[i][0] == 0 && vector[i][1] == 0)
		throw new IllegalArgumentException("null vector");
	}
	int cnt = 0;
	for (int i = 0; i < N; i++)
	{
	    for (int j = 0; j < N; j++)
	    {
		if(j != i)
		{
		    for (int k = 0; k < N; k++)
		    {
			if(k != i && k != j)
			{
			    long x = vector[j][0] + vector[k][0];
			    long y = vector[j][1] + vector[k][1]; 
			    if(!(x == 0 && y == 0) && isPerpendicular(vector[i][0], vector[i][1],  x , y))	{		    
				cnt ++;
				System.out.println(" i ["+vector[i][0]+", "+vector[i][1]+"] j ["+vector[j][0]+", "+vector[j][1]+"] k ["+vector[k][0]+", "+vector[k][1]+"]");
			    }
			}
		    }
		}
	    }
	}

	out.println(cnt);
    }

    static class vector
    {
	long x,y;	
	long nume,deno;
	vector(long x,long y)
	{
	    this.x = x;
	    this.y = y;	    
	    long absX = Math.abs(x);
	    long absY = Math.abs(y);
	    nume = absX / gcd(absX, absY);
	    deno = absY / gcd(absX, absY);
	    nume *= Long.signum(x * y);
	}
	vector()
	{
	    this(0,0);
	}
	long dot(vector that)
	{
	    return (x * that.x) + (y * that.y);
	}
	vector negative(){
	    return new vector(-x, -y);
	}
	@Override
	public boolean equals(Object obj) {
	    vector that = (vector)obj;
	    return x == that.x && y == that.y;
	}
	@Override
	public int hashCode() {
	    if(nume == 0 &&  deno == 0){
		return Objects.hash(0,0);
	    }
	    else if(nume == 0 && deno != 0){
		return Objects.hash(0,(long)(3e9));
	    }
	    else if(nume != 0 && deno == 0){
		return Objects.hash((long)(3e9),0);
	    }
	    else{
		return Objects.hash(nume,deno);
	    }

	}
	@Override
	public String toString() {
	    return "x = "+x+" y = "+y;
	}
    }

    private static <Key> void frequency(Map<Key, Integer> mp, Key k) {
	Integer query = mp.get(k);
	mp.put(k, query == null ? 1 : query + 1);
    }
    private static <Key> Integer get(Map<Key, Integer> mp, Key k) {
	Integer query = mp.get(k);
	return query == null ? 0 : query;
    }
    private static void solve2(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	vector arr[] = new vector[N];	
	HashMap<vector,Integer> freq = new HashMap<>();
	for(int i=0;i<N;i++){
	    arr[i] = new vector(s1.nextLong(), s1.nextLong());	    
	    frequency(freq, arr[i]);
	}
	long negCnt = 0;
	for (Map.Entry<vector,Integer> e : freq.entrySet()) 	
	    negCnt += (e.getValue().longValue() * get(freq, e.getKey().negative()).longValue());

	negCnt /= 2;
	System.out.println(negCnt);

	long cnt = 0;
	for(int i=0;i<N;i++)
	{
	    HashMap<Long,Integer> dotFreq = new HashMap<>();
	    for(int j=0;j<N;j++)
		if(j != i)
		    frequency(dotFreq, arr[i].dot(arr[j]));

	    System.out.println(arr[i]+ " " +dotFreq);

	    for (Map.Entry<Long,Integer> e : dotFreq.entrySet()) 
	    {
		if(e.getKey().longValue() == 0)		
		{
		    if( e.getValue() >= 2)
		    {
			System.out.print("before cnt "+cnt);	
			cnt += (e.getValue().longValue() * (e.getValue().longValue()-1));
			System.out.print(" middle "+cnt);
			cnt -=  2 * (negCnt - get(freq, arr[i].negative()));
			System.out.println(" cnt "+cnt);
		    }
		}
		else
		{		
		    if(dotFreq.get(-e.getKey()) != null)
		    {
			System.out.print("before cnt "+cnt);		    
			cnt += (e.getValue().longValue() * dotFreq.get(-e.getKey()).longValue());
			System.out.print(" middle "+cnt);
			cnt -= (negCnt - get(freq, arr[i].negative()));
			System.out.println(" cnt "+cnt);
		    }
		}
	    }

	}
	out.print(cnt);
    }
    static long gcd(long big, long small) {
	long b = Math.max(big, small);
	long s = Math.min(big, small);
	if (s == 0)
	    return b;
	else
	    return gcd(s, b % s);
    }
    static class Pair
    {
	long x,y;	
	long nume,deno;
	Pair(long x,long y)
	{
	    this.x = x;
	    this.y = y;	    
	    if(x == 0 && y == 0)
	    {
		nume = 0;
		deno = 0;
	    }
	    else
	    {
		long absX = Math.abs(x);
		long absY = Math.abs(y);	    
		if(absX == 0){
		    nume = 0;deno = absY;
		}
		else if(absY == 0){
		    nume = absX;deno = 0;
		}
		else
		{
		    nume = absX / gcd(absX, absY);
		    deno = absY / gcd(absX, absY);
		    nume *= Long.signum(x * y);
		}
	    }
	}
	Pair()
	{
	    this(0,0);
	}
	Pair add(Pair that){
	    return new Pair(x + that.x, y + that.y);
	}
	long dot(Pair that)
	{
	    return (x * that.x) + (y * that.y);
	}
	@Override
	public boolean equals(Object obj) {
	    Pair that = (Pair)obj;
	    if(nume == 0 &&  deno == 0){
		return that.nume == 0 && that.deno == 0;
	    }
	    else if(nume == 0 && deno != 0){
		return that.nume == 0;
	    }
	    else if(nume != 0 && deno == 0){
		return that.deno == 0;
	    }
	    else{
		return nume == that.nume && deno == that.deno;
	    }
	}
	@Override
	public int hashCode() {
	    if(nume == 0 &&  deno == 0){
		return Objects.hash(0,0);
	    }
	    else if(nume == 0 && deno != 0){
		return Objects.hash(0,(long)(3e9));
	    }
	    else if(nume != 0 && deno == 0){
		return Objects.hash((long)(3e9),0);
	    }
	    else{
		return Objects.hash(nume,deno);
	    }

	}
	@Override
	public String toString() {
	    return "x = "+x+" y = "+y+ "["+nume+"/"+deno+"]";
	}
    }
    private static void solve3(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	Pair arr[] = new Pair[N];
	HashMap<Pair,Integer> freq = new HashMap<>();
	HashMap<Long , Integer> dotFreq = new HashMap<>();
	for(int i=0;i<N;i++)arr[i] = new Pair(s1.nextLong(), s1.nextLong());

	for(int i=0;i<N;i++)
	    for(int j=0;j<N;j++)
		if(j != i)
		{
		    Pair sum = arr[i].add(arr[j]);
		    Pair mod = new Pair(-sum.y, sum.x);
		    System.out.println(" mod " + mod +" hash "+mod.hashCode());
		    frequency(freq, mod);
		    frequency(dotFreq, arr[i].dot(arr[j]));
		}
	
	long cnt = 0;
	System.out.println("count");
	for(int i=0;i<N;i++)
	{
	    System.out.println(arr[i] + " = " + get(freq, arr[i]) +" " + get(dotFreq, -arr[i].dot(arr[i])));
	    if(freq.get(arr[i]) != null)
	    cnt += (freq.get(arr[i]) - get(dotFreq, -arr[i].dot(arr[i]))/2);
	}
	System.out.println();
	printMap(freq);
	out.print(cnt);
    }
    static <K,V> void  printMap(Map<K,V> map)
    {
	for (Map.Entry<K,V> e : map.entrySet()) 
	{
	    System.out.println(e.getKey() + " = " + e.getValue());
	}
    }
    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve3(in, out);
	in.close();
	out.close();
    }    

    static String outputFile = "bear_vector_incorrect.txt";
    /*
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
	PrintWriter out = 
		new PrintWriter(outputFile); 
	solve2(in, out);
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