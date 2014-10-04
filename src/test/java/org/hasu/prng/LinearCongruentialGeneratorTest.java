package org.hasu.prng;

import org.junit.Test;

import static java.math.BigInteger.valueOf;
import static org.junit.Assert.assertEquals;

public class LinearCongruentialGeneratorTest {

    @Test
    public void testGetNext() throws Exception {
        Prng prng = new LinearCongruentialGenerator(1, 2, 3, 7);
        assertEquals(valueOf(5), prng.getNext());
        assertEquals(valueOf(6), prng.getNext());
        assertEquals(valueOf(1), prng.getNext());
        assertEquals(valueOf(5), prng.getNext());
    }
}