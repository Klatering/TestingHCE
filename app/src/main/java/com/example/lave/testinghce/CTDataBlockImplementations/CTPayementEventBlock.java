package com.example.lave.testinghce.CTDataBlockImplementations;

import com.example.lave.testinghce.CTDataBlock;

/**
 * Created by lave on 6/24/2016.
 */
public class CTPayementEventBlock extends CTDataBlock
{
    private byte[] firstPage;
    private byte[] secondPage;
    private byte[] thirdPage;
    private byte[] fourthPage;

    public CTPayementEventBlock()
    {
        firstPage = new byte[]{0x00, 0x00, 0x00, 0x00};
        secondPage = new byte[]{0x00, 0x00, 0x00, 0x00};
        thirdPage = new byte[]{0x00, 0x00, 0x00, 0x00};
        fourthPage = new byte[]{0x00, 0x00, 0x00, 0x00};
    }

    public CTPayementEventBlock(byte[] p1, byte[] p2, byte[] p3, byte[] p4)
    {
        if(p1.length == PAGE_SIZE && p2.length == PAGE_SIZE &&p3.length == PAGE_SIZE && p4.length == PAGE_SIZE) {
            firstPage = p1;
            secondPage = p2;
            thirdPage = p3;
            fourthPage = p4;
        }
        else
        {
            firstPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            secondPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            thirdPage = new byte[]{0x00, 0x00, 0x00, 0x00};
            fourthPage = new byte[]{0x00, 0x00, 0x00, 0x00};
        }
    }
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
     *
     * @param page is modulo 4 of pageNumber
     * @param newPage
     */
    @Override
    public void writePage(int page, byte[] newPage)
    {
        if(newPage.length == PAGE_SIZE)
        {
            if (page == 0)
                firstPage = newPage;
            else if (page == 1)
                secondPage = newPage;
            else if (page == 2)
                thirdPage  = newPage;
            else if (page == 3)
                fourthPage = newPage;
        }
    }
}
