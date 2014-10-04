package org.hasu.cipher;

import org.hasu.prng.Prng;

/**
 * Stream cipher based on a PRNG.
 */
public class PrngStreamCipher implements StreamCipher {
    private final Prng prng;
    private final byte[] keyStream;
    private int ksOff;

    /**
     * Creates a new stream cipher with a PRNG.
     *
     * @param prng the PRNG to generate the key stream
     */
    public PrngStreamCipher(Prng prng) {
        this.prng = prng;
        int bufLen = (prng.getOutputLen() + Byte.SIZE - 1) / Byte.SIZE;
        this.keyStream = new byte[bufLen];
        this.ksOff = 0;
        computeKeyStream();
    }

    @Override
    public void process(byte[] in, int inOff, int len, byte[] out, int outOff) {
        for (int i = inOff; i < inOff + len; ++i) {
            if (ksOff >= keyStream.length) {
                computeKeyStream();
                ksOff = 0;
            }
            out[outOff + i] = (byte) (keyStream[ksOff++] ^ in[i]);
        }
    }

    private void computeKeyStream() {
        byte[] random = prng.getNext().toByteArray();
        System.arraycopy(random, random.length - keyStream.length, keyStream, 0, keyStream.length);
    }
}
