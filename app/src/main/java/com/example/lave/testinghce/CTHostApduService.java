package com.example.lave.testinghce;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import com.example.lave.testinghce.ByteHexStringUtilities;
import com.example.lave.testinghce.CTProduct;

import java.util.Arrays;

/**
 * Created by lave on 6/21/2016.
 *
 * CTHostApduService processes apdu commands
 */
public class CTHostApduService extends HostApduService
{
    private static final byte[] SELECT_OK_SW = ByteHexStringUtilities.HexStringToByteArray("9000");
    private static final byte[] Select_NOK_SW = ByteHexStringUtilities.HexStringToByteArray("6A88");
    private CTProduct product = new CTProduct();


    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras)
    {
        HomeScreen.setButtonText("received apdu");
        HomeScreen.showTestID(null);
        return SELECT_OK_SW;
    }

    @Override
    public void onDeactivated(int reason) {

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
