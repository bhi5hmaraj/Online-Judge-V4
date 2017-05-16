import java.util.*;
import java.io.*;
class VCAKE
{


    /************************ SOLUTION STARTS HERE ***********************/    

    static boolean findPossible(int index,String curr,long R,long C,long arr[]){

	if(index == 2){
	    int pos = Integer.parseInt(curr);
	    return R * C == arr[pos];
	}
	else{

	    boolean flag3 = false;

	    for(int i=0;i<curr.length();i++){
		
		boolean flag1 = false , flag2 = false;
		int pos = Character.getNumericValue(curr.charAt(i));
		
		if(arr[pos] % R == 0 && arr[pos] / R < C){
		    flag1 = true;
		    flag1 &= findPossible(index+1, curr.substring(0, i) + curr.substring(i+1), R, C - (arr[pos] / R), arr);
		}
		if(arr[pos] % C == 0 && arr[pos] / C < R){
		    flag2 = true;
		    flag2 &= findPossible(index+1, curr.substring(0, i) + curr.substring(i+1) , R - (arr[pos]/C), C, arr);
		}

		flag3 |= (flag1 | flag2);

	    }

	    return flag3;

	}
    }


    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    long R = s1.nextLong();
	    long C = s1.nextLong();
	    long arr[] = s1.nextLongArray(3);
	    out.println(findPossible(0, "012", R, C, arr) ? "Yes" : "No");
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