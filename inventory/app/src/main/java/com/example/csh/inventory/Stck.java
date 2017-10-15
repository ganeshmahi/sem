package com.example.csh.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Stck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stck);
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Stock Selected", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, pay.class);
        EditText editText = (EditText) findViewById(R.id.editText3);
        String message = editText.getText().toString();
        intent.putExtra("message", message);
        startActivity(intent);
    }
}
