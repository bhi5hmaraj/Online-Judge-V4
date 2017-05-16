import java.util.*;
import java.io.*;
class CHSQARR_quadTree
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Node
    {
	int  sum,max;
	Node child[];
	int  coord[];
	int  hMid,vMid;
	Node(){
	    child = new Node[4];
	    coord = new int [4];
	}
	Node(int key,int x,int y){
	    this();
	    sum   = key;
	    max   = key;
	    hMid = x;
	    vMid = y;
	    coord = new int[]{x,y,x,y};
	}

	boolean isEqual(int coord[]){
	    return Arrays.equals(this.coord, coord);
	}

	@Override
	public String toString() {
	    return String.format("sum = %d max = %d hMid = %d vMid = %d coord = "+Arrays.toString(coord), sum,max,hMid,vMid);
	}
    }

    static class Pair
    {
	int sum,max;
	Pair(int sum,int max){
	    this.sum = sum;
	    this.max = max;
	}
	void combine(Pair that){
	    if(that!=null){
		sum += that.sum;
		max = Math.max(max,that.max);
	    }
	}
    }

    static class QuadTree
    {
	Node root;
	int R,C,arr[][];

	private void printTree(Node root)
	{
	    if(root != null)
	    {
		for(Node n:root.child)
		    printTree(n);

		System.out.println(root);
	    }
	}

	private boolean isValid(int coord[])
	{
	    return isValid(coord[0], coord[1]) && isValid(coord[2], coord[3]) && coord[0] <= coord[2] && coord[1] <= coord[3];
	}
	private boolean isValid(int i,int j)
	{
	    return i >= 0 && i < R && j >= 0 && j < C;
	}
	QuadTree(int R,int C,int arr[][])
	{
	    this.R = R;
	    this.C = C;
	    this.arr = arr;
	    this.root = build(new int[]{0,0,R-1,C-1});
	}



	Pair query(int coord[],Node root){

	    if(isValid(coord))
	    {
		//System.out.println("query root ="+root+" coord "+Arrays.toString(coord));
		if(root.isEqual(coord))
		    return new Pair(root.sum, root.max);
		else if(coord[1] > root.vMid && coord[2] <= root.hMid)
		    return query(coord, root.child[0]);
		else if(coord[3] <= root.vMid && coord[2] <= root.hMid)
		    return query(coord, root.child[1]);
		else if(coord[0] > root.hMid && coord[3] <= root.vMid)
		    return query(coord, root.child[2]);
		else if(coord[0] > root.hMid && coord[1] > root.vMid)
		    return query(coord, root.child[3]);
		else {
		    Pair p = new Pair(0, 0);
		    p.combine(query(new int[]{coord[0],Math.max(coord[1],root.vMid+1),Math.min(root.hMid,coord[2]),coord[3]}  ,root.child[0]));
		    p.combine(query(new int[]{coord[0],coord[1],Math.min(root.hMid,coord[2]),Math.min(root.vMid,coord[3])}    ,root.child[1]));
		    p.combine(query(new int[]{Math.max(root.hMid+1,coord[0]),coord[1],coord[2],Math.min(root.vMid,coord[3])}  ,root.child[2]));
		    p.combine(query(new int[]{Math.max(root.hMid+1,coord[0]),Math.max(root.vMid+1,coord[1]),coord[2],coord[3]},root.child[3]));
		    return p;
		}
	    }
	    else
		return null;

	}



	Node build(int coord[])
	{
	    if(isValid(coord))
	    {
		if(coord[0] == coord[2] && coord[1] == coord[3])
		    return new Node(arr[coord[0]][coord[1]], coord[0], coord[1]);
		else
		{
		    Node root = new Node();
		    root.coord = coord;
		    root.hMid = (coord[0] + coord[2]) / 2;
		    root.vMid = (coord[1] + coord[3]) / 2;
		    root.child[0] = build(new int[]{coord[0],root.vMid+1,root.hMid,coord[3]});
		    root.child[1] = build(new int[]{coord[0],coord[1],root.hMid,root.vMid});
		    root.child[2] = build(new int[]{root.hMid+1,coord[1],coord[2],root.vMid});
		    root.child[3] = build(new int[]{root.hMid+1,root.vMid+1,coord[2],coord[3]});
		    for(Node n : root.child){
			if(n != null){
			    root.sum += n.sum;
			    root.max = Math.max(root.max,n.max);
			}
		    }
		    return root;
		}
	    }
	    else
		return null;

	}

	int time(int... coord)
	{
	    //System.out.println(Arrays.toString(coord));
	    Pair p = query(coord, root);
	    //System.out.println("sum "+p.sum + " max "+p.max + " total " + tot);
	    return ((coord[2]-coord[0]+1) * (coord[3]-coord[1]+1) * p.max) - p.sum;
	}


    }




    private static void solve(FastScanner s1, PrintWriter out){

	int R = s1.nextInt();
	int C = s1.nextInt();
	int arr[][] = new int[R][];
	for(int i=0;i<R;i++)
	    arr[i] = s1.nextIntArray(C);

	QuadTree qtree = new QuadTree(R, C, arr);
	//qtree.printTree(qtree.root);
	int Q = s1.nextInt();
	while(Q-->0)
	{
	    int a = s1.nextInt();
	    int b = s1.nextInt();
	    int min = Integer.MAX_VALUE;
	    for (int i = 0; i <= R - a; i++)
		for (int j = 0; j <= C - b; j++)
		    min = Math.min(min, qtree.time(i, j, i + a - 1, j + b - 1));
	    
	    out.println(min);
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