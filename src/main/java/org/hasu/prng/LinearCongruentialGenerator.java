package org.hasu.prng;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

/**
 * Bad random number generator
 */
public class LinearCongruentialGenerator implements Prng {

    private final BigInteger a;
    private final BigInteger b;
    private final BigInteger modulus;
    private BigInteger s;

    /**
     * Creates a linear congruential PRNG with integer parameters.
     *
     * @param seed    the initial value s0
     * @param a       first secret parameter for the PRNG
     * @param b       second secret parameter for the PRNG
     * @param modulus modulus of the generator
     */
    public LinearCongruentialGenerator(long seed, long a, long b, long modulus) {
        this(valueOf(seed), valueOf(a), valueOf(b), valueOf(modulus));
    }

    /**
     * Creates a linear congruential PRNG with BigInteger parameters.
     *
     * @param seed    the initial value s0
     * @param a       first secret parameter for the PRNG
     * @param b       second secret parameter for the PRNG
     * @param modulus modulus of the generator
     */
    public LinearCongruentialGenerator(BigInteger seed, BigInteger a, BigInteger b, BigInteger modulus) {
        this.s = seed;
        this.a = a;
        this.b = b;
        this.modulus = modulus;
    }

    /**
     * Returns a "random" number between 0 and modulus - 1.
     *
     * @return the next random number
     */
    @Override
    public BigInteger getNext() {
        s = a.multiply(s)
                .add(b)
                .mod(modulus);
        return s;
    }

    /**
     * Returns the size of the modulus - 1 in bits.
     *
     * @return the size of the modulus - 1
     */
    @Override
    public int getOutputLen() {
        return modulus.subtract(BigInteger.ONE).bitLength();
    }
}
