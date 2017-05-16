import java.util.*;
import java.io.*;
public class CountLuck
{

    
    /************************ SOLUTION STARTS HERE ***********************/
    private static int R,C;
    private static char grid[][];
    private static boolean isValid(int i,int j){
	return (i >= 0 && i <R && j >=0 && j<C && grid[i][j] != 'X');
    }
    private static int x[] = {-1,0,1,0};
    private static int y[] = {0,1,0,-1};
    private static boolean marked[][];
    private static int from[][][];
    
    private static void dfs(int i,int j,int pi,int pj){
	
	marked[i][j] = true;
	from[i][j][0] = pi;
	from[i][j][1] = pj;
	for(int k=0;k<4;k++){
	    if(isValid(i+x[k], j+y[k]) && !marked[i+x[k]][j+y[k]])
		dfs(i+x[k], j+y[k], i, j);
	}
	
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0){
	    
	    R = s1.nextInt();
	    C = s1.nextInt();
	    grid = new char[R][C];
	    marked = new boolean[R][C];
	    from = new int [R][C][2];
	    int s_x = 0,s_y = 0,e_x = 0,e_y = 0;
	    for(int i=0;i<R;i++){
		String in = s1.nextLine();
		for(int j=0;j<C;j++){
		    grid[i][j] = in.charAt(j);
		    if(grid[i][j] == 'M'){			
			s_x = i;
			s_y = j;
		    }
		    if(grid[i][j] == '*'){
			e_x = i;
			e_y = j;
		    }
		}
	    }
	    int K = s1.nextInt();
	    dfs(s_x, s_y, -1, -1);
	    int cnt = 0;
	    int cur_x = from[e_x][e_y][0];
	    int cur_y = from[e_x][e_y][1];
	    while(!(cur_x == -1 && cur_y == -1)){
		int ct = 0;
		//System.out.println(cur_x + " " +cur_y);
		for(int k=0;k<4;k++)
		    ct += isValid(cur_x+x[k], cur_y+y[k]) ? 1 : 0;
		
		if(cur_x == s_x && cur_y == s_y)
		    cnt += (ct -1) > 0 ? 1: 0;
		else
		    cnt += (ct - 2) > 0 ? 1: 0;
		int oldX = cur_x;
		cur_x = from[cur_x][cur_y][0];
		cur_y = from[oldX][cur_y][1];
		
	    }
	    //System.err.println(cnt);
	    out.println(cnt == K ? "Impressed" : "Oops!");
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