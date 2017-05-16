import java.util.*;
import java.io.*;
class MMPROD
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair>
    {
	int index, abs, key;
	Pair(int index,int key)
	{
	    this.index = index;
	    this.key = key;
	    abs = Math.abs(key);
	}
	@Override
	public int compareTo(Pair o) {
	    return Integer.compare(o.abs,abs);
	}
	@Override
	public String toString() {
	    return "key "+key;
	}
    }

    static long mul(long a, long b) {
	return ( (a % mod) * (b % mod) ) % mod;
    }
    static final long mod = (long) (1e9) + 7L; // Default

    private static long mullAll(Iterable<Integer> iter){

	long prod = 1;
	for(int i:iter)
	    prod = mul(prod, i);

	return prod;
    }
    private static long absMod(long n){
	return n < 0 ? (n % mod) + mod : (n % mod);
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int N = s1.nextInt();
	    int K = s1.nextInt();
	    int arr[] = s1.nextIntArray(N);

	    int cntNon0 = 0;
	    int cnt0 = 0;
	    for(int a:arr)
		cntNon0 += ( a != 0 ) ? 1 : 0;
	    cnt0 = N - cntNon0;
	    if(cntNon0 < K)
		out.println(0);
	    else
	    {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		boolean noPos = true;
		for(int i=0;i<N;i++){
		    pq.add(new Pair(i, arr[i]));
		    noPos &= arr[i] <= 0; 
		}

		PriorityQueue<Integer> pos = new PriorityQueue<>();
		PriorityQueue<Integer> neg = new PriorityQueue<>();
		for(int i=1;i<=K;i++){
		    Pair p = pq.remove();
		    if(p.key > 0){
			pos.add(p.abs);
		    }
		    else if(p.key < 0){
			neg.add(p.abs);
		    }
		}

		if(neg.size() % 2 == 0){
		    out.println(mul(mullAll(neg),mullAll(pos)));
		}
		else{
		    if(noPos){			
			if(cnt0 > 0)
			    out.println(0);
			else{
			    PriorityQueue<Integer> maxK = new PriorityQueue<>(K);
			    for(int a:arr){
				maxK.add(a);
				if(maxK.size() > K)
				    maxK.remove();
			    }

			    out.println(absMod(mullAll(maxK)));
			}
		    }
		    else{

			if(neg.size() == K){

			    int max = 0;
			    while((max = pq.remove().key) < 0)
				;

			    neg.remove();
			    out.println(mul(max, mullAll(neg)));
			}
			else{			    
			    int bigPos = 0,bigNeg = 0;
			    while(!pq.isEmpty() && (bigPos == 0 || bigNeg == 0))
			    {
				Pair p = pq.remove();
				bigPos = (bigPos == 0 && p.key > 0) ? p.abs : bigPos;
				bigNeg = (bigNeg == 0 && p.key < 0) ? p.abs : bigNeg;
			    }
			    if(bigNeg == 0 && bigPos == 0){	
				if(cnt0 > 0)
				    out.println(0);
				else
				    out.println(absMod(-mul(mullAll(pos), mullAll(neg))));
			    }
			    else{
				long negMult = (long)neg.peek() * (long)(bigNeg);
				long posMult = (long)pos.peek() * (long)(bigPos);
				if(negMult > posMult){
				    neg.add(bigNeg);
				    pos.remove();
				}
				else{
				    neg.remove();
				    pos.add(bigPos);
				}

				out.println(mul(mullAll(pos), mullAll(neg)));
			    }
			}
		    }
		}
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

    /*
    static String outputFile = "MMPROD_fast_out.txt";
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