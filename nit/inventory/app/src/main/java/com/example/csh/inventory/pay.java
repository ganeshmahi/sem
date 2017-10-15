package com.example.csh.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class pay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        // Get the Intent that started this activity and extract the string
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        // Capture the layout's TextView and set the string as its text
        int result = Integer.parseInt(message);
        result=result*12;
        String message1="Rs. "+result;
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(message1);
    }
}
