package com.example.lave.testinghce.CTDataBlockImplementations;

import com.example.lave.testinghce.CTDataBlock;

/**
 * Created by lave on 6/23/2016.
 *
 * This class represents the header of a CT
 * The UID consists of seven bytes (firstPage[0-2] and secondPage[0-3])
 * 
 */
public class CTHeaderBlock extends CTDataBlock
{
    private final byte[] firstPage; //UID0, UID1, UID2, BCC0
    private final byte[] secondPage; //UID3, UID4, UID5, UID6
    private byte[] thirdPage; //BCC1, Internal, L1 , L2
    private byte[] fourthPage; //OTP

    public CTHeaderBlock(byte[] p1, byte[] p2, byte[] p3)
    {
        if(p1.length == PAGE_SIZE && p2.length == PAGE_SIZE &&p3.length == PAGE_SIZE)
        {
            firstPage = p1;
            secondPage = p2;
            thirdPage = p3;
        }
        else
        {
            firstPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            secondPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            thirdPage = new byte[]{0x00, 0x00, 0x00, 0x00};
        }
        fourthPage = new byte[]{0x00, 0x00, 0x00, 0x00};
    }

    /**
     *
     * @param page is requested pageNumber of this block (firstPage is 0)
     * @return byte[4] which belongs to requested page 0-3 other requests return byte[1]{0x00}
     */
    @Override
    public byte[] getPage(int page)
    {
        if(page == 0)
            return firstPage;
        else if(page == 1)
            return secondPage;
        else if(page == 2)
            return thirdPage;
        else if(page == 3)
            return fourthPage;
        else
            return new byte[]{0x00};
    }

    /**
     * Only fourth and part of third page are writeable
     * Writing to fourth and third page is done OR-bitwise
     *
     * @param page is the pageNumber from this block
     * @param newPage is new byte[4] which writes page
     *
     */
    @Override
    public void writePage(int page, byte[] newPage) {
        if (page == 3 && newPage.length == PAGE_SIZE) {
            for (int i = 0; i < PAGE_SIZE; i++)
            {
                fourthPage[i] = (byte) (newPage[i] | fourthPage[i]);
            }
        }
        //only byte 2 and byte 3 can be written
        else if (page == 2 && newPage.length == PAGE_SIZE) {
            for (int i = 2; i < PAGE_SIZE; i++)
            {
                thirdPage[i] = (byte) (thirdPage[i] | newPage[i]);
            }
        }
    }
}
