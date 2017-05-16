import java.util.*;
import java.io.*;
public class WorkingRoutine
{


	/************************ SOLUTION STARTS HERE ***********************/


	static class Node {
		int key;
		Node UP , DOWN , RIGHT , LEFT;
		Node(int key){
			this.key = key;
		}
	}

	static int N , M;
	static Node north[],south[],east[],west[];

	static Node get(int i , int j){

		Node curr = null;
		int Nd = i;
		int Wd = j;
		int Sd = N - i + 1;
		int Ed = M - j + 1;
		int min = Math.min(Math.min(Nd,Wd),Math.min(Sd,Ed));
		int STEP;
		if(min == Nd){
			curr = north[Wd - 1];
			STEP = Nd;
		}
		else if(min == Sd){
			curr = south[Wd - 1];
			STEP = Sd;
		}
		else if(min == Ed){
			curr = east[Nd - 1];
			STEP = Ed;
		}
		else{
			curr = west[Nd - 1];
			STEP = Wd;
		}

		for(;STEP > 0;STEP--){
			if(min == Nd)
				curr = curr.DOWN;
			else if(min == Sd)
				curr = curr.UP;
			else if(min == Ed)
				curr = curr.LEFT;
			else
				curr = curr.RIGHT;
		}

		return curr;

	}

	static void swap(int a , int  b , int c , int d , int h , int w){

		Node n1l = get(a, b);
		Node n1r = get(a + h - 1, b + w - 1);
		Node n2l = get(c, d);
		Node n2r = get(c + h - 1, d + w - 1);
		Node t1,t2,t3,t4;
		for(int i=w;i>=1;i--){
			t1 = n1l.UP;
			t2 = n2l.UP;
			t3 = n1r.DOWN;
			t4 = n2r.DOWN;
			n1l.UP = t2;
			t2.DOWN = n1l;
			n2l.UP = t1;
			t1.DOWN = n2l;
			n1r.DOWN = t4;
			t4.UP = n1r;
			n2r.DOWN = t3;
			t3.UP = n2r;
			if(i != 1){
				n1l = n1l.RIGHT;
				n2l = n2l.RIGHT;
				n1r = n1r.LEFT;
				n2r = n2r.LEFT;
			}
		}
		for(int i=h;i>=1;i--){
			t1 = n1l.RIGHT;
			t2 = n2l.RIGHT;
			t3 = n1r.LEFT;
			t4 = n2r.LEFT;
			n1l.RIGHT = t2;
			t2.LEFT = n1l;
			n2l.RIGHT = t1;
			t1.LEFT = n2l;
			n1r.LEFT = t4;
			t4.RIGHT = n1r;
			n2r.LEFT = t3;
			t3.RIGHT = n2r;
			if(i != 1){
				n1l = n1l.DOWN;
				n2l = n2l.DOWN;
				n1r = n1r.UP;
				n2r = n2r.UP;
			}
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		N = s1.nextInt();
		M = s1.nextInt();
		int Q = s1.nextInt();
		int grid[][] = new int[N][M];

		for (int i = 0; i < N; i++)
			grid[i] = s1.nextIntArray(M);

		Node arr[][] = new Node[N + 2][M + 2];
		north = new Node[M];
		south = new Node[M];
		east  = new Node[N];
		west  = new Node[N];

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				if (i >= 1 && i <= N && j >= 1 && j <= M)
					arr[i][j] = new Node(grid[i - 1][j - 1]);
				else
					arr[i][j] = new Node(-1);
			}
		}

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {

				if (i - 1 >= 0)
					arr[i][j].UP = arr[i - 1][j];
				if (j + 1 < M + 2)
					arr[i][j].RIGHT = arr[i][j + 1];
				if (i + 1 < N + 2)
					arr[i][j].DOWN = arr[i + 1][j];
				if (j - 1 >= 0)
					arr[i][j].LEFT = arr[i][j - 1];

				if (i == 0 && j > 0 && j <= M)
					north[j - 1] = arr[i][j];
				else if (i == N + 1 && j > 0 && j <= M)
					south[j - 1] = arr[i][j];
				else if (j == 0 && i > 0 && i <= N)
					west[i - 1] = arr[i][j];
				else if (j == M + 1 && i > 0 && i <= N)
					east[i - 1] = arr[i][j];
			}
		}

		Node root = arr[0][0];
		grid = null;
		arr = null;

		while (Q-- > 0)
			swap(s1.nextInt(), s1.nextInt(), s1.nextInt(), s1.nextInt(), s1.nextInt(), s1.nextInt());

		Node curr = root.RIGHT.DOWN;
		Node start = curr;
		for (int i = 0; i < N; i++) {
			start = start.DOWN;
			for (int j = 0; j < M; j++) {
				out.print(curr.key + " ");
				curr = curr.RIGHT;
			}
			curr = start;
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