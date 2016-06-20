package com.example.lave.testinghce;

/**
 * Created by lave on 6/20/2016.
 */
public class CTProduct {

    private byte[] testID = {0x0A, (byte)0xBC, (byte)0xDE, (byte)0xF1};

    public byte[] getTestID()
    {
        return testID;
    }

    public String getTestIDString()
    {
        String format = "0x%02X ";
        int i1 = testID[0] & 0xff;
        String s1 = String.format(format, i1);
        int i2 = testID[1] & 0xff;
        String s2 = String.format(format, i2);
        int i3 = testID[2] & 0xff;
        String s3 = String.format(format, i3);
        int i4 = testID[3] & 0xff;
        String s4 = String.format(format, i4);

        return s1 + s2 + s3 + s4;
    }

    public void writeID(byte[] newPage)
    {
        if(newPage.length == 4)
        {
            testID = newPage;
        }
    }
}
