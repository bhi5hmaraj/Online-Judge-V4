import java.util.*;
import java.io.*;
public class MashmokhandReverseOperation {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int aux[];
    static long rem[];
    static long fill(int depth , int L , int R , int arr[] , long inv[]) {
        if(L < R) {
            int mid = (L + R) >> 1;
            long left = fill(depth + 1, L, mid, arr, inv);
            long right = fill(depth + 1, mid + 1, R, arr, inv);
            System.arraycopy(arr, L, aux, L, R - L + 1);
            long cross = 0;
            // System.out.println("L " + L + " R " + R + " arr " + Arrays.toString(Arrays.copyOfRange(arr, L, R + 1)));
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
            // System.out.println("inv " + (left + right + cross));
            long cnt = 0;
            for(int i = L; i <= R; ) {
                int val = arr[i];
                while(i <= R && arr[i] == val) {
                    i++;
                    cnt++;
                }
                rem[depth] += nC2(cnt);
                cnt = 0;
            }
            inv[depth] += left + right + cross;
            return left + right + cross;
        }
        
        return 0;
    }
    
    static long nC2(long n) {
        return (n * (n - 1)) >> 1;
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
        rem = new long[n];
        fill(0, 0, m - 1, a, inv[0]);
        fill(0, 0, m - 1, b, inv[1]);
        for(int i = 0; i < n; i++)
            rem[i] /= 2;
        /*
        println("inv 0 " + Arrays.toString(inv[0]));
        println("inv 1 " + Arrays.toString(inv[1]));
        println("rem " + Arrays.toString(rem));
        println(Arrays.toString(a));
        */
        int q = nextInt();
        long curr[] = Arrays.copyOf(inv[0], n);
        int turn = 0;
        
        while(q-->0) {
            int l = nextInt();
            if(l > 0) {
                l = n - l;
                int next = turn ^ 1;
                for(int i = l + 1; i < n; i++)
                    curr[i] = inv[next][i];
                
                long old = curr[l];
                curr[l] = ((1L << l) * nC2(1L << (n - l))) - rem[l] - curr[l];
                
                for(int i = l; i > 0; i--) {
                    long temp = curr[i - 1];
                    curr[i - 1] += curr[i] - old;
                    old = temp;
                }

                turn = next;
            }
            //println(Arrays.toString(curr));
            println(curr[0]);
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