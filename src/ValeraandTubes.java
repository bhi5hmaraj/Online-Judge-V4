import java.util.*;
import java.io.*;
public class ValeraandTubes
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int m = s1.nextInt();
	int k = s1.nextInt();
	int i = 1 , j = 1 ;


	if(m % 2 == 1 && k - 1 > ((m - 1) / 2) * n){  //Special case when number of columns is odd and we can't fit in

	    for(int z = 1 ; z <= (((m - 1) / 2) * n) ; z++ , j += 2){
		if(j >= m){
		    j = 1;
		    i++;
		}
		out.println("2 " + i + " " + j + " " + i + " " + (j + 1));
	    }
	    
	    k -= (n * ((m - 1)/2));
	    i = 1;
	    j = m;
	    for(int z = 1;z <= k-1;z++){
		out.println("2 " + i + " " + j + " " + (i + 1) + " " + j);
		i += 2;
	    }
	    out.print((n - i + 1));
	    while(i <= n)
		out.print(" "+ (i++) + " " + j);
	    
	    
	    return;
	}

	boolean marked[][] = new boolean[n+1][m+1];
	for(int z = 1 ; z <= k - 1 ; z++ , j += 2){
	    if(j >= m){
		j = 1;
		i++;
	    }
	    out.println("2 " + i + " " + j + " " + i + " " + (j + 1));
	    marked[i][j] = marked[i][j + 1] = true;
	}

	ArrayList<String> moves = new ArrayList<>();
	if(m % 2 == 0){
	    if(j >= m){
		j = 1;
		i++;
	    }

	    boolean flip = true;
	    while(true){

		if(i > n)
		    break;

		if(flip){
		    if(j > m){
			j = m;
			i++;
			flip = !flip;
			continue;
		    }
		    moves.add(" " +i + " " + j + " " + i + " " + (j + 1));
		    j += 2;
		}
		else{
		    if(j < 1){
			j = 1;
			i++;
			flip = !flip;
			continue;
		    }
		    moves.add(" " +i + " " + j + " " + i + " " + (j - 1));
		    j -= 2;
		}
	    }
	    out.print(2 * moves.size());
	    for(String s:moves)
		out.print(s);
	}
	else{

	    i = 1;
	    j = m;
	    boolean flip = true;

	    while(true){


		if(j < 1)
		    break;


		if(flip){

		    while(i <= n && marked[i][j])
			i++;

		    if(i > n){
			i = n;
			j--;
			flip = !flip;
			continue;
		    }
		    moves.add(" " + (i++) + " " + j);
		}
		else{

		    if(i < 1 || marked[i][j]){
			i = 1;
			j--;
			flip = !flip;
			continue;
		    }
		    moves.add(" " + (i--) + " " + j);
		}

	    }
	    out.print(moves.size());
	    for(String s:moves)
		out.print(s);
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