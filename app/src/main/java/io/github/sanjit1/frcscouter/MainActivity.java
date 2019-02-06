package io.github.sanjit1.frcscouter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

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

                Save(scout,"activity.🚀🤖🚀");






    }

    public void onClick(View button){

        if (button==findViewById(R.id.templates)){
            Intent myIntent = new Intent(MainActivity.this,
                    templates.class);
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

    public void Save(File root, String childName){
        try
        {
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, childName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            Log.e("",e.getMessage());

        }
    }

}
