package com.example.csh.inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    // Array of strings...
    String[] mobileArray = {"Apple","Banana","Mango","Grapes", "Lichi","Tomato","Potato","Watermelon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = "Select the sock";
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                sendMessage(view);

            }
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, Stck.class);
        startActivity(intent);
    }


}

