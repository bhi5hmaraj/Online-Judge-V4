import java.util.*;
import java.io.*;
public class TheBombermanGame
{


    /************************ SOLUTION STARTS HERE ***********************/


    static class Pair {
	int r, c;

	Pair(int r, int c) {
	    this.r = r;
	    this.c = c;
	}
    }

    static int R , C;
    static char grid[][];
    static boolean isValid(int i, int j){
	return i >= 0 && i < R && j >= 0 && j < C;
    }

    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,-1,0,1};

    static void fill(){
	for(int i=0;i<R;i++)
	    Arrays.fill(grid[i], 'O');
    }

    static ArrayList<Pair> blast(ArrayList<Pair> locations){

	ArrayList<Pair> next = new ArrayList<>();

	for(Pair p:locations){
	    int i = p.r , j = p.c;
	    grid[i][j] = '.';
	    for(int k = 0;k<4;k++)
		if(isValid(i + dx[k], j + dy[k]))
		    grid[i+dx[k]][j+dy[k]] = '.';
	}

	for (int i = 0; i < R; i++)
	    for (int j = 0; j < C; j++)
		if (grid[i][j] == 'O')
		    next.add(new Pair(i, j));

	return next;
    }

    private static void solve(FastScanner s1, PrintWriter out){  //Slow Solution	

	R = s1.nextInt();
	C = s1.nextInt();
	int N = s1.nextInt();
	grid = new char[R][];
	for(int i=0;i<R;i++)
	    grid[i] = s1.nextLine().toCharArray();

	ArrayList<Pair> locations = new ArrayList<>();
	for(int i=0;i<R;i++)
	    for(int j=0;j<C;j++)
		if(grid[i][j] == 'O')
		    locations.add(new Pair(i, j));

	for(int time = 0;time < N - 1;time++){

	    switch(time % 2){
	    case 0:
		fill();
		break;
	    case 1:
		locations = blast(locations);
		break;
	    }

	}

	for(int i=0;i<R;i++)
	    out.println(new String(grid[i]));
    }
    /*
a bcba bcba 
1 2345 6789
  1234  
     */ 
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