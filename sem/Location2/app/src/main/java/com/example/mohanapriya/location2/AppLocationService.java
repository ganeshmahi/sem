package com.example.mohanapriya.location2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class AppLocationService extends Service implements LocationListener {

    public int onStartCommand(Intent intent,int flags,int startId)
    {
        getLocation(LocationManager.GPS_PROVIDER);

        return START_STICKY;
    }



    protected LocationManager locationManager;
    Location location;
    private Context context;
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE =  2000;

    public AppLocationService() {
        locationManager = (LocationManager) context
                .getSystemService(LOCATION_SERVICE);
    }
    public AppLocationService(Context context) {
        locationManager = (LocationManager) context
                .getSystemService(LOCATION_SERVICE);
    }

    public Location getLocation(String provider) {

        // if(db==null)
        //      db= DatabaseHandler.getWritableDatabase();
        try {
            if (locationManager.isProviderEnabled(provider)) {
                final Criteria criteria = new Criteria();
                criteria.setCostAllowed(false);
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                final String p = locationManager.getBestProvider(criteria, true);
                locationManager.requestLocationUpdates(p,
                        MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(provider);
                    // FileOutputStream fileout=openFileOutput(f,false);


                    return location;
                }
                //  FileWriter fw=new FileWriter("mytextfile1.txt",false);


                //      latitude="a"+location.getLatitude();
                //      longitude="a"+location.getLongitude();

                //     db.addContact(new Contact(latitude, longitude));
            }
        }
        catch (SecurityException e)
        {

        }
        return null;

    }


    @Override
    public void onLocationChanged(Location location) {



    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}