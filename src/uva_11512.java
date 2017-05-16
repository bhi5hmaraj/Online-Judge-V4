import java.util.*;
import java.io.*;
public class uva_11512
{


	/************************ SOLUTION STARTS HERE ***********************/

	static String text;
	static int len;
	static int size[];
	static class Pair {
		int index , start , length;
		Pair(int index , int start,int length){
			this.index = index;
			this.start = start;
			this.length = length;
		}

	}
	static ArrayList<TreeMap<Character,Pair>> tree;

	static void add(int idx_text,int idx_tree){

		Pair p = tree.get(idx_tree).get(text.charAt(idx_text));
		if(p == null){
			p = new Pair(tree.size(), idx_text, len - idx_text);
			tree.add(new TreeMap<>());
			tree.get(idx_tree).put(text.charAt(idx_text), p);
		}
		else{
			for(int i = idx_text , j = p.start;i < len && j < p.start + p.length;i++,j++){
				if(text.charAt(i) != text.charAt(j)){
					Pair p1 = new Pair(tree.size(), j, p.start + p.length - j);
					tree.add(null);
					tree.set(p1.index, tree.get(p.index));
					tree.set(p.index, new TreeMap<>());
					Pair p2 = new Pair(tree.size(), i, len - i);
					p.length = j - p.start;
					tree.add(new TreeMap<>());
					tree.get(p.index).put(text.charAt(j), p1);
					tree.get(p.index).put(text.charAt(i), p2);
					return;
				}
			}
			add(idx_text + p.length, p.index);
		}
	}
	
	static int computeSize(int u){
		for(Map.Entry<Character, Pair> e : tree.get(u).entrySet())
			size[u] += computeSize(e.getValue().index);

		return size[u] == 0 ? size[u] = 1 : size[u];
	}
	
	static int idx_end;
	static ArrayDeque<String> stack;
	static int max_len;
	static int freq;
	static void findAnswer(Pair curr , int curr_length){
		if(curr.length > 0 && size[curr.index] > 1){
			int sub_len = curr_length + curr.length;
			if(sub_len > max_len){
				max_len = sub_len;
				idx_end = curr.index;
				freq = size[curr.index];
			}
		}
		
		for(Map.Entry<Character, Pair> e : tree.get(curr.index).entrySet())
			findAnswer(e.getValue(), curr_length + curr.length);
	}
	
	static boolean getAnswer(Pair curr){
		if(curr.index == idx_end){
			stack.push(text.substring(curr.start , curr.start + curr.length));
			return true;
		}
		else{
			boolean flag = false;
			for(Map.Entry<Character, Pair> e : tree.get(curr.index).entrySet())
				flag |= getAnswer(e.getValue());
			
			if(flag)
				stack.push(text.substring(curr.start, curr.start + curr.length));
			
			return flag;
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){


		int t = s1.nextInt();
		while(t-->0){
			text = s1.nextLine().concat("$");
			len = text.length();
			tree = new  ArrayList<>();
			tree.add(new TreeMap<>());
			max_len = -1;
			for(int i = len - 1;i >= 0;i--)
				add(i, 0);	
			
			size = new int[tree.size() + 1];
			computeSize(0);
			
			findAnswer(new Pair(0, 0, 0), 0);
			if(max_len < 0)
				out.println("No repetitions found!");
			else{
				stack = new ArrayDeque<>();
				getAnswer(new Pair(0, 0, 0));
				for(String s:stack)
					out.print(s);
				out.println(" " + freq);
			}
		}

	}

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new uva_11512().run();
			}
		}, "Increase Stack", 1L << 25).start();
	}

	void run(){	
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

