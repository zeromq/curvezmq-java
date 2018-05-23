package org.zeromq.codec;

import org.junit.Assert;
import org.junit.Test;
import org.zeromq.codec.Z85Test;

import java.util.Arrays;
import java.util.Random;

public class Z85Test
{

    @Test
    public void testZ85EncodingEmpty()
    {
        byte[] raw = new byte[] {};
        String actual = Z85.Z85Encoder(raw);
        Assert.assertEquals("", actual);
    }

    @Test
    public void testZ85DecodingEmpty()
    {
        byte[] expected = new byte[] {};
        byte[] actual = Z85.Z85Decoder("");
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testZ85Encoding()
    {
        byte[] raw = new byte[] { (byte) 0x86, 0x4F, (byte) 0xD2, 0x6F, (byte) 0xB5, 0x59, (byte) 0xF7, 0x5B };
        String actual = Z85.Z85Encoder(raw);
        Assert.assertEquals("HelloWorld", actual);
    }

    @Test
    public void testZ85Decoding()
    {
        byte[] expected = new byte[] { (byte) 0x86, 0x4F, (byte) 0xD2, 0x6F, (byte) 0xB5, 0x59, (byte) 0xF7, 0x5B };
        byte[] actual = Z85.Z85Decoder("HelloWorld");
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testZ85Random()
    {
        Random random = new Random();
        for (int i = 0 ; i < 100 ; i++) {
            byte[] expected = new byte[i];
            random.nextBytes(expected);
            String actual = Z85.Z85Encoder(expected);
            if (expected.length % 4 != 0) {
                Assert.assertNull("unpadded input expected to encode as null", actual);
            } else {
                byte[] decoded = Z85.Z85Decoder(actual);
                Assert.assertArrayEquals("failed with  " + Arrays.toString(expected), expected, decoded);
            }
        }
    }
}
