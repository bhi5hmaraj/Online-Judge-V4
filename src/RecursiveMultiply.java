import java.util.*;
import java.io.*;
public class RecursiveMultiply
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static List<Integer> add(List<Integer> a1 , List<Integer> a2) { // In reverse order
		List<Integer> ans = new ArrayList<>();
		int i = 0 , j = 0;
		int carry = 0 , sum;
		while(i < a1.size() || j < a2.size()) {
			sum = ((i < a1.size()) ? a1.get(i++) : 0) + ((j < a2.size()) ? a2.get(j++) : 0) + carry;
			ans.add(sum % 10);
			carry = sum / 10;
		}
		if(carry != 0)
			ans.add(carry);
		
		return ans;
	}
	static List<Integer> shift(List<Integer> arl , int n) {
		for(int i=0;i<n;i++)
			arl.add(0, 0);
		
		return arl;
	}
	
	/*static void pad(List<Integer> a1 , List<Integer> a2) {
		int max = Math.max(a1.size(),a2.size());
		for(int i=0;i<max-a1.size();i++)
			a1.add(0);
		for(int i=0;i<max-a2.size();i++)
			a2.add(0);
	}
	*/
	static List<Integer> baseMultiply(List<Integer> arl , int a) {
		List<Integer> ans = new ArrayList<>();
		int sum = 0 , carry = 0;
		for(int digit : arl) {
			sum = (a * digit) + carry;
			ans.add(sum % 10);
			carry = sum / 10;
		}
		if(carry != 0)
			ans.add(carry);
		
		return ans;
	}
	/*static List<Integer> subList(List<Integer> arl , int L , int R) {
		List<Integer> ans = new List<>();
		for(int i=L;i<R;i++)
			ans.add(arl.get(i));
	}*/
	static List<Integer> multiply(List<Integer> a1 , List<Integer> a2) {
		
		// System.out.println("a1 " + a1);
		// System.out.println("a2 " + a2);
		
		int N1 = a1.size();
		int N2 = a2.size();
		if(N1 == 1)
			return baseMultiply(a2, a1.get(0));
		else if(N2 == 1)
			return baseMultiply(a1, a2.get(0));
		else {
			int h1 = N1 / 2;
			int h2 = N2 / 2;
			List<Integer> b = a1.subList(0, h1);
			List<Integer> a = a1.subList(h1, N1);
			List<Integer> d = a2.subList(0, h2);
			List<Integer> c = a2.subList(h2, N2);
			return 
					add(add(shift(multiply(a, c), h1 + h2), shift(multiply(a, d), h1)), 
						add(shift(multiply(b, c), h2), multiply(b, d)));
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String str1 = s1.nextLine();
		String str2 = s1.nextLine();
		
		ArrayList<Integer> a1 = new ArrayList<>();
		ArrayList<Integer> a2 = new ArrayList<>();
		
		for(int i=str1.length()-1;i >=0;i--)
			a1.add(str1.charAt(i) - '0');
		
		for(int i=str2.length()-1;i >=0;i--)
			a2.add(str2.charAt(i) - '0');
		
		List<Integer> ans = multiply(a1, a2);
		if(ans.get(ans.size() - 1) == 0) {
			ans.clear();
			ans.add(0);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = ans.size() - 1;i >= 0;i--)
			sb.append(ans.get(i));
		
		out.println(sb);
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}