package com.example.mohanapriya.location2;
import android.app.Activity;
import android.content.Intent;
import android.javapapers.com.androidgeocodelocation.R;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  {

  private EditText passwordTextView;
    private EditText phTextView;
public double longitude;
    public double  latitude;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;

    com.example.mohanapriya.location2.AppLocationService appLocationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        passwordTextView = (EditText) findViewById(R.id.editText2);
        phTextView=(EditText)findViewById(R.id.editText);
        appLocationService = new AppLocationService(MainActivity.this);
        Location gpsLocation = appLocationService
                 . getLocation(LocationManager.GPS_PROVIDER);
            if (gpsLocation != null) {
                 latitude = gpsLocation.getLatitude();
                 longitude = gpsLocation.getLongitude();
           String result = "Latitude: " + gpsLocation.getLatitude() +
                   " Longitude: " + gpsLocation.getLongitude();
            } else {
                Toast.makeText(getApplicationContext(),"Switch on the GPS",Toast.LENGTH_LONG).show();
                   }

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editText);
        txtMessage = (EditText) findViewById(R.id.editText2);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                initLogin();

            } });
        TextView reg = (TextView) findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent nxt = new Intent(getApplicationContext(), Register.class);
                startActivity(nxt);
            }

        });
        TextView reg1 = (TextView) findViewById(R.id.reg1);
        reg1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent nxt = new Intent(getApplicationContext(), Passwordreset.class);
                startActivity(nxt);
            }

        });
    }
    public void initLogin() {

        phTextView.setError(null);
        passwordTextView.setError(null);

        String ph = phTextView.getText().toString();
        String password= passwordTextView.getText().toString();

        boolean cancelLogin = false;
        View focusView = null;

        if (!TextUtils.isEmpty(ph) && !isphValid(ph)) {
            phTextView.setError(getString(R.string.invalid_password));
            focusView = phTextView;
            cancelLogin = true;
        }
        if (TextUtils.isEmpty(password)) {
            passwordTextView.setError(getString(R.string.field_required));
            focusView = passwordTextView;
            cancelLogin = true;
        }

        if (cancelLogin)
        {

                focusView.requestFocus();

        } else
        {
            sendSMSMessage();

        }
    }

    private boolean isphValid(String ph) {

        return ph.length()>9 ;
    }




    protected void sendSMSMessage()
    {
        Log.i("Send SMS", "");
        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.getText().toString();

        try
        {

            SmsManager smsManager =SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null, "$" + "\n"+ message + "\n" + "a"+latitude + "\n" + "a"+longitude,null,null);
            Toast.makeText(getApplicationContext(),"SMS sent.",Toast.LENGTH_LONG).show();


        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"SMS faild, please try again.",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}