package com.example.lave.testinghce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.lave.testinghce.CTDataBlockImplementations.CTContractBlock;
import com.example.lave.testinghce.CTDataBlockImplementations.CTHeaderBlock;
import com.example.lave.testinghce.CTDataBlockImplementations.CTPayementEventBlock;
import com.example.lave.testinghce.CTProduct;

public class HomeScreen extends AppCompatActivity {

    private static Button button;
    private static TextView tv;
    private static CTProduct ctProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        button = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textview);

        //create test CTPRODUCT
        CTDataBlock head = new CTHeaderBlock(ByteUtilities.HexStringToByteArray("042DD677"),
                ByteUtilities.HexStringToByteArray("2A104980"), ByteUtilities.HexStringToByteArray("F34800F0"));
        head.writePage(3, ByteUtilities.HexStringToByteArray("C879FFFF"));
        CTDataBlock event1 = new CTPayementEventBlock(ByteUtilities.HexStringToByteArray("C0003004"),
                ByteUtilities.HexStringToByteArray("CDE1A9E0"), ByteUtilities.HexStringToByteArray("3AD06B70"),
                ByteUtilities.HexStringToByteArray("5A0C8B82"));
        CTDataBlock event2 = new CTPayementEventBlock(ByteUtilities.HexStringToByteArray("C8002024"),
                ByteUtilities.HexStringToByteArray("4DE1A9D0"), ByteUtilities.HexStringToByteArray("BF71043E"),
                ByteUtilities.HexStringToByteArray("DC58E2EC"));
        CTDataBlock contract = new CTContractBlock(ByteUtilities.HexStringToByteArray("875C8F38"),
                ByteUtilities.HexStringToByteArray("30BFE02E"), ByteUtilities.HexStringToByteArray("E3CF96B6"),
                ByteUtilities.HexStringToByteArray("983D1747"));
        ctProduct = new CTProduct(head, event1, event2, contract);
    }
    public static CTProduct getCtProduct()
    {
        return ctProduct;
    }

    public static void showTestID(String text, View view)
    {
        tv.setText(text);
    }

    public static void setButtonText(String text)
    {
        button.setText(text);
    }
}
