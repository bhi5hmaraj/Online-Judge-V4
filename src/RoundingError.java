import java.util.*;
import java.io.*;
public class RoundingError {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        for(int tc = 1, T = nextInt(); tc <= T; tc++) {
            
            int N = nextInt();
            int L = nextInt();
            int rem = N;
            
            ArrayList<Double> arl = new ArrayList<>();
            int sum = 0;
            for(int i = 0; i < L; i++) {
                int c = nextInt();
                double per = c * 100.0 / N;
                sum += Math.round(Math.floor(per));
                if(per - Math.floor(per) < 0.5)
                    arl.add(0.5 - (per - Math.floor(per)));
                else
                    sum++;
                rem -= c;
            }
            
            
            Collections.sort(arl);
            for(double need : arl) {
                int req = (int) Math.round(Math.ceil(N * need / 100.0));
                if(rem < req) break;
                sum++;
                rem -= req;
            }
            
            double single = 100.0 / N;
            if(single - Math.floor(single) >= 0.5)
                sum += (Math.round(Math.floor(single)) + 1) * rem;
            else {
                double frac = single - Math.floor(single);
                int howmany = (int) Math.round(Math.ceil(a))
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