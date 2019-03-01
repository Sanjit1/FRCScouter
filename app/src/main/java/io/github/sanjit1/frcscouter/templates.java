package io.github.sanjit1.frcscouter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
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
import java.util.ArrayList;
import java.util.Arrays;

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





            for ( int numb = 1; numb<arrOfStr.length;numb++ ){
                FileReader activity = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/"+arrOfStr[numb]+".🚀🤖");
                BufferedReader b = new BufferedReader(activity);
                StringBuilder s = new StringBuilder();
                String lin = b.readLine();
                while (lin != null) {
                    s.append(lin).append("\n");
                    lin = b.readLine();
                }

                String fi = s.toString();
                String[] arr = fi.split(System.lineSeparator(), 0);
                String desc = arr[arr.length-1];

                final int number = numb;
                CardView layoutHolder = new CardView(this);
                CardView ref = findViewById(R.id.refCard);
                LinearLayout trashAndOthers = new LinearLayout(this);
                Button trash = new Button(this);
                trash.setLayoutParams(findViewById(R.id.reference).getLayoutParams());
                trash.setBackground(getDrawable(R.drawable.ic_delete_black_24dp));
                trash.setGravity(Gravity.END | Gravity.CENTER);
                trashAndOthers.setGravity(Gravity.LEFT | Gravity.CENTER);
                layoutHolder.setLayoutParams(ref.getLayoutParams());
                LinearLayout textHolder = new LinearLayout(this);
                textHolder.setOrientation(LinearLayout.VERTICAL);
                TextView name = new TextView(this);
                name.setTextSize(24);
                TextView description = new TextView(this);
                name.setText(arrOfStr[numb]);
                description.setMaxLines(2);
                description.setText(desc);
                description.setTextSize(17);
                textHolder.addView(name);
                textHolder.addView(description);
                textHolder.setWeightSum(1);
                trashAndOthers.addView(textHolder);
                trashAndOthers.addView(trash);
                layoutHolder.addView(trashAndOthers);
                parent.addView(layoutHolder);
                trash.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        try {
                            File activityWrite = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/activity.🚀🤖🚀"));
                            FileWriter writer = new FileWriter(activityWrite);
                            ArrayList<String> write = new ArrayList<>(Arrays.asList(arrOfStr));
                            for (int adder = 0; adder<write.size(); adder++){
                                if (write.get(adder) != arrOfStr[number]) {
                                    writer.append(write.get(adder));
                                    writer.append(System.lineSeparator());
                                }
                            }
                            writer.close();
                            activityWrite = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/"+arrOfStr[number]+".🚀🤖"));
                            activityWrite.delete();
                            onRestart();
                        }
                        catch(IOException e){}
                    }

                });
                layoutHolder.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){


                        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
                        if (!scout.exists()){
                            createDir(scout);
                            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
                            createDir(scout);

                            File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
                            if(!toCheck.exists())saveDefault();}

                        File activityFile = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/cache"));

                        try{
                            FileWriter writer = new FileWriter(activityFile);
                            writer.append(arrOfStr[number]);
                            writer.flush();
                            writer.close();
                            Intent myIntent = new Intent(getApplicationContext(),
                                    viewActivity.class);
                            startActivity(myIntent);
                        } catch (IOException e){}

                    }
                });

            }
        } catch(IOException e){
            // You are toast!!
        }



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_templates);

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





            for ( int numb = 1; numb<arrOfStr.length;numb++ ){
                FileReader activity = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/"+arrOfStr[numb]+".🚀🤖");
                BufferedReader b = new BufferedReader(activity);
                StringBuilder s = new StringBuilder();
                String lin = b.readLine();
                while (lin != null) {
                    s.append(lin).append("\n");
                    lin = b.readLine();
                }

                String fi = s.toString();
                String[] arr = fi.split(System.lineSeparator(), 0);
                String desc = arr[arr.length-1];

                final int number = numb;
                CardView layoutHolder = new CardView(this);
                CardView ref = findViewById(R.id.refCard);
                LinearLayout trashAndOthers = new LinearLayout(this);
                Button trash = new Button(this);
                trash.setLayoutParams(findViewById(R.id.reference).getLayoutParams());
                trash.setBackground(getDrawable(R.drawable.ic_delete_black_24dp));
                trash.setGravity(Gravity.END | Gravity.CENTER);
                trashAndOthers.setGravity(Gravity.LEFT | Gravity.CENTER);
                layoutHolder.setLayoutParams(ref.getLayoutParams());
                LinearLayout textHolder = new LinearLayout(this);
                textHolder.setOrientation(LinearLayout.VERTICAL);
                TextView name = new TextView(this);
                name.setTextSize(24);
                TextView description = new TextView(this);
                name.setText(arrOfStr[numb]);
                description.setMaxLines(2);
                description.setText(desc);
                description.setTextSize(17);
                textHolder.addView(name);
                textHolder.addView(description);
                textHolder.setWeightSum(1);
                trashAndOthers.addView(textHolder);
                trashAndOthers.addView(trash);
                layoutHolder.addView(trashAndOthers);
                parent.addView(layoutHolder);
                trash.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        try {
                            File activityWrite = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/activity.🚀🤖🚀"));
                            FileWriter writer = new FileWriter(activityWrite);
                            ArrayList<String> write = new ArrayList<>(Arrays.asList(arrOfStr));
                            for (int adder = 0; adder<write.size(); adder++){
                                if (write.get(adder) != arrOfStr[number]) {
                                    writer.append(write.get(adder));
                                    writer.append(System.lineSeparator());
                                }
                            }
                            writer.flush();
                            writer.close();
                            activityWrite = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/"+arrOfStr[number]+".🚀🤖"));
                            activityWrite.delete();
                            activityWrite = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + (arrOfStr[number]+".xls"));
                            activityWrite.delete();
                            onRestart();
                        }
                        catch(IOException e){}
                    }

                });
                layoutHolder.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){


                        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
                        if (!scout.exists()){
                            createDir(scout);
                            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
                            createDir(scout);

                            File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
                            if(!toCheck.exists())saveDefault(); }

                        File activityFile = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/cache"));

                        try{
                            FileWriter writer = new FileWriter(activityFile);
                            writer.append(arrOfStr[number]);
                            writer.flush();
                            writer.close();
                            Intent myIntent = new Intent(getApplicationContext(),
                                    viewActivity.class);
                            startActivity(myIntent);
                        } catch (IOException e){}

                    }
                });

            }
        } catch(IOException e){
            // You are toast!!
        }


    }


    public void createDir(File scout){
        if (!scout.exists() && !scout.isDirectory()) {
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
            File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");

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


        public void onBackPressed(){
        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
        if (!scout.exists()){
            createDir(scout);
            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
            createDir(scout);

            File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
            if(!toCheck.exists())saveDefault();}
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

            File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
            if(!toCheck.exists()) saveDefault(); }
        if (button==findViewById(R.id.add)){
            Intent myIntent = new Intent(templates.this,
                    addTemplates.class);
            startActivity(myIntent);
        }
    }


}
