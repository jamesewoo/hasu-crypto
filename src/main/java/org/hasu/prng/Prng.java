package org.hasu.prng;

import java.math.BigInteger;

/**
 * Pseudo-random number generator (PRNG)
 */
public interface Prng {
    /**
     * Returns pseudo-random number.
     *
     * @return the random number
     */
    BigInteger getNext();
}
