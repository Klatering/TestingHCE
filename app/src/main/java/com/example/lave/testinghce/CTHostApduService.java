package com.example.lave.testinghce;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.widget.SimpleCursorTreeAdapter;

import java.util.Arrays;
import com.example.lave.testinghce.CTProduct;

/**
 * Created by lave on 6/21/2016.
 *
 * CTHostApduService processes apdu commands
 */
public class CTHostApduService extends HostApduService
{
    private static final byte[] OK_SW = ByteUtilities.HexStringToByteArray("9000");
    private static final byte[] Select_NOK_SW = ByteUtilities.HexStringToByteArray("0000");
    private static final byte[] WRONG_LENGTH_SW = ByteUtilities.HexStringToByteArray("6981");
    private final int MAX_UPDATE_BINARY_ACTIONS = 4;
    private static final int HEADER_INS_POS = 1;
    private static final int HEADER_CLA_POS = 0;
    private static final int HEADER_PARA1_POS = 2;
    private static final int HEADER_PARA2_POS = 3;
    private static final int HEADER_LENGTH_POS = 4;
    private static final int HEADER_DATA_POS = 5;



    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras)
    {
        HomeScreen.showTestID(ByteUtilities.ByteArrayToHexString(commandApdu), null);
        if(Arrays.equals(ApduCommandBuilder.buildSelectAidApdu(), commandApdu))
        {
            HomeScreen.setButtonText("NFC Connection established");
            return OK_SW;
        }
        else if(Arrays.equals(ApduCommandBuilder.buildGetUidApdu(), commandApdu))
        {
            return ByteUtilities.ConcatArrays(HomeScreen.getCtProduct().getUID(), OK_SW);
        }
        else if(commandApdu[HEADER_INS_POS] ==
                ByteUtilities.HexStringToByteArray(ApduCommandBuilder.getReadHeader())[HEADER_INS_POS])
        {
            String offSet = ByteUtilities.byteToHexString(commandApdu[HEADER_PARA2_POS]);
            if(Arrays.equals(ApduCommandBuilder.buildStandardReadApdu(offSet), commandApdu))
            {
                CTProduct product= HomeScreen.getCtProduct();
                return ByteUtilities.ConcatArrays(product.readPages(Integer.parseInt(offSet, 16)),
                        OK_SW);
            }
            else
                return WRONG_LENGTH_SW;
        }
        else if(commandApdu[HEADER_INS_POS] ==
                ByteUtilities.HexStringToByteArray(ApduCommandBuilder.getUpdateBinaryHeader())[HEADER_INS_POS])
        {
            int offSet = Integer.parseInt(ByteUtilities.byteToHexString(commandApdu[HEADER_PARA2_POS]),16);
            int dataLength = commandApdu[HEADER_LENGTH_POS] & 0xFF;
            if(dataLength%4==0)
            {
                for(int i = 0; i < dataLength/4; i++)
                {
                    byte[] writeData = new byte[CTProduct.getPAGE_SIZE()];
                    for(int j = 0; j < CTProduct.getPAGE_SIZE(); j++)
                    {
                        int commandPos = HEADER_DATA_POS+(i*4)+j;
                        writeData[j] = commandApdu[commandPos];
                    }
                    HomeScreen.getCtProduct().updatePage(offSet, writeData);
                    offSet++;
                }
                return OK_SW;
            }
            else
                return Select_NOK_SW;

        }
        else
        {
            return Select_NOK_SW;
        }
    }

    @Override
    public void onDeactivated(int reason) {
        HomeScreen.setButtonText("NFC Connection deactivated, reason: " + Integer.toString(reason));
    }
}
