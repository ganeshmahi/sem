package com.example.mohanapriya.location2;

import android.app.Activity;
import android.content.Intent;
import android.javapapers.com.androidgeocodelocation.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by mohana priya on 25-03-2016.
 */
public class Passwordreset extends Activity {
    Button reset;
    EditText newpassword;
    EditText oldpassword;
    private EditText passwordTextView;
    private EditText password1TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordreset);
        passwordTextView = (EditText) findViewById(R.id.editText2);
        password1TextView = (EditText) findViewById(R.id.editText);

        reset = (Button) findViewById(R.id.btnSendSMS);
        oldpassword = (EditText) findViewById(R.id.editText);
        newpassword = (EditText) findViewById(R.id.editText2);

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                initLogin();

            }
        });

    }

    public void initLogin() {

        password1TextView.setError(null);
        passwordTextView.setError(null);

        String ph = password1TextView.getText().toString();
        String password = passwordTextView.getText().toString();

        boolean cancelLogin = false;
        View focusView = null;
        try {
         //   File myDir= new File(getFilesDir().getAbsolutePath());
            FileInputStream fileIn =openFileInput("register1.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            BufferedReader br = new BufferedReader((InputRead));
            String line = "";
            line = br.readLine();
            InputRead.close();


        if (ph.equals(line)) {
            try {
                FileOutputStream fileout=openFileOutput("register1.txt",MODE_WORLD_WRITEABLE);
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                outputWriter.write(password);
                outputWriter.close();
            } catch (Exception e) {

            }
        } else {
            password1TextView.setError(getString(R.string.incorrect_password));
            focusView = password1TextView;
            cancelLogin = true;
        }
            if (cancelLogin)
            {

                focusView.requestFocus();

            } else
            {
                Toast.makeText(getApplicationContext(),"Password has been reset",Toast.LENGTH_LONG).show();
                Intent nxt = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(nxt);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}