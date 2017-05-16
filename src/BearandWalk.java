import java.util.*;
import java.io.*;
class BearandWalk
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair
    {
	int i,j;
	Pair(int... cord)
	{
	    i = cord[0];
	    j = cord[1];
	}
	Pair(){

	}
	@Override
	public boolean equals(Object obj) {
	    Pair that = (Pair)obj;
	    return i == that.i && j == that.j;
	}
	@Override
	public String toString() {
	    return "[i = "+i+" j = "+j+"]";
	}
	boolean isValid()
	{
	    return i >= 0 && i < R && j >= 0 && j < C;
	}
	@Override
	public int hashCode() {
	    return Objects.hash(i,j);
	}
    }
    private static int R ;
    private static int C ;
    private static int dx[] = {0,0,-1,1};
    private static int dy[] = {1,-1,0,0};
    private static char[] moves = {'R','L','U','D'};

    private static Pair move(Pair curr,char ch)
    {	
	int index = -1;
	for (int i = 0; i < 4; i++)
	    if (moves[i] == ch)
		index = i;

	return new Pair(curr.i + dx[index], curr.j + dy[index]);
    }
    private static char direction(Pair from , Pair to)
    {
	for (int i = 0; i < 4; i++)
	    if (from.i + dx[i] == to.i && from.j + dy[i] == to.j)
		return moves[i];

	return '*';
    }

    private static ArrayDeque<Character> bfs(Pair arr[][],Pair start,Pair end)
    {
	ArrayDeque<Pair> queue = new ArrayDeque<>();
	queue.add(start);
	boolean found = false;
	outer:
	    while(!queue.isEmpty())
	    {
		Pair curr = queue.remove();
		for(char ch : moves)
		{
		    Pair moved = move(curr, ch);
		    if(moved.equals(end))
		    {
			arr[moved.i][moved.j] = curr;
			found = true;
			break outer;
		    }
		    if(moved.isValid() && arr[moved.i][moved.j] == null)
		    {
			arr[moved.i][moved.j] = curr;
			queue.add(moved);
		    }
		}
	    }
	if(!found)
	    return null;
	else
	{
	    Pair curr = end;
	    ArrayDeque<Character> stack = new ArrayDeque<>();
	    while(!curr.equals(start))
	    {		
		Pair from = arr[curr.i][curr.j];
		stack.push(direction(from, curr));
		curr = from;
	    }
	    return stack;
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    char moves[] = s1.nextLine().toCharArray();
	    R = /*(2 * 10 * moves.length) + 10;*/ 100;
	    C = /*(2 * 10 * moves.length) + 10;*/  100; 
	    Pair arr[][] = new Pair[R][C];
	    Pair start = new Pair(R/2,C/2);

	    Pair curr = start;
	    for(int i=0;i<moves.length;i++)
	    {
		curr = move(curr, moves[i]);
		arr[curr.i][curr.j] = curr;
	    }	    

	    ArrayDeque<Character> ans = bfs(arr, curr, start);
	    if(ans == null)
		out.print("IMPOSSIBLE");
	    else
		for(char ch:ans)
		    out.print(ch);

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