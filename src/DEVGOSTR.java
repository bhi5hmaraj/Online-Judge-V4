import java.util.*;
import java.io.*;
class DEVGOSTR
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static String possible[];
    private static int index;
    private static int len;
    private static char a[];
    private static String str;
    private static int ham;
    private static void generate(String curr,String possible[])
    {
	if(curr.length() == len)
	    possible[index++] = curr;
	else
	{
	    for(char ch:a)
		generate(curr + ch,possible);
	}
    }
    private static boolean isGood(String curr)
    {
	int cnt = 0;
	for(int i=0;i<len;i++)
	    cnt += (str.charAt(i) != curr.charAt(i)?1:0);

	if(cnt > ham)
	    return false;

	boolean flag = true;

	for(int size = 1;;size++)
	{
	    int i = 0;
	    int j = i + size;
	    int k = i + (2*size);
	    if(k >= len)
		break;

	    for (; k < len; i++, j = i + size, k = i + (2 * size))
	    {
		flag &= !(curr.charAt(i) == curr.charAt(j) && curr.charAt(j) == curr.charAt(k));		
	    }
	    if(!flag)
		return flag;
	}
	return flag;
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	ArrayList<String[]> cache = new ArrayList<>(15);
	a = new char[]{'a','b','c'};
	for(len = 1;len <= 12;len++)
	{
	    index = 0;
	    String arr[] = new String[(int)Math.pow(3, len)];
	    generate("", arr);
	    cache.add(arr);	
	}
	while(t-->0)
	{
	    int alph = s1.nextInt();
	    ham = s1.nextInt();
	    str = s1.nextLine();
	    len = str.length();
	    index = 0;
	    if(alph==1)
	    {
		int len = str.length();
		if(len == 1 || len == 2)
		    out.println(1);
		else
		    out.println(0);
	    }

	    else if(alph == 3)
	    {
		int cnt = 0;
		for(String s:cache.get(len-1))
		    cnt += (isGood(s)) ? 1 : 0;

		out.println(cnt);
	    }		
	    else
	    {
		possible = new String[1<<len];
		a = new char[]{'a','b'};
		generate("",possible);
		int cnt = 0;
		for(String s:possible)
		    cnt += (isGood(s)) ? 1 : 0;

		out.println(cnt);
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