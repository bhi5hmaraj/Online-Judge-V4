import java.util.*;
import java.io.*;
public class Nephrengivesariddle {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        String base = "What are you doing at the end of the world? Are you busy? Will you save us?";
        String rep1 =  "What are you doing while sending \"";   // 34
        String rep2 =  "\"? Are you busy? Will you send \"";
        String rep3 =  "\"?";
        long lengths[] = new long[60];
        lengths[0] = base.length();
        int sz = 1;
        while(lengths[sz - 1] <= (long) 1e18) {
            lengths[sz] = 2L * lengths[sz - 1] + rep1.length() + rep2.length() + rep3.length();;
            sz++;
        }
        
//        println("sz " + sz + " len " + Arrays.toString(lengths));
        
        int Q = nextInt();
        while(Q-->0) {
            int n = nextInt();
            long k = nextLong();
            while(n >= sz && k > rep1.length()) {
                k -= rep1.length();
                n--;
            }
//            println("n " + n + " k " + k);
            if(n >= sz && k <= rep1.length())
                print(rep1.charAt((int)(k - 1)));
            else if (n < sz && k > lengths[n])
                print('.');
            else {
                while (true) {
                    if(n == 0) {
                        print(base.charAt((int)(k - 1)));
                        break;
                    }
                    else if(k <= rep1.length()) {
                        print(rep1.charAt((int)(k - 1)));
                        break;
                    }
                    else if(k <= rep1.length() + lengths[n - 1]) {
                        k -= rep1.length();
                        n--;
                    }
                    else if(k <= rep1.length() + lengths[n - 1] + rep2.length()) {
                        print(rep2.charAt((int)(k - rep1.length() - lengths[n - 1] - 1)));
                        break;
                    }
                    else if (k <= rep1.length() + 2L * lengths[n - 1] + rep2.length()) {
                        k -= rep1.length() + lengths[n - 1] + rep2.length();
                        n--;
                    }
                    else {
                        print(rep3.charAt((int)(k - (rep1.length() + 2L * lengths[n - 1] + rep2.length()) - 1)));    
                        break;
                    }
                }
            }
            
        }
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