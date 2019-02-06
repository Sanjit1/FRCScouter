package io.github.sanjit1.frcscouter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
                        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
                        if (!scout.exists()){
                            createDir(scout);
                            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
                            createDir(scout);

                            Save(scout,"activity.🚀🤖🚀"); }

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



    public void onBackPressed(){
        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
        if (!scout.exists()){
            createDir(scout);
            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
            createDir(scout);

            Save(scout,"activity.🚀🤖🚀"); }
        Intent myIntent = new Intent(this,
                MainActivity.class);
        startActivity(myIntent);
    }

    public void onClick(View button){
        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
        if (!scout.exists()){
            createDir(scout);
            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
            createDir(scout);

            Save(scout,"activity.🚀🤖🚀"); }
        if (button==findViewById(R.id.add)){
            Intent myIntent = new Intent(templates.this,
                    addTemplates.class);
            startActivity(myIntent);
        }
    }


}
