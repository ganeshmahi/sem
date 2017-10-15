package com.example.mohanapriya.location2;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class SMSBroadcastReceiver extends  BroadcastReceiver implements LocationListener {

    String strPhone;
    String strMessage;
    String strGPS;
String lat,lon;
    Double gps1,gps2;
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE =  2000;


    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override

    public void onReceive(Context context, Intent intent) {



        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;
        strPhone = "";
        strMessage = "";
        String[] smsMessages=new String[10];
        strGPS="";
        try {

            FileInputStream fileIn = context.openFileInput("register1.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            BufferedReader br = new BufferedReader((InputRead));
            String line = "";
            line = br.readLine();
            InputRead.close();
        if (bundle != null) {
            // ---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                strPhone+=msgs[i].getOriginatingAddress();
                strMessage+=msgs[i].getMessageBody().toString();
              //  String smsBody = smsMessage.getMessageBody().toString();
                 smsMessages = strMessage.split("\n");

            }

            if (strMessage.contains("$") && strMessage.contains(line)) {

                AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
               LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                Location location;
                try{
                if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ){
                    final Criteria criteria = new Criteria();
                    criteria.setCostAllowed(false);
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);
                    final String p = lm.getBestProvider(criteria, true);
                    lm.requestLocationUpdates(p,
                            MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
                    if (lm != null) {
                        location = lm.getLastKnownLocation((LocationManager.GPS_PROVIDER));
                        gps1=location.getLatitude();
                        gps2=location.getLongitude();
                        lat="a"+location.getLatitude();
                        lon="a"+location.getLongitude();}
                        // FileOutputStream fileout=openFileOutput(f,false);
                    //     db.addContact(new Contact(latitude, longitude));
                }
                }
                catch (SecurityException e)
                {

                }

                       // return location;


if(smsMessages[2].equals(lat) && smsMessages[3].equals(lon))
{
   // strGPS = String.valueOf(gps[0]) + " " + String.valueOf(gps[1]);
    sendSMS(strPhone,"location same");
}
                else
{
                strGPS = String.valueOf(gps1) + "\n" + String.valueOf(gps2);
                sendSMS(strPhone, strGPS); }
            }   }
        } catch (Exception e) {
            e.printStackTrace();
             }
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

}