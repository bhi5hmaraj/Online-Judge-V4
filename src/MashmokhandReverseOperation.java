import java.util.*;
import java.io.*;
public class MashmokhandReverseOperation {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int aux[];
    static long fill(int depth , int L , int R , int arr[] , long inv[]) {
        if(L < R) {
            int mid = (L + R) >> 1;
            long left = fill(depth + 1, L, mid, arr, inv);
            long right = fill(depth + 1, mid + 1, R, arr, inv);
            System.arraycopy(arr, L, aux, L, R - L + 1);
            long cross = 0;
            System.out.println("L " + L + " R " + R + " arr " + Arrays.toString(Arrays.copyOfRange(arr, L, R + 1)));
            for(int ptrL = L , ptrR = mid + 1 , ptr = L; ptrL <= mid || ptrR <= R; ) {
                // System.out.println("ptrL " + ptrL + " ptrR " + ptrR);
                if(ptrL > mid)
                    arr[ptr++] = aux[ptrR++];
                else if(ptrR > R)
                    arr[ptr++] = aux[ptrL++];
                else if(aux[ptrL] <= aux[ptrR]) 
                    arr[ptr++] = aux[ptrL++];
                else if(aux[ptrR] < aux[ptrL]) {
                    arr[ptr++] = aux[ptrR++];
                    cross += mid - ptrL + 1;
                }
            }
            System.out.println("inv " + (left + right + cross));
            inv[depth] += left + right + cross;
            return left + right + cross;
        }
        
        return 0;
    }
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = 1 << n;
        int a[] = nextIntArray(m);
        int b[] = new int[m];
        for(int i = 0; i < m; i++)
            b[i] = a[m - i - 1];
        
        long inv[][] = new long[2][n];
        aux = new int[m];
        fill(0, 0, m - 1, a, inv[0]);
        // fill(0, 0, m - 1, b, inv[1]);
        println(Arrays.toString(inv[0]));
        /*
        int q = nextInt();
        while(q-->0) {
            
            
            
        }*/
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