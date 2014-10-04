package org.hasu.cipher;

/**
 * Basic stream cipher
 */
public interface StreamCipher {
    /**
     * Processes len number of bytes.
     *
     * @param in     input buffer
     * @param inOff  input offset
     * @param len    number of bytes to process
     * @param out    output buffer
     * @param outOff output offset
     */
    void process(byte[] in, int inOff, int len, byte[] out, int outOff);
}
