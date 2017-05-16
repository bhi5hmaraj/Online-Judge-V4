import java.util.*;
import java.io.*;
public class BearAndRandomVowel
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static boolean isSet(long N,int i){
	return ((1L << i)&N) != 0;
    }
    private static long set(long N,int i){
	return (N | (1L << i));
    }
    private static boolean isHard(long bits, int len){

	int vow = Long.bitCount(bits);
	if(vow < (len - vow))
	    return true;
	else
	{		
	    boolean flag = false;
	    for(int i=0;i<len-2;i++)
		flag |= !isSet(bits, i) && !isSet(bits, i+1) && !isSet(bits, i+2);

	    return flag;
	}
    }
    private static boolean isVowel(char ch)
    {
	switch(ch)
	{
	case 'a':case 'e':case 'i':case 'o':case 'u':case 'y':return true;
	}
	return false;
    }
    static int cnt = 0;
    private static int countWays(long orgBits, long currBits, int index, int k , int len){
	if(index == len){
	    if(Long.bitCount(currBits) == k)
		return isHard(currBits | orgBits, len) ? 1 : 0;
	    else
		return 0;
	}
	else{
	    return countWays(orgBits, set(currBits, index), index + 1, k , len) + countWays(orgBits, currBits, index + 1, k , len);
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int len = s1.nextInt();
	int k = s1.nextInt();
	char arr[] = s1.nextLine().toCharArray();
	long bits = 0;
	for(int i=0;i<len;i++){
	    bits = (isVowel(arr[len - i -1])) ? set(bits, i) : bits;
	}
	out.print(countWays(bits, 0, 0, k, len));	
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