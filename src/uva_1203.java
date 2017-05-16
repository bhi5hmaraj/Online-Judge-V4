import java.util.*;
import java.io.*;
public class uva_1203
{


    /************************ SOLUTION STARTS HERE ***********************/


    static class Pair implements Comparable<Pair>
    {
	int q_num,time;
	Pair(int q_num,int time)
	{
	    this.q_num = q_num;
	    this.time = time;
	}
	@Override
	public int compareTo(Pair o) {
	    if(this.time != o.time)
		return this.time - o.time;
	    else
		return this.q_num - o.q_num;
	}

    }


    private static void solve(FastScanner s1, PrintWriter out){

	String in = null;
	PriorityQueue<Pair> pq = new PriorityQueue<>(10000);
	HashMap<Integer,Integer> mp = new HashMap<>();
	while(!(in = s1.nextLine()).equals("#"))
	{
	    Scanner fuckYouUva = new Scanner(in);
	    boolean fuck = false;
	    int q_num=0,time=0;
	    while(fuckYouUva.hasNext())
	    {
		String shit = fuckYouUva.next();
		try
		{
		    if(!fuck)
		    {
			q_num = Integer.parseInt(shit);
			fuck = true;
		    }
		    else
			time = Integer.parseInt(shit);
		}
		catch(Exception e){continue;}
	    }
	    fuckYouUva.close();
	    pq.add(new Pair(q_num, time));
	    mp.put(q_num, time);
	}

	int K = s1.nextInt();
	while(K-->0)
	{
	    Pair curr = pq.remove();
	    pq.add(new Pair(curr.q_num,curr.time + mp.get(curr.q_num)));
	    out.println(curr.q_num);
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