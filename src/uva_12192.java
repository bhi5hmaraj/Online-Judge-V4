import java.util.*;
import java.io.*;
public class uva_12192
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static int striclyLess(int arr[],int key){

	int lo = 0;
	int hi = arr.length - 1;
	int cnt = 0;
	while(lo <= hi){

	    int mid = (lo + hi) / 2;
	    if(key > arr[mid]){
		cnt += (mid - lo + 1);
		lo = mid + 1;
	    }
	    else
		hi = mid - 1;	    
	}
	return cnt;
    }

    private static int[] merge(int a[] , int b[])
    {
	if(a[1] < b[0] || b[1] < a[0])
	    return new int[]{1,0};
	else{
	    if(b[0] >= a[0] && b[1] <= a[1])
		return new int[]{b[0] , b[1]};
	    else if(b[0] < a[0] && b[1] > a[1])
		return new int[]{a[0],a[1]};
	    else if(b[1] >= a[0] && b[1] <= a[1])
		return new int[]{a[0] , b[1]};
	    else
		return new int[]{b[0] , a[1]};
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int N,M;
	while((N = s1.nextInt()) != 0  && (M = s1.nextInt()) != 0)
	{
	    int arr[][] = new int[N][];
	    for(int i=0;i<N;i++)
		arr[i] = s1.nextIntArray(M);
	    int Q = s1.nextInt();
	    while(Q-->0)
	    {
		int L = s1.nextInt();
		int R = s1.nextInt();
		int search[][] = new int[N][2];
		for(int i=0;i<N;i++){
		    search[i][0] = striclyLess(arr[i], L);
		    search[i][1] = striclyLess(arr[i], R + 1) - 1;
		}

		int max_size = 0;
		//System.out.println("query "+Q);
		for(int i=0;i<N;i++)
		{
		    int interval[] = {search[i][0],search[i][1]};
		    //System.out.println(Arrays.toString(interval));
		    int inter_len  = interval[1] - interval[0] + 1;
		    int end = i + inter_len;
		    max_size = inter_len > 0 ? Math.max(1,max_size) : max_size;
		    int j = i + 1;
		    while(j < N && j < end && inter_len > max_size)
		    {
			//System.out.println("before merge " + Arrays.toString(interval) + " , "+ Arrays.toString(search[j]));
			interval = merge(interval, search[j]);
			//System.out.println("After merge "+ Arrays.toString(interval));
			inter_len  = interval[1] - interval[0] + 1;
			//System.out.println("i " + i +" j " + j);
			max_size = Math.max(max_size,Math.min(inter_len,j-i+1));
			//System.out.println("max "+max_size);
			j++;
		    }
		}
		out.println(max_size);
	    }
	    out.println("-");
	}

    }

    private static void solve2(FastScanner s1, PrintWriter out){

	int N,M;
	while((N = s1.nextInt()) != 0  && (M = s1.nextInt()) != 0)
	{
	    int arr[][] = new int[N][];
	    for(int i=0;i<N;i++)
		arr[i] = s1.nextIntArray(M);
	    int Q = s1.nextInt();
	    while(Q-->0)
	    {
		int L = s1.nextInt();
		int R = s1.nextInt();
		int max = 0;
		for(int i=0;i<N;i++)
		{
		    int lb = striclyLess(arr[i], L);
		    for(int diag = max; diag < N;diag++)
		    {			    
			if( i + diag < N && lb + diag < M && arr[i + diag][lb + diag] <= R)
			    max = Math.max(max,diag + 1);
			else
			    break;
		    }
		}
		out.println(max);
	    }
	    out.println("-");
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