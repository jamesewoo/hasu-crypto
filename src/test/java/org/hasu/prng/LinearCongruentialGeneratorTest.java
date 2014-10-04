package org.hasu.prng;

import org.junit.Test;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;
import static org.junit.Assert.assertEquals;

public class LinearCongruentialGeneratorTest {

    @Test
    public void testGetNext() throws Exception {
        Prng prng = new LinearCongruentialGenerator(BigInteger.ONE,
                valueOf(2),
                valueOf(3),
                valueOf(7));
        assertEquals(valueOf(5), prng.getNext());
        assertEquals(valueOf(6), prng.getNext());
        assertEquals(valueOf(1), prng.getNext());
        assertEquals(valueOf(5), prng.getNext());
    }
}