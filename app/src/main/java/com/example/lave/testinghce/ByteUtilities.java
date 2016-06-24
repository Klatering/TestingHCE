package com.example.lave.testinghce;

import java.util.Arrays;

/**
 * Created by lave on 6/23/2016.
 */
public class ByteUtilities {

    /**Utility method to convert hexadecimal string to byte string.
     *
     * @param s String containing hexadecimal characters to convert
     * @return Byte array generated from input
     * @throws java.lang.IllegalArgumentException if input length is incorrect or non-hexadecimal chars
     */
    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {

        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2]; // Allocate 1 byte per 2 hex characters
        for (int i = 0; i < len; i += 2) {
            //Checks for non-hexadecimal character
            boolean char1OK = false;
            boolean char2OK = false;
            for(int j = 0; j < hexArray.length; j++)
            {
                if(hexArray[j] == s.charAt(i))
                {
                    char1OK = true;
                }
                if(hexArray[j] == s.charAt(i+1))
                {
                    char2OK = true;
                }
            }
            if(!(char1OK && char2OK))
            {
                throw new IllegalArgumentException("Character not hexadecimal");
            }
            // Convert each character into a integer (base-16), then bit-shift into place
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    /**
     * Utility method to concatenate two byte arrays.
     * @param first First array
     * @param rest Any remaining arrays
     * @return Concatenated copy of input arrays
     */
    public static byte[] ConcatArrays(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
