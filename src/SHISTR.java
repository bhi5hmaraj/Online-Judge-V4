import java.util.*;
import java.io.*;
class SHISTR
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	char alph[] = new char[26];
	char a='a';
	for(int i=0;a<='z';a++,i++)
	    alph[i] = a;
	while(t-->0)
	{
	    int N = s1.nextInt();
	    int Q = s1.nextInt();
	    char str[] = s1.nextLine().toCharArray();	    
	    int arr[] = new int[N+2];
	    while(Q-->0)
	    {
		int L = s1.nextInt();
		int R = s1.nextInt();
		int K = s1.nextInt();
		arr[L] += K;
		arr[R+1] -= K;
	    }
	    for(int i=1;i<=N;i++)
		arr[i] += arr[i-1];
	    
	    for(int i=1;i<=N;i++)
	    {
		if (arr[i] > 0)
		    str[i - 1] = alph[((str[i - 1] - 'a') + arr[i]) % 26];
		else
		{
		    int change = (-arr[i])%26;
		    if((str[i-1]-'a') >= change)
			str[i-1] = alph[(str[i-1]-'a')-change];
		    else
			str[i-1] = alph[26-(change-(str[i-1]-'a'))];
		}
	    }
	    for(char c:str)
		out.print(c);
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