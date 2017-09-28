import java.util.*;
import java.io.*;
public class SeregaandFun {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int Q = nextInt();

        final int BLOCK_SIZE = (int) (Math.sqrt(n) + 1); 
        LinkedList<Integer> blocks[] = new LinkedList[n / BLOCK_SIZE + 1];
        int freq[][] = new int[n / BLOCK_SIZE + 1][n + 1];
        for(int i = 0; i < blocks.length; i++) 
            blocks[i] = new LinkedList<>();
        
        for(int i = 0; i < n; i++) {
            blocks[i / BLOCK_SIZE].add(arr[i]);
            freq[i / BLOCK_SIZE][arr[i]]++;
        }
        /*
        System.out.println("Block sz = " + BLOCK_SIZE);
        for(int i = 0; i < blocks.length; i++) {
            println("i " + i);
            println(blocks[i]);
            println(freq[i]);
        }
        */
        int ans = 0;
        while(Q-->0) {
            int type = nextInt();
            if(type == 1) {
                int l = ((nextInt() + ans - 1) % n);
                int r = ((nextInt() + ans - 1) % n);
                int L = Math.min(l , r);
                int R = Math.max(l , r);
                boolean add = false;
                for(int i = L / BLOCK_SIZE; i < R / BLOCK_SIZE; i++) {
                    add = true;
                    int toRem = blocks[i].pollLast();
                    freq[i][toRem]--;
                    blocks[i + 1].addFirst(toRem);
                    freq[i + 1][toRem]++;
                }
                
                int toRem = blocks[R / BLOCK_SIZE].remove((R % BLOCK_SIZE) + (add ? 1 : 0));
                freq[R / BLOCK_SIZE][toRem]--;
                blocks[L / BLOCK_SIZE].add(L % BLOCK_SIZE, toRem);
                freq[L / BLOCK_SIZE][toRem]++;
            } else {
                int l = ((nextInt() + ans - 1) % n);
                int r = ((nextInt() + ans - 1) % n);
                int L = Math.min(l , r);
                int R = Math.max(l , r);
                int K = ((nextInt() + ans - 1) % n) + 1;
                int cnt = 0;
                for(ListIterator<Integer> it =  blocks[L / BLOCK_SIZE].listIterator(L % BLOCK_SIZE); L <= R && L % BLOCK_SIZE != 0; L++)
                    cnt += it.next().intValue() == K ? 1 : 0;
                for(ListIterator<Integer> it = blocks[R / BLOCK_SIZE].listIterator((R % BLOCK_SIZE) + 1); R >= L && (R + BLOCK_SIZE) % BLOCK_SIZE != BLOCK_SIZE - 1; R--)
                    cnt += it.previous().intValue() == K ? 1 : 0;
                
                for(; L < R; L += BLOCK_SIZE)
                    cnt += freq[L / BLOCK_SIZE][K];
                
                println(ans = cnt);
            }
        }
            
    }
    
    
    static void solve2() {
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int Q = nextInt();

        final int BLOCK_SIZE = (int) (Math.sqrt(n) + 1); 
        
        int blocks[][] = new int[n / BLOCK_SIZE + 1][BLOCK_SIZE];
        int offset[] = new int[n / BLOCK_SIZE + 1];
        int aux[] = new int[BLOCK_SIZE];
        int freq[][] = new int[n / BLOCK_SIZE + 1][n + 1];
        
        for(int i = 0; i < n; i++) {
            blocks[i / BLOCK_SIZE][i % BLOCK_SIZE] = arr[i];
            freq[i / BLOCK_SIZE][arr[i]]++;
        }
        
        int ans = 0;
        while(Q-->0) {
            int type = nextInt();
            if(type == 1) {
                int l = ((nextInt() + ans - 1) % n);
                int r = ((nextInt() + ans - 1) % n);
                int L = Math.min(l , r);
                int R = Math.max(l , r);
                int exit = -1;
                
                if(offset[L / BLOCK_SIZE] != 0) {
                    for(int i = 0; i < BLOCK_SIZE; i++)
                        aux[i] = blocks[L / BLOCK_SIZE][(offset[L / BLOCK_SIZE] + i) % BLOCK_SIZE];
                    System.arraycopy(aux, 0, blocks[L / BLOCK_SIZE], 0, BLOCK_SIZE);
                    offset[L / BLOCK_SIZE] = 0;
                }
                
                if(L / BLOCK_SIZE == R / BLOCK_SIZE) {
                    int prev = blocks[L / BLOCK_SIZE][L % BLOCK_SIZE];
                    for(int i = L % BLOCK_SIZE + 1; i <= R % BLOCK_SIZE; i++) {
                        int t = blocks[L / BLOCK_SIZE][i];
                        blocks[L / BLOCK_SIZE][i] = prev;
                        prev = t;
                    }
                    blocks[L / BLOCK_SIZE][L % BLOCK_SIZE] = prev;
                }
                else {   // move L
                    int qu = L / BLOCK_SIZE;
                    int re = L % BLOCK_SIZE;
                    exit = blocks[qu][BLOCK_SIZE - 1];
                    
                    freq[qu][exit]--;
                    for(int i = BLOCK_SIZE - 1; i > re; i--)
                        blocks[qu][i] = blocks[qu][i - 1];
                    blocks[qu][re] = blocks[R / BLOCK_SIZE][(offset[R / BLOCK_SIZE] + R) % BLOCK_SIZE];
                    freq[qu][blocks[qu][re]]++;
                    for(int i = L / BLOCK_SIZE + 1; i < R / BLOCK_SIZE; i++) {
                        offset[i] = (offset[i] - 1 + BLOCK_SIZE) % BLOCK_SIZE;
                        int next = blocks[i][offset[i]];
                        blocks[i][offset[i]] = exit;
                        freq[i][exit]++;
                        exit = next;
                        freq[i][exit]--;
                        
                    }
                    

                    if(offset[R / BLOCK_SIZE] != 0) {
                        for(int i = 0; i < BLOCK_SIZE; i++)
                            aux[i] = blocks[R / BLOCK_SIZE][(offset[R / BLOCK_SIZE] + i) % BLOCK_SIZE];
                        System.arraycopy(aux, 0, blocks[R / BLOCK_SIZE], 0, BLOCK_SIZE);
                        offset[R / BLOCK_SIZE] = 0;
                    }
                    
                    freq[R / BLOCK_SIZE][exit]++;
                    int toRem = blocks[R / BLOCK_SIZE][R % BLOCK_SIZE];
                    freq[R / BLOCK_SIZE][toRem]--;
                    for(int i = R % BLOCK_SIZE; i > 0; i--)
                        blocks[R / BLOCK_SIZE][i] = blocks[R / BLOCK_SIZE][i - 1];
                    
                    blocks[R / BLOCK_SIZE][0] = exit;
                    
                }
            } else {
                int l = ((nextInt() + ans - 1) % n);
                int r = ((nextInt() + ans - 1) % n);
                int L = Math.min(l , r);
                int R = Math.max(l , r);
                int K = ((nextInt() + ans - 1) % n) + 1;
                int cnt = 0;
                for(int idx = L / BLOCK_SIZE; L <= R && L % BLOCK_SIZE != 0; L++)
                    cnt += blocks[idx][(offset[idx] + L) % BLOCK_SIZE] == K ? 1 : 0;
                for(int idx = R / BLOCK_SIZE; R >= L && (R + BLOCK_SIZE) % BLOCK_SIZE != BLOCK_SIZE - 1; R--)
                    cnt += blocks[idx][(offset[idx] + R) % BLOCK_SIZE] == K ? 1 : 0;
                for(; L < R; L += BLOCK_SIZE)
                    cnt += freq[L / BLOCK_SIZE][K];
                
                println(ans = cnt);
            }
        }
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve2();
        // LinkedList nearly passed !! TC 55 TLE
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