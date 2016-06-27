package com.example.lave.testinghce;

/**
 * Created by lave on 6/20/2016.
 */
public class CTProduct {

    private static final int PAGE_SIZE = 4; //A page consists of 4 bytes
    private final int BLOCK_SIZE = 4; //A block consists of 4 pages
    private final int UID_BYTE_SIZE = 7; //An UID consists of 7 bytes
    private final int HEADER_OFFSET = 0;
    private final int EVENT1_OFFSET = 1;
    private final int EVENT2_OFFSET = 2;
    private final int CONTRACT_OFFSET =3;

    private CTDataBlock header;
    private CTDataBlock event1;
    private CTDataBlock event2;
    private CTDataBlock contract;

    public CTProduct(CTDataBlock h, CTDataBlock ev1, CTDataBlock ev2, CTDataBlock c)
    {
        header = h;
        event1 = ev1;
        event2 = ev2;
        contract = c;
    }

    /**
     * UID consists of 7 bytes
     * @return UID in byte[7] extracted from header
     */
    public byte[] getUID()
    {
        byte[] r = new byte[UID_BYTE_SIZE];
        for(int i = 0; i < 3; i++)
        {
            r[i] = header.getPage(0x00)[i];
        }
        for (int i = 3; i < 7; i++)
        {
            r[i] = header.getPage(0x01)[i-3];
        }
        return r;
    }

    /**
     *
     * @param offSet is pageNumber of first page to read
     * @return byte[16] counting from offset and up.
     *
     */
    public byte[] readPages(int offSet)
    {
        byte[] res = new byte[0];
        int blockOffSet;
        for(int i = 0; i < BLOCK_SIZE; i++)
        {
            blockOffSet = offSet/BLOCK_SIZE;
            if(blockOffSet == HEADER_OFFSET)
            {
                res = ByteUtilities.ConcatArrays(res, header.getPage(offSet%PAGE_SIZE));
            }
            else if(blockOffSet == EVENT1_OFFSET)
            {
                res = ByteUtilities.ConcatArrays(res, event1.getPage(offSet%PAGE_SIZE));
            }
            else if(blockOffSet == EVENT2_OFFSET)
            {
                res = ByteUtilities.ConcatArrays(res, event2.getPage(offSet%PAGE_SIZE));
            }
            else if(blockOffSet == CONTRACT_OFFSET)
            {
                res = ByteUtilities.ConcatArrays(res, contract.getPage(offSet%PAGE_SIZE));
            }
            //End of file reached. Resume reading at top of file;
            else if(blockOffSet > CONTRACT_OFFSET)
            {
                offSet = -1;
                i--;
            }
            offSet++;
        }
        return res;
    }

    public void updatePage(int offSet, byte[] newPage)
    {
        int blockOffSet = offSet/BLOCK_SIZE;
        if(newPage.length == 4)
        {
            if(blockOffSet == HEADER_OFFSET)
            {
                header.writePage(offSet%PAGE_SIZE, newPage);
            }
            else if(blockOffSet == EVENT1_OFFSET)
            {
                event1.writePage(offSet%PAGE_SIZE, newPage);
            }
            else if(blockOffSet == EVENT2_OFFSET)
            {
                event2.writePage(offSet%PAGE_SIZE, newPage);
            }
            else if(blockOffSet == CONTRACT_OFFSET)
            {
                contract.writePage(offSet%PAGE_SIZE, newPage);
            }
        }
    }

    public static int getPAGE_SIZE() {
        return PAGE_SIZE;
    }
}
