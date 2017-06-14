import java.util.*;
import java.io.*;
import java.math.BigDecimal;

public class NearestVector {

    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair> {
        BigDecimal d;
        int        index;

        Pair(double d, int index) {
            this.d = new BigDecimal(d);
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return d.compareTo(o.d);
        }

        @Override
        public String toString() {
            return d.toString();
        }
    }

    private static double theta(int x, int y) {
        double angle = Math.atan2(y, x);
        angle = (angle < 0) ? (2.0 * Math.PI) + angle : angle;
        return angle;
    }

    private static void solve(FastScanner s1, PrintWriter out) {

        int N = s1.nextInt();
        Pair arr[] = new Pair[N];
        for (int i = 0; i < N; i++)
            arr[i] = new Pair(theta(s1.nextInt(), s1.nextInt()), i + 1);

        Arrays.sort(arr);

        BigDecimal min = BigDecimal.valueOf(Double.MAX_VALUE);
        int i1 = -1, i2 = -1;
        for (int i = 1; i < N; i++) {
            BigDecimal diff = arr[i].d.subtract(arr[i - 1].d);
            if (diff.compareTo(min) < 0) {
                i1 = arr[i].index;
                i2 = arr[i - 1].index;
                min = diff;
            }
        }
        BigDecimal finalAngle = BigDecimal.valueOf((2.0 * Math.PI)).subtract(arr[N - 1].d).add(arr[0].d);
        if (finalAngle.compareTo(min) < 0) {
            i1 = arr[N - 1].index;
            i2 = arr[0].index;
        }

        out.print(i1 + " " + i2);
    }

    /************************ SOLUTION ENDS HERE ************************/

    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        solve(in, out);
        in.close();
        out.close();
    }

    static class FastScanner {
        BufferedReader  reader;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            st = null;
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        return null;
                    }
                    st = new StringTokenizer(line);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            return st.nextToken();
        }

        String nextLine() {
            String s = null;
            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        char nextChar() {
            return next().charAt(0);
        }

        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            int i = 0;
            while (i < n) {
                arr[i++] = nextInt();
            }
            return arr;
        }

        long[] nextLongArray(int n) {
            long[] arr = new long[n];
            int i = 0;
            while (i < n) {
                arr[i++] = nextLong();
            }
            return arr;
        }

        int[] nextIntArrayOneBased(int n) {
            int[] arr = new int[n + 1];
            int i = 1;
            while (i <= n) {
                arr[i++] = nextInt();
            }
            return arr;
        }

        long[] nextLongArrayOneBased(int n) {
            long[] arr = new long[n + 1];
            int i = 1;
            while (i <= n) {
                arr[i++] = nextLong();
            }
            return arr;
        }

        void close() {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /************************ TEMPLATE ENDS HERE ************************/
}