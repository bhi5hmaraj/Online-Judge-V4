import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class StadiumandGames{
	
	
	
	/************************ SOLUTION STARTS HERE ************************/
	
	static BigInteger isPerfectSq(BigInteger n) {
		
		BigInteger lo = BigInteger.ONE;
		BigInteger hi = new BigInteger(n.toString());
		BigInteger sqrt = BigInteger.ZERO;
		
		while(lo.compareTo(hi) <= 0) {
			BigInteger mid = lo.add(hi);
			mid = mid.divide(BigInteger.valueOf(2));
			
			if(mid.multiply(mid).compareTo(n) > 0)
				hi = mid.subtract(BigInteger.ONE);
			else {
				lo = mid.add(BigInteger.ONE);
				sqrt = mid;
			}
		}
		
		return sqrt.multiply(sqrt).equals(n) ? sqrt : BigInteger.ZERO;
	}
	
	private static void solve() {
		
		
		long X = nextLong();
		
		TreeSet<BigInteger> ans = new TreeSet<>();
		
		for(int i = 0; i < 70; i++) {
			BigInteger term1 = BigInteger.valueOf(2).pow(i + 1).subtract(BigInteger.valueOf(3));
			BigInteger term2 = isPerfectSq(term1.multiply(term1).add(BigInteger.valueOf(8L * X)));

			BigInteger numer = term2.subtract(term1);
			BigInteger div[] = numer.divideAndRemainder(BigInteger.valueOf(2));
			
			if(!term2.equals(BigInteger.ZERO) && numer.compareTo(BigInteger.ZERO) >= 0 
					&& div[1].equals(BigInteger.ZERO)) {
				
				BigInteger m = div[0];
				BigInteger answer = m.multiply(BigInteger.valueOf(2).pow(i));
				
//				BigInteger T_ = m.multiply(BigInteger.valueOf(2).pow(i).subtract(BigInteger.ONE)).
//						add((m.multiply(m.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2))));
				
				
//				println("T " + T_ + " m " + m + " n  " + i);
				
				if(m.divideAndRemainder(BigInteger.valueOf(2))[1].equals(BigInteger.ONE) 
						/*&& T_.equals(BigInteger.valueOf(X))*/)
					ans.add(answer);
			}
		}
		
		if(ans.size() == 0)
			println(-1);
		else
			ans.stream().forEach(writer::println);
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE **********************/
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
		st     = null;
		solve();
		reader.close();
		writer.close();
	}
	
	static BufferedReader reader;
	static PrintWriter    writer;
	static StringTokenizer st;
	
	static String next()
	{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
	st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
	static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
	static int    nextInt()   {return Integer.parseInt(next());}
	static long   nextLong()  {return Long.parseLong(next());}     
	static double nextDouble(){return Double.parseDouble(next());}
	static char   nextChar()  {return next().charAt(0);}
	static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
	static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
	static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
	static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
	static void   print(Object o)  { writer.print(o);  }
	static void   println(Object o){ writer.println(o);}
	
	/************************ TEMPLATE ENDS HERE ************************/
	
}