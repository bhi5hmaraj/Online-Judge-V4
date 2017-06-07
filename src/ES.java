import java.io.*;
import java.math.*;
import java.util.*;
/*
* Adapted from https://math.stackexchange.com/a/2053713
*/
class ES {
    static final BigInteger TWO = BigInteger.valueOf(2);
    static BigInteger sum(BigInteger n) {
        return ((n.multiply(n)).add(n)).divide(TWO);
    }
    static BigInteger S(double alpha , BigInteger n) {
        System.out.println("alpha " + alpha + " n " + n);
        if(n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;
        if(alpha >= 2) {
            double frac = alpha - Math.floor(alpha);
            int mult = (int) Math.round(alpha - (1.0 + frac));
            return S(alpha - mult, n).add((sum(n).multiply(BigInteger.valueOf(mult))));
        }
        else {
            double beta = alpha / (alpha - 1);
            BigInteger n_ = new BigDecimal(n).multiply(BigDecimal.valueOf(alpha - 1)).toBigInteger();
            BigInteger m = n.add(n_);
            return sum(m).subtract(S(beta, n_));
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println(S(2.7182818284590452353602874713527, new BigInteger(new Scanner(System.in).nextLine())));
    }
}