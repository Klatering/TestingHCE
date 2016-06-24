package com.example.lave.testinghce;

/**
 * Created by lave on 6/23/2016.
 */
public abstract class CTDataBlock {

    protected static final int PAGE_SIZE = 4;
    public abstract byte[] getPage(int page);
    public abstract void writePage(int page, byte[] newPage);
}
