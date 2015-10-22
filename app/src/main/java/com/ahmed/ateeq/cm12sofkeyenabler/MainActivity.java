package com.ahmed.ateeq.cm12sofkeyenabler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends Activity {

    Button ebutton;
    Button dbutton;
    Button cbutton;

    public void CheckSU(){


        boolean suAvailable = Shell.SU.available();
        if (suAvailable)
        {
            Toast.makeText(getApplicationContext(), "Phone Rooted! ", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Phone not Rooted", Toast.LENGTH_SHORT).show();
        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ebutton = (Button) findViewById(R.id.ebutton);
        dbutton = (Button) findViewById(R.id.dbutton);
        cbutton = (Button) findViewById(R.id.cbutton);

        ebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Shell.SU.run("settings put secure dev_force_show_navbar 1");


            }
        });
        dbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Shell.SU.run("settings put secure dev_force_show_navbar 0");


            }
        });

        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CheckSU();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
