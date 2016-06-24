package com.example.lave.testinghce.CTDataBlockImplementations;

import com.example.lave.testinghce.CTDataBlock;

/**
 * Created by lave on 6/24/2016.
 */
public class CTContractBlock extends CTDataBlock
{
    //All pages are encrypted
    private final byte[] firstPage;
    private final byte[] secondPage;
    private final byte[] thirdPage;
    private final byte[] fourthPage;

    public CTContractBlock(byte[] p1, byte[] p2, byte[] p3, byte[] p4)
    {
        if(p1.length == PAGE_SIZE && p2.length == PAGE_SIZE &&p3.length == PAGE_SIZE && p4.length == PAGE_SIZE)
        {
            firstPage = p1;
            secondPage = p2;
            thirdPage = p3;
            fourthPage = p4;
        }
        else //This makes the CT product invalid
        {
            firstPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            secondPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            thirdPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            fourthPage = new byte[]{0x00, 0x00, 0x00, 0x00};
        }

    }

    /**
     *
     * @return requested byte[] page
     */
    @Override
    public byte[] getPage(int page) {
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
     * ContractBlock can't be written
     * @param page
     * @param newPage
     */
    @Override
    public void writePage(int page, byte[] newPage) {
        //do Nothing
    }
}
