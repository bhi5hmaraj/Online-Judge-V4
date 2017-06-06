import java.io.*;
import java.math.*;
import java.util.*;
/*
 * Adapted from https://math.stackexchange.com/a/2053713
 */
public class ES {
    static final BigInteger TWO = BigInteger.valueOf(2);
    static BigInteger sum(BigInteger n) {
        return n.multiply(n).add(n).divide(TWO);
    }
    static BigInteger S(double alpha , BigInteger n) {
        if(n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;
        if(alpha >= 2)
            return S(alpha - 1, n).add(sum(n));
        else {
            double beta = alpha / (alpha - 1);
            BigInteger n_ = new BigDecimal(n).multiply(BigDecimal.valueOf(alpha - 1)).toBigInteger();
            BigInteger m = n.add(n_);
            return sum(m).subtract(S(beta, n_));
        }
    }
    public static void main(String[] args) throws IOException {
//        BigInteger N = new BigInteger(new Scanner(System.in).nextLine());
        BigInteger N = BigInteger.TEN.pow(200);
        long start = System.nanoTime();
        System.out.println(S(Math.E, N));
        System.out.println("Time " + (System.nanoTime() - start)/1e9);
    }
}
