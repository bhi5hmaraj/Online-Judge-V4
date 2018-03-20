import java.util.*;
import java.io.*;
public class uva_12604  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int[] KMPPrefixFunction(char str[]) {
        int n = str.length;
        int prefix[] = new int[n];  // Stores the length of largest border for a prefix 
        for(int i = 1; i < n; i++) {
            int border;
            for(border = prefix[i - 1]; border > 0 && str[border] != str[i]; border = prefix[border - 1])
                ;
            prefix[i] = str[i] == str[border] ? border + 1: 0;
        }
        
        return prefix;
    }

    static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int tmp_curChar;
        private int tmp_numChars;

        public int read() {
            if (tmp_numChars == -1)
                throw new InputMismatchException();
            if (tmp_curChar >= tmp_numChars) {
                tmp_curChar = 0;
                try {
                    tmp_numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (tmp_numChars <= 0)
                    return -1;
            }
            return buf[tmp_curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }
    
    private static void solve() {
        
        FasterScanner scan = new FasterScanner();
        int T = scan.nextInt();
        while(T-->0) {
            char alph[] = scan.nextLine().toCharArray();
            String word = scan.nextLine();
            String encrypt = scan.nextLine();
            
            int inv[] = new int[128];
            for(int i = 0; i < alph.length; i++)
                inv[alph[i]] = i;
            
            ArrayList<Integer> possible = new ArrayList<>();
            for(int i = 0; i < alph.length; i++) {
                StringBuilder sb = new StringBuilder(word + "$");
                for(int j = 0; j < encrypt.length(); j++)
                    sb.append(alph[(inv[encrypt.charAt(j)] - i + alph.length) % alph.length]);
                
                int prefix[] = KMPPrefixFunction(sb.toString().toCharArray());
                
                int cnt = 0;
                for(int j = 0; j < encrypt.length(); j++)
                    cnt += (prefix[j + word.length() + 1] == word.length()) ? 1 : 0; 
                        
                if(cnt == 1)
                    possible.add(i);
                    
            }
            
            println(possible.isEmpty() ? "no solution" 
                   :possible.size() == 1 ? "unique: " + possible.get(0) 
                   :"ambiguous: " + String.join(" ", possible.stream().map(String::valueOf).toArray(String[]::new))
                    );
            
        }
        
        
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
//        reader.close();
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