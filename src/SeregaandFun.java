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
        HashMap<Integer , Integer> freq[] = new HashMap[n / BLOCK_SIZE + 1];
        
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = new LinkedList<>();
            freq[i] = new HashMap<>();
        }
        for(int i = 0; i < n; i++) {
            blocks[i / BLOCK_SIZE].add(arr[i]);
            freq[i / BLOCK_SIZE].put(arr[i], freq[i / BLOCK_SIZE].getOrDefault(arr[i] , 0) + 1);
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
        double q1T = 0;
        double q2T = 0;
        while(Q-->0) {
            int type = nextInt();
            if(type == 1) {
                long start = System.nanoTime();
                int l = ((nextInt() + ans - 1) % n);
                int r = ((nextInt() + ans - 1) % n);
                int L = Math.min(l , r);
                int R = Math.max(l , r);
                boolean add = false;
                for(int i = L / BLOCK_SIZE; i < R / BLOCK_SIZE; i++) {
                    add = true;
                    int toRem = blocks[i].pollLast();
                    int f = freq[i].get(toRem);
                    
                    if(f == 1) freq[i].remove(toRem);
                    else freq[i].put(toRem, f - 1);
                    
                    blocks[i + 1].addFirst(toRem);
                    freq[i + 1].put(toRem, freq[i + 1].getOrDefault(toRem, 0) + 1);
                }
                
                int toRem = blocks[R / BLOCK_SIZE].remove((R % BLOCK_SIZE) + (add ? 1 : 0));
                int f = freq[R / BLOCK_SIZE].get(toRem);
                
                if(f == 1) freq[R / BLOCK_SIZE].remove(toRem);
                else freq[R / BLOCK_SIZE].put(toRem, f - 1);

                blocks[L / BLOCK_SIZE].add(L % BLOCK_SIZE, toRem);
                freq[L / BLOCK_SIZE].put(toRem, freq[L / BLOCK_SIZE].getOrDefault(toRem, 0) + 1);
                q1T += (System.nanoTime() - start) / 1e9;
            } else {
                long start = System.nanoTime();
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
                    cnt += freq[L / BLOCK_SIZE].getOrDefault(K, 0);
                
                q2T += (System.nanoTime() - start) / 1e9;
                println(ans = cnt);
            }
        }
        
        if(n == 100000)
            println("Q1T " + q1T + " q2T " + q2T);
            
    }
    
    
    static void solve2() {
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int Q = nextInt();

        final int BLOCK_SIZE = (int) (Math.sqrt(n) + 1); 
        
        int blocks[][] = new int[n / BLOCK_SIZE + 1][BLOCK_SIZE];
        int offset[] = new int[n / BLOCK_SIZE + 1];
        int aux[] = new int[BLOCK_SIZE];
        HashMap<Integer , Integer> freq[] = new HashMap[n / BLOCK_SIZE + 1];
        for(int i = 0; i < blocks.length; i++)
            freq[i] = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            blocks[i / BLOCK_SIZE][i % BLOCK_SIZE] = arr[i];
            freq[i / BLOCK_SIZE].put(arr[i], freq[i / BLOCK_SIZE].getOrDefault(arr[i], 0) + 1);
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
                    blocks[L / BLOCK_SIZE][L % BLOCK_SIZE] = aux[R % BLOCK_SIZE];
                    for(int i = L % BLOCK_SIZE + 1; i <= R % BLOCK_SIZE; i++)
                        blocks[L / BLOCK_SIZE][i] = aux[i - 1];
                }
                else {   // move L
                    int qu = L / BLOCK_SIZE;
                    int re = L % BLOCK_SIZE;
                    exit = blocks[qu][BLOCK_SIZE - 1];
                    
                    freq[qu].compute(exit, (k , v) -> v == 1 ? freq[qu].remove(k) : freq[qu].put(k, v - 1));
                    /*
                    int f = freq[qu].get(exit);
                    
                    if(f == 1) freq[qu].remove(exit);
                    else freq[qu].put(exit, f - 1);
                    */
                    for(int i = BLOCK_SIZE - 1; i > re; i--)
                        blocks[qu][i] = blocks[qu][i - 1];
                    blocks[qu][re] = blocks[R / BLOCK_SIZE][(offset[R / BLOCK_SIZE] + (R % BLOCK_SIZE)) % BLOCK_SIZE];
                    freq[qu].put(blocks[qu][re], freq[qu].getOrDefault(blocks[qu][re], 0) + 1);
                    
                    for(int i = L / BLOCK_SIZE + 1; i < R / BLOCK_SIZE; i++) {
                        offset[i] = (offset[i] - 1 + BLOCK_SIZE) % BLOCK_SIZE;
                        int next = blocks[i][offset[i]];
                        blocks[i][offset[i]] = exit;
                        freq[i].put(exit, freq[i].getOrDefault(exit, 0) + 1);
                        exit = next;
                        
                        freq[i].compute(exit, (k , v) -> v == 1 ? freq[qu].remove(k) : freq[qu].put(k , v - 1));
                        
                        /*
                        f = freq[i].get(exit);
                        
                        if(f == 1) freq[i].remove(exit);
                        else freq[i].put(exit, f - 1);
                        */
                    }
                    

                    if(offset[R / BLOCK_SIZE] != 0) {
                        for(int i = 0; i < BLOCK_SIZE; i++)
                            aux[i] = blocks[R / BLOCK_SIZE][(offset[R / BLOCK_SIZE] + i) % BLOCK_SIZE];
                        System.arraycopy(aux, 0, blocks[R / BLOCK_SIZE], 0, BLOCK_SIZE);
                        offset[R / BLOCK_SIZE] = 0;
                    }
                    
                    freq[R / BLOCK_SIZE].put(exit, freq[R / BLOCK_SIZE].getOrDefault(exit, 0) + 1);
                    
                    int toRem = blocks[R / BLOCK_SIZE][R % BLOCK_SIZE];
                    freq[R / BLOCK_SIZE].compute(toRem, (k , v) -> v == 1 ? freq[R / BLOCK_SIZE].remove(k) : 
                                                                            freq[R / BLOCK_SIZE].put(k , v - 1));
                    
                    for(int i = R % BLOCK_SIZE; i > 0; i--)
                        blocks[R / BLOCK_SIZE][i] = blocks[R / BLOCK_SIZE][i - 1];
                    
                    blocks[R / BLOCK_SIZE][0] = exit;
                    
                }
                
                for(int i = 0; i < blocks.length; i++) {
                    System.out.println("i " + i);
                    System.out.println("bl " + Arrays.toString(blocks[i]));
                    System.out.println("freq " + freq[i]);
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
        solve2();
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