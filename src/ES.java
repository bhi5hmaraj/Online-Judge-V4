import java.io.*;
import java.math.*;
import java.util.*;
/*
* Adapted from https://math.stackexchange.com/a/2053713
*/
class ES {
    static final BigInteger TWO = BigInteger.valueOf(2);
    static final BigDecimal TWO_ = new BigDecimal("2.0");
    static int rec = 0;
    static BigInteger sum(BigInteger n) {
        return ((n.multiply(n)).add(n)).divide(TWO);
    }
    static BigInteger S(BigDecimal alpha , BigInteger n) {
         System.out.println("alpha " + alpha + " n " + n);
        rec++;
        if(n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;
        if(alpha.compareTo(TWO_) >= 0) {
            BigDecimal frac = alpha.remainder(BigDecimal.ONE);
            int mult = alpha.subtract(BigDecimal.ONE).intValue();
            return S(BigDecimal.ONE.add(frac), n).add((sum(n).multiply(BigInteger.valueOf(mult))));
        }
        else {
            BigDecimal cache = alpha.subtract(BigDecimal.ONE);
//            System.out.println(cache);
            BigDecimal beta = alpha.divide(cache ,200 , BigDecimal.ROUND_FLOOR);
            BigInteger n_ = new BigDecimal(n).multiply(cache).toBigInteger();
            BigInteger m = n.add(n_);
            return sum(m).subtract(S(beta, n_));
        }
    }
    public static void main(String[] args) throws IOException {
        // System.out.println(S(Math.E, new BigInteger(new Scanner(System.in).nextLine())));
        try {
            // System.out.println();
            BigDecimal E = new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956");
            BigInteger ans = S(E, BigInteger.valueOf(10).pow(100));
            System.out.println(ans);
            System.out.println("Depth " + rec); 
        }
        catch(Error SOE) {
            System.out.println("Stack Overflow !!! \n" + 
                                "Exiting...");
        }
    }
}
//