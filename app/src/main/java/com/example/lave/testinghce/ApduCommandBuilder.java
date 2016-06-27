package com.example.lave.testinghce;

/**
 * Created by lave on 6/27/2016.
 */
public class ApduCommandBuilder
{
    private static final String AID = "FF4368657373";
    private static final String AID_LENGTH = "06";
    private static final String READ_HEADER = "00B0";
    private static final String SELECT_AID_HEADER = "00A40400";
    private static final String UPDATE_BINARY_HEADER = "00D6";
    private static final String GET_UID_HEADER = "00CA000007";
    private static final String UNUSED_PARAMETER = "00";

    public static byte[] buildSelectAidApdu()
    {
        //Select AID command format: [Class|Instruction|Parameter1|Parameter2|Length|Data]
        return ByteUtilities.HexStringToByteArray(SELECT_AID_HEADER + AID_LENGTH + AID);
    }

    public static byte[] buildReadApdu(String offSet, String readBytes)
    {
        //Read APDU Command format: [CLass|Instruction|Parameter 1|Parameter 2| Length]
        return ByteUtilities.HexStringToByteArray(READ_HEADER + UNUSED_PARAMETER + offSet + readBytes);
    }

    public static byte[] buildStandardReadApdu(String offSet)
    {
        return ByteUtilities.HexStringToByteArray(READ_HEADER + UNUSED_PARAMETER +
                offSet + "10");
    }

    public static byte[] buildUpdateAPDU(String offSet, String dataLength, String data)
    {
        //Update Binary APDU Command format: [CLass|Instruction|Parameter 1|Parameter 2| Length|Data]
        return ByteUtilities.HexStringToByteArray(UPDATE_BINARY_HEADER + UNUSED_PARAMETER + offSet + dataLength + data);
    }

    public static byte[] buildGetUidApdu()
    {
        //Read APDU Command format: [CLass|Instruction|Parameter 1|Parameter 2| Length]
        return ByteUtilities.HexStringToByteArray(GET_UID_HEADER);
    }

    public static String getUpdateBinaryHeader() {
        return UPDATE_BINARY_HEADER;
    }

    public static String getReadHeader() {
        return READ_HEADER;
    }
}
