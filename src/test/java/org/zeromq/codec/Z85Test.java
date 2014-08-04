package org.zeromq.codec;

import org.junit.Assert;
import org.junit.Test;
import org.zeromq.codec.Z85Test;

public class Z85Test
{

    @Test
    public void testZ85Encoding()
    {
        byte[] raw = new byte[] { (byte) 0x86, 0x4F, (byte) 0xD2, 0x6F, (byte) 0xB5, 0x59, (byte) 0xF7, 0x5B };
        String actual = Z85.Z85Encoder(raw);
        Assert.assertEquals(actual, "HelloWorld");
    }

    @Test
    public void testZ85Decoding()
    {
        byte[] expected = new byte[] { (byte) 0x86, 0x4F, (byte) 0xD2, 0x6F, (byte) 0xB5, 0x59, (byte) 0xF7, 0x5B };
        byte[] actual = Z85.Z85Decoder("HelloWorld");
        Assert.assertArrayEquals(expected, actual);
    }
}
