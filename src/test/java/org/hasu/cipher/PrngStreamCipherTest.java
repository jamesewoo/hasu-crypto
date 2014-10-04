package org.hasu.cipher;

import org.hasu.prng.LinearCongruentialGenerator;
import org.hasu.prng.Prng;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PrngStreamCipherTest {

    @Test
    public void testProcess_prngOutputHasLen1() throws Exception {
        LinearCongruentialGenerator prng = new LinearCongruentialGenerator(1, 2, 3, 7);
        assert getOutputLenBytes(prng) == 1;
        PrngStreamCipher cipher = new PrngStreamCipher(prng);
        byte[] message = {0, 1, 2, 3, (byte) 0xfc, (byte) 0xfd, (byte) 0xfe, (byte) 0xff};
        cipher.process(message, 0, message.length, message, 0);
        assertArrayEquals(new byte[]{5, 7, 3, 6, (byte) 0xfa, (byte) 0xfc, (byte) 0xfb, (byte) 0xf9}, message);
    }

    @Test
    public void testProcess_prngOutputHasLen4() throws Exception {
        LinearCongruentialGenerator prng = new LinearCongruentialGenerator(0x7fffffffL, 0xaaaaaaaaL, 0xbbbbbbbbL, 0xffffffffL);
        assert getOutputLenBytes(prng) == 4;
        PrngStreamCipher cipher = new PrngStreamCipher(prng);
        byte[] message = {0, 1, 2, 3, (byte) 0xfc, (byte) 0xfd, (byte) 0xfe, (byte) 0xff};
        cipher.process(message, 0, message.length, message, 0);
        assertArrayEquals(new byte[]{0x66, 0x67, 0x64, 0x65, 0x47, 0x46, 0x45, 0x44}, message);
    }

    private static int getOutputLenBytes(Prng prng) {
        return (prng.getOutputLen() + Byte.SIZE - 1) / Byte.SIZE;
    }
}