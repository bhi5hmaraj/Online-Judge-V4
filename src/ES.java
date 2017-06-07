import java.math.*;
import java.util.*;
/*
* Adapted from https://math.stackexchange.com/a/2053713
*/
class ES {
    static final BigDecimal TWO_ = new BigDecimal("2");
    static final MathContext c = new MathContext(150);
    static BigDecimal sum(BigDecimal n) {
        return ((n.multiply(n , c)).add(n , c)).divide(TWO_ , c);
    }
    static BigDecimal S(BigDecimal alpha , BigDecimal n) {
        System.out.println(alpha + " " + n);
        if(n.equals(BigDecimal.ZERO))
            return BigDecimal.ZERO;
        if(alpha.compareTo(TWO_) >= 0) {
            BigDecimal frac = alpha.remainder(BigDecimal.ONE , c);
            BigDecimal mult = alpha.subtract(frac.add(BigDecimal.ONE , c) , c);
            System.out.println("mult " + mult);
            return S(alpha.subtract(mult , c), n).add((sum(n).multiply(mult , c)) , c);
        }
        else {
            BigDecimal cache = alpha.subtract(BigDecimal.ONE , c);
            BigDecimal beta = alpha.divide(cache , c);
            BigDecimal n_ = n.multiply(cache , c).round();
            BigDecimal m = n.add(n_ , c);
            return sum(m).subtract(S(beta, n_) , c);
        }
    }
    public static void main(String[] args) {
        BigDecimal E = new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956");
        System.out.println(S(E, BigDecimal.TEN.pow(1)).toBigInteger());
    }
}