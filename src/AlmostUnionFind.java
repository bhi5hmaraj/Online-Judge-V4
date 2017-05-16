import java.util.*;
import java.io.*;
public class AlmostUnionFind
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static final int MAX = (int)(1.5e5);
    static class DSU {
	private int parent[];
	private int size[];
	private int cnt;
	private long sum[];		
	DSU(int length) {
	    this.cnt = length;
	    parent = new int[MAX];
	    size   = new int[MAX];
	    sum    = new long[MAX];
	    Arrays.fill(size, 1);
	    for (int i = 0; i < parent.length; i++){
		parent[i] = i;
		if(i <= length){
		    sum[i] = i;
		}
	    }
	}
	void printParent(){
	    int max= 10;
	    System.out.print("\nparent ");
	    for(int i=1;i<=max;i++){
		System.out.printf("%3s", parent[i]);
	    }
	    System.out.println();
	    System.out.printf("%7s", "i");
	    for(int i=1;i<=max;i++){
		System.out.printf("%3s", i);
	    }
	}
	int root(int p) {
	    return (parent[p] == p) ? p : (parent[p] = root(parent[p]));
	}

	int sizeOf(int p) {
	    return size[root(p)];
	}
	long sumOf(int p){
	    return sum[root(p)];
	}
	boolean connected(int u, int v) {
	    return root(u) == root(v);
	}

	int components() {
	    return cnt;
	}

	/*this is a block comment generated*/
	void union(int u, int v) {
	    if (!connected(u, v)) {
		cnt--;
		int rootU = root(u);
		int rootV = root(v);
		if (size[rootU] < size[rootV]) {
		    parent[rootU] = rootV;
		    size[rootV] += size[rootU];
		    sum[rootV] += sum[rootU];
		} else { 
		    parent[rootV] = rootU;
		    size[rootU] += size[rootV];
		    sum[rootU] += sum[rootV];
		}
	    }
	}	
    }

    private static void solve(FastScanner s1, PrintWriter out){

	String read = null;
	while((read = s1.nextLine()) != null){	    
	    String split[] = read.split(" ");
	    int N = Integer.parseInt(split[0]);
	    int Q = Integer.parseInt(split[1]);
	    int map[] = new int[N+1];
	    for(int i=1;i<=N;i++)map[i] = i;
	    int curr = N+2;
	    DSU dsu = new DSU(N);
	    while(Q-->0){
		char ch = s1.nextChar();
		switch(ch){
		case '1':		    
		    dsu.union(map[s1.nextInt()], map[s1.nextInt()]);
		    break;
		case '2':
		    int u = s1.nextInt();
		    int v = s1.nextInt();
		    int newU = map[u];
		    int newV = map[v];
		    if(!dsu.connected(newU, newV)){
			int root = dsu.root(newU);
			dsu.size[root]--;
			dsu.sum[root] -= u;
			map[u] = curr++;
			newU = map[u];
			dsu.sum[newU] = u;
			dsu.union(newU, newV);
		    }
		    break;
		case '3':
		    int p = map[s1.nextInt()];	
		    out.println(dsu.sizeOf(p) + " " + dsu.sumOf(p));
		    break;
		}
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