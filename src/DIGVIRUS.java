/************************         ௳            ************************/
import java.util.*;
import java.io.*;
class DIGVIRUS {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static boolean check(char str[]) {
        for(int i=1;i<str.length;i++)
            if(str[i] != str[0])
                return false;
        return true;
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            char str[] = nextLine().toCharArray();
            if(check(str)) {
                println(0);
                continue;
            }
            int N = str.length;
            int freq[] = new int[10];
            int time = 0;
            
            for(char ch : str)
                freq[ch - '0']++;

            if(freq[0] + freq[1] == N) {
                int zeroCnt = 0;
                int start = 0;
                int end = N - 1;
                while(start <= end && str[start] == '0')
                    start++;
                time = start;
                while(start <= end && str[end] == '0')
                    end--;
                time = Math.max(time,N - end - 1);
                while(start <= end) {
                    if(str[start] == '0')
                        zeroCnt++;
                    else {
                        time = Math.max(time,(zeroCnt / 2) + (zeroCnt % 2));
                        zeroCnt = 0;
                    }
                    start++;
                }
                
            }
            else {
                char temp[] = new char[N];
                while(!check(str)) {
                    time++;
                    for(int i=0;i<N;i++) {
                        char max = 0;
                        for(int j = Math.max(0,i - 9);j < Math.min(N,i + 10);j++)
                            if(str[j] - str[i] >= Math.abs(j - i))
                                max = str[j] > max ? str[j] : max;
                        temp[i] = max;
                    }
                    System.arraycopy(temp, 0, str, 0, N);
                }
            }

            println(time);
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