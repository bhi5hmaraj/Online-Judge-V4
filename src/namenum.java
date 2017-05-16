/*
ID: bs3861
LANG: JAVA
TASK: namenum
*/
import java.util.*;
import java.io.*;
public class  namenum{
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<String> dict ;
    static ArrayList<String> possible;
    static final char mapping[][] = { {} , {} , {'A','B','C'} , {'D','E','F'} , {'G','H','I'} , 
            {'J','K','L'} , {'M','N','O'} , {'P','R','S'} , {'T','U','V'} , {'W','X','Y'}
    };
    
    static void rec(int idx , String code , String curr) {
        if(idx == code.length()) {
            if(Collections.binarySearch(dict, curr) >= 0)
                possible.add(curr);
        }
        else {
            for(char ch : mapping[code.charAt(idx) - '0'])
                rec(idx + 1, code, curr + ch);
        }
    }
    
    private static void solve() throws FileNotFoundException , IOException {

        try(BufferedReader br = new BufferedReader(new FileReader("dict.txt"))) {
            dict = new ArrayList<>();
            possible = new ArrayList<>();
            String line = null;
            while((line = br.readLine()) != null)
                dict.add(line);
            
            rec(0, nextLine(), "");
            
            if(possible.isEmpty())
                println("NONE");
            else
                possible.stream().forEach(s -> println(s));
        }
    }

    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    static String file = "namenum";
    static final boolean OJ = false;
    
    public static void main(String[] args) throws IOException , FileNotFoundException {
        if(!OJ) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        }
        else {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file + ".in")));
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")), false);
        }
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