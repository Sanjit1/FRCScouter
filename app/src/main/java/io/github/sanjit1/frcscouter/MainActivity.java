package io.github.sanjit1.frcscouter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // request permission, for internet access, read and write storage if it has not been already given
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);


                File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
                createDir(scout);
                scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
                createDir(scout);

        File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
        if(!toCheck.exists()) {
           saveDefault();


        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.about:
                Intent myIntent = new Intent(MainActivity.this,
                        About.class);
                startActivity(myIntent);
                break;

            case R.id.settings:
                break;

            case R.id.github:
                String url = "https://github.com/Sanjit1/FRCScouter";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.githubPage:
                String url1 = "https://sanjit1.github.io/FRCScouter";
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(url1));
                startActivity(i1);
                break;

        }
        return true;
    }


    public void onClick(View button){

        if (button==findViewById(R.id.templates)){
            Intent myIntent = new Intent(MainActivity.this,
                    templates.class);
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(MainActivity.this,
                    search.class);
            startActivity(myIntent);
        }
    }

    public void createDir(File scout){
        if (!scout.exists() && !scout.isDirectory()) {
            // create empty directory
            if (scout.mkdirs()) {
                Log.i("CreateDir", "App dir created");
            }
        } else {
            Log.i("CreateDir", "App dir already exists");
        }
    }

    public void saveDefault(){
        try
        {
            File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");

            File defaultLayout = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"/ScouterAppData/ActivityData/Deep Space.🚀🤖");
            FileWriter game = new FileWriter(defaultLayout);
            game.write("Deep Space");game.write(System.lineSeparator());
            game.write("Text");game.write(System.lineSeparator());
            game.write("Pre Game");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Cargo Pre Game");game.write(System.lineSeparator());
            game.write("Spinner");game.write(System.lineSeparator());
            game.write("Starting Position");game.write(System.lineSeparator());
            game.write("Level 1 ");game.write(System.lineSeparator());
            game.write("Level 2");game.write(System.lineSeparator());
            game.write("endSpinner🤖🚀🤖🚀");game.write(System.lineSeparator());
            game.write("Text");game.write(System.lineSeparator());
            game.write("Sandstorm");game.write(System.lineSeparator());
            game.write("Check");game.write(System.lineSeparator());
            game.write("Crossed Hab Line");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Hatch Rocket Lv1");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Hatch Rocket Lv2 & 3");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Hatch Ship");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Cargo Rocket Lv1");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Cargo Rocket Lv2 & 3");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Cargo Ship");game.write(System.lineSeparator());
            game.write("Text");game.write(System.lineSeparator());
            game.write("Tele-Op");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Hatch Rocket Lv1");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Hatch Rocket Lv2 & 3");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Hatch Ship");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Cargo Rocket Lv1");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Cargo Rocket Lv2 & 3");game.write(System.lineSeparator());
            game.write("Counter");game.write(System.lineSeparator());
            game.write("Cargo Ship");game.write(System.lineSeparator());
            game.write("Text");game.write(System.lineSeparator());
            game.write("End Game");game.write(System.lineSeparator());
            game.write("Spinner");game.write(System.lineSeparator());
            game.write("HAB Level Climbed");game.write(System.lineSeparator());
            game.write("Level1");game.write(System.lineSeparator());
            game.write("Level2");game.write(System.lineSeparator());
            game.write("Level3");game.write(System.lineSeparator());
            game.write("endSpinner🤖🚀🤖🚀");game.write(System.lineSeparator());
            game.write("EditText");game.write(System.lineSeparator());
            game.write("Notes");game.write(System.lineSeparator());
            game.write("Text");game.write(System.lineSeparator());
            game.write("endActivity🤖🚀🤖🚀");game.write(System.lineSeparator());
            game.write("Destination Deep Space Scouting 2019");
            game.flush();
            game.close();

            FileWriter actList = new FileWriter(scout);
            actList.append("Deep Space");
            actList.flush();
            actList.close();


            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            Log.e("",e.getMessage());

        }
    }

}
