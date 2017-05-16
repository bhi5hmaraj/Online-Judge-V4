import java.util.*;
import java.io.*;
public class VasyaandParty
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class DSU {
	private int parent[];
	private int size[];
	private int cnt;
	private int maxCnt[];
	private int knowledge[];
	DSU(int length, int arr[]) {
	    this.cnt  = length;
	    parent    = new int[length + 10];
	    size      = new int[length + 10];
	    maxCnt    = new int[length + 10];
	    knowledge = Arrays.copyOf(arr, arr.length);
	    Arrays.fill(size, 1);	
	    Arrays.fill(maxCnt, 1);
	    for (int i = 0; i < parent.length; i++)
		parent[i] = i;
	}

	int root(int p) {
	    return (parent[p] == p) ? p : (parent[p] = root(parent[p]));
	}

	int sizeOf(int p) {
	    return size[root(p)];
	}

	boolean connected(int u, int v) {
	    return root(u) == root(v);
	}

	int components() {
	    return cnt;
	}
	
	void update(int from , int to){
	    if(knowledge[from] > knowledge[to]){
		knowledge[to] = knowledge[from];
		maxCnt[to] = maxCnt[from];
	    }
	    else if(knowledge[from] == knowledge[to]){
		maxCnt[to] += maxCnt[from];
	    }
	}
	
	void union(int u, int v) {
	    if (!connected(u, v)) {
		cnt--;
		int rootU = root(u);
		int rootV = root(v);
		if (size[rootU] < size[rootV]) {
		    parent[rootU] = rootV;
		    size[rootV] += size[rootU];	
		    update(rootU, rootV);
		} else {
		    parent[rootV] = rootU;
		    size[rootU] += size[rootV];
		    update(rootV, rootU);
		}
	    }
	}
    }

    static final long MOD = (long)(1e9) + 7;
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int M = s1.nextInt();
	int arr[] = s1.nextIntArrayOneBased(N);
	DSU dsu = new DSU(N, arr);
	HashMap<Integer, Integer> map = new HashMap<>();
	while(M-->0)
	    dsu.union(s1.nextInt(), s1.nextInt());
	
	for(int i=1;i<=N;i++){
	    int root = dsu.root(i);
	    map.put(root, dsu.maxCnt[root]);
	}
	
	long ways = 1;
	for (Map.Entry<Integer,Integer> e : map.entrySet()) 
	    ways = ((ways % MOD) * (e.getValue().longValue() % MOD)) % MOD;
	
	out.println(ways);
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