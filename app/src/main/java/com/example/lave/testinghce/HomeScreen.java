package com.example.lave.testinghce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.lave.testinghce.CTProduct;

public class HomeScreen extends AppCompatActivity {

    private Button button;
    private TextView tv;
    private CTProduct ctProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

       button = (Button) findViewById(R.id.button);
       tv = (TextView) findViewById(R.id.textview);
       ctProduct = new CTProduct();
    }

    public void showTestID(View view)
    {
        tv.setText(ctProduct.getTestIDString());
    }
}
