import java.util.*;
import java.io.*;
public class dq
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	for(int z=1;z<=t;z++)
	{
	    out.println("Case "+z+":");
	    ArrayDeque<Integer> dq = new ArrayDeque<>();
	    int size = s1.nextInt();
	    int q = s1.nextInt();
	    while(q-->0)
	    {
		String com = s1.next();
		switch(com)
		{
		case "pushLeft":
		int num = s1.nextInt();
		    if(dq.size() == size)
		    {
			out.println("The queue is full");
			break;
		    }
		    dq.addFirst(num);
		    out.println("Pushed in left: "+num);
		    break;
		case "pushRight":
		    num = s1.nextInt();
		    if(dq.size() == size)
		    {
			out.println("The queue is full");
			break;
		    }
		    dq.addLast(num);
		    out.println("Pushed in right: "+num);
		    break;
		case "popLeft":
		    if(dq.size() == 0)
		    {
			out.println("The queue is empty");
			break;
		    }
		    int rem = dq.removeFirst();
		    out.println("Popped from left: "+rem);
		    break;
		case "popRight":
		    if(dq.size() == 0)
		    {
			out.println("The queue is empty");
			break;
		    }
		    rem = dq.removeLast();
		    out.println("Popped from right: "+rem);
		    break;
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