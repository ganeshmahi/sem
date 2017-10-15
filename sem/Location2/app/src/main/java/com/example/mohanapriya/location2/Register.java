package com.example.mohanapriya.location2;

import android.content.Context;
import android.content.Intent;
import android.javapapers.com.androidgeocodelocation.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by mohana priya on 18-03-2016.
 */
public class Register extends AppCompatActivity {
    public final static String EXTRA_MESSAGE="com.example.mohanapriya.MESSAGE";

    Button sendBtn;
  //  EditText txtphoneNo;
    EditText txtMessage;
    Context c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        sendBtn = (Button) findViewById(R.id.btnSendSMS);
     //   txtphoneNo = (EditText) findViewById(R.id.editText);
        txtMessage = (EditText) findViewById(R.id.editText);
sendBtn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            try {
                String msg=txtMessage.getText().toString();
               // File myDir= new File(getFilesDir().getAbsolutePath());
                FileOutputStream fileout=openFileOutput("register1.txt",MODE_PRIVATE);
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                outputWriter.write(msg);
                outputWriter.close();

                //display file saved message
                Toast.makeText(getBaseContext(), "you have registered successfully!",
                        Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }


            Intent nxt = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(nxt);
        }
    });

}

    }
