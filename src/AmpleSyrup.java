import java.util.*;
import java.io.*;
public class AmpleSyrup {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Pancake {
        int idx;
        long R , H;
        Pancake(int idx ,long rr , long hh) {
            this.idx = idx;
            R = rr;
            H = hh;
        }
    }
    
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
            int N = nextInt();
            int K = nextInt();
            Pancake cakes[] = new Pancake[N];
            for(int i=0;i<N;i++)
                cakes[i] = new Pancake(i , nextLong(), nextLong());
            
            double maxArea = 0;
            for(int i=0;i<N;i++) {
                
                final int wtf = i;
                ArrayList<Pancake> others = new ArrayList<>();
                double surfArea = (2.0 * Math.PI * cakes[i].R * cakes[i].H) + (Math.PI * cakes[i].R * cakes[i].R);
                Arrays.stream(cakes).
                filter(cake -> cake.idx != wtf && cake.R <= cakes[wtf].R).
                forEach(cake -> others.add(cake));
               
                if(others.size() >= K - 1) {
                    Collections.sort(others, (c1 , c2) -> Long.compare(c2.R * c2.H , c1.R * c1.H));
                    for(int j = 0;j < K - 1;j++)
                        surfArea += 2.0 * Math.PI * others.get(j).R * others.get(j).H;
                    
                    maxArea = Math.max(maxArea,surfArea);
                }
            }
            println(String.format("Case #%d: %.8f", tc , maxArea));
        }
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        //reader = new BufferedReader(new InputStreamReader(System.in));
        //writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        reader = new BufferedReader(new FileReader("A-largeCake.in"));
        writer  = new PrintWriter("out.txt");
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