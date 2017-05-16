import java.util.*;
import java.io.*;
public class CricketTournament
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static void swap(int arr[],int i,int j)
    {
	int tem = arr[i];
	arr[i] = arr[j];
	arr[j] = tem;
    }

    private static void solve(FastScanner s1, PrintWriter out){


	int t = s1.nextInt();
	while(t-->0)
	{
	    int score[] = new int[12];
	    int next = 3;
	    int curr[] = {1,2};
	    int wicket = 0;
	    int N = s1.nextInt();
	    int arr[] = s1.nextIntArray(6*N);
	    for(int balls = 0;balls<arr.length;balls++)
	    {
		if(balls!=0 && balls % 6 == 0)
		    swap(curr, 0, 1);

		int res = arr[balls];
		if(res == -1)
		{
		    wicket++;
		    if(wicket == 10)
			break;
		    else
		    {
			curr[0] = next++;
		    }
		}
		else
		{		   		    
		    score[curr[0]] += res;
		    if(res % 2 == 1)
			swap(curr, 0, 1);
		}
	    }
	    for(int i=1;i<=11;i++)
		out.print(score[i] + " ");
	    out.println();
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