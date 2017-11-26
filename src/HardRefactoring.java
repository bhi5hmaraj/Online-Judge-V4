import java.util.*;
import java.io.*;
public class HardRefactoring  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int offset = 1 << 15;
        String line;
        int segment[] = new int[1 << 16 + 1];
        while((line = nextLine()) != null) {
            
            Arrays.fill(segment, 0);

            while(true) {
                String spl[] = line.split(" ");
                int lo = -(1 << 15);
                int hi = (1 << 15) - 1;
                if(line.contains(">=") && line.contains("<=")) {
                    lo = Integer.parseInt(spl[2]);
                    hi = Integer.parseInt(spl[6]);
                } else if(line.contains(">="))
                    lo = Integer.parseInt(spl[2]);
                else
                    hi = Integer.parseInt(spl[2]);
                
                lo += offset;
                hi += offset;
                if(lo <= hi) {
                    segment[lo]++;
                    segment[hi + 1]--;
                }
                if(!line.contains("||"))
                    break;
                line = nextLine();
            } 
            
            for(int i = 1; i < segment.length; i++)
                segment[i] += segment[i - 1];
            
            boolean all = true;
            for(int i = 0; i < (1 << 16); i++)
                all &= segment[i] > 0;
            
            if(Arrays.stream(segment).allMatch(v -> v == 0))
                println("false");
            else if(all)
                println("true");
            else {
                ArrayList<String> collect = new ArrayList<>();
                for(int i = 0; i < (1 << 16); ) {
                    if(segment[i] > 0) {
                        int start = i;
                        while(i < (1 << 16) && segment[i] > 0)
                            i++;
                        int end = i - 1;
                        if(start == 0)
                            collect.add("x <= " + (end - offset));
                        else if(end == (1 << 16) - 1)
                            collect.add("x >= " + (start - offset));
                        else
                            collect.add("x >= " + (start - offset) + " && x <= " + (end - offset));
                    }
                    else 
                        i++;
                }
                
                
                for(int i = 0; i < collect.size(); i++)
                    println(collect.get(i) + (i < collect.size() - 1 ? " ||" : ""));
            }
        }
        
        
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
//        reader = new BufferedReader(new FileReader("hard.in"));
//        writer = new PrintWriter("hard.out");
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