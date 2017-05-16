import java.util.*;
import java.io.*;
class ThelegendofTanmay
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve1(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int N = s1.nextInt();
	    int MAX = 1 << N;
	    long arr[] = s1.nextLongArray(N);
	    long min = Long.MAX_VALUE;
	    long max = Long.MIN_VALUE;
	    for (int i = 1; i < MAX; i++)
	    {
		long prod = 1;
		for (int j = 0; j < N; j++)
		{
		    if ((i & (1 << j)) != 0)
			prod *= arr[j];
		}
		min = Math.min(min,prod);
		max = Math.max(max,prod);
	    }
	    out.println(max + " " + min);
	}

    }

    private static void solve2(FastScanner s1, PrintWriter out){
	int t = s1.nextInt();
	while(t-->0)
	{
	    int N = s1.nextInt();
	    long arr[] = s1.nextLongArray(N);
	    boolean flag = true;
	    for(long l:arr)flag&=l==0;
	    long min1 = Long.MAX_VALUE,max1 = Long.MIN_VALUE,min2=Long.MAX_VALUE,max2=Long.MIN_VALUE;;
	    long min = 1,max = 1;
	    if(flag)
		out.println("0 0");
	    else
	    {
		int ctPos=0,ctNeg=0;
		for(long l:arr)
		{
		    if(l >0)ctPos++;
		    if(l <0)ctNeg++;
		}

		
		    long prd = 1;
		    long minNeg = Long.MAX_VALUE;
		    long maxNeg = Long.MIN_VALUE;
		    for(long l:arr)		    
			if(l < 0)
			{
			    minNeg = Math.min(minNeg,l);
			    maxNeg = Math.max(maxNeg,l);
			    prd *= l;
			}

		    if(ctNeg % 2 == 0)
		    {
			max1 = prd;
			min1 = prd / maxNeg;			
		    }
		    else
		    {
			max1 = prd / maxNeg;
			if(ctNeg == 1 && ctPos == 0)
			{
			    max1 = Long.MIN_VALUE;
			    for(long l:arr)
				max1 = Math.max(max1,l);
			}
			min1 = prd;
		    }
		

		    prd = 1;
		    for(long l:arr)
		    {
			min2 = Math.min(min2,l);
			prd = (l > 0)?prd*l:prd;
		    }
		    max2 = prd;
		

		if(ctNeg != 0 && ctPos != 0)
		{
		    min = min1 * max2;
		    max = max1 * max2;

		}
		else
		{
		    if(ctPos==0)
		    {
			min = min1;
			max= max1;	
		    }
		    else
		    {
			min = min2;
			max = max2;
		    }
		}
		out.println(max + " " + min);
	    }   
	}

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