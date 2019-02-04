package io.github.sanjit1.frcscouter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class templates extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        LinearLayout parent = findViewById(R.id.parent);
        try{
            FileReader activityList = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
            BufferedReader br = new BufferedReader(activityList);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);
            for (int numb = 0; numb<arrOfStr.length;numb++ ){
                CardView layoutHolder = new CardView(this);
                LinearLayout textHolder = new LinearLayout(this);
                final Button nameOfActivity = new Button(this);
                nameOfActivity.setBackgroundTintList(findViewById(R.id.reference).getBackgroundTintList());
                nameOfActivity.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){


                        File activityFile = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/cache"));

                            try{
                                FileWriter writer = new FileWriter(activityFile);
                                writer.append(nameOfActivity.getText());
                                writer.flush();
                                writer.close();
                                Intent myIntent = new Intent(getApplicationContext(),
                                        viewActivity.class);
                                startActivity(myIntent);
                            } catch (IOException e){}

                    }
                });
                nameOfActivity.setText(arrOfStr[numb]);
                textHolder.addView(nameOfActivity);
                layoutHolder.addView(textHolder);
                parent.addView(layoutHolder);
            }
        } catch(IOException e){
            // You are toast!!
                   }



    }

    public void onBackPressed(){
        Intent myIntent = new Intent(this,
                MainActivity.class);
        startActivity(myIntent);
    }

    public void onClick(View button){
        if (button==findViewById(R.id.add)){
            Intent myIntent = new Intent(templates.this,
                    addTemplates.class);
            startActivity(myIntent);
        }
    }


}
