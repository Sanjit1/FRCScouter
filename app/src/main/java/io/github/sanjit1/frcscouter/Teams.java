package io.github.sanjit1.frcscouter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thebluealliance.api.v3.TBA;
import com.thebluealliance.api.v3.models.Event;
import com.thebluealliance.api.v3.models.Team;
import com.thebluealliance.api.v3.requests.TeamRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class teams extends AppCompatActivity {

    class frcTeam{
        int number;
        blueAllianceStuff requester;
        String nickname;
        String website;
        ArrayList<String> templatesUsed;
        ArrayList<Integer> numberOfGames;
        ArrayList<HSSFWorkbook> results;
        public frcTeam(int numb){
            requester = new blueAllianceStuff("KVyomvzVScCbzlVUxYiW7TECJrAyN7u6pzgGpiNQ92jFLu6amRviYxbJA2ORh5cc");
            number = numb;
            File teamProperties = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + ("/ScouterAppData/teamData/"+number+"/info.hi"));
            if(!teamProperties.exists()) {
                nickname = requester.getNickname(number);
                website = requester.getWebsite(number);
                try {
                    FileWriter nameNdSite = new FileWriter(teamProperties);
                    nameNdSite.append(nickname);
                    nameNdSite.append(System.lineSeparator());
                    nameNdSite.append(website);
                    nameNdSite.flush();
                    nameNdSite.close();
                }catch (IOException e){}

            }else
                {
                    try{
                        FileReader teamList = new FileReader((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + ("/ScouterAppData/teamData/"+number+"/info.hi"));
                        BufferedReader br = new BufferedReader(teamList);
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();
                        while (line != null) {
                            sb.append(line).append("\n");
                            line = br.readLine();
                        }
                        String fileAsString = sb.toString();
                        String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);
                        nickname = arrOfStr[0];
                        website = arrOfStr[1];
                    }catch (IOException e){}
                }
            try {
                FileReader teamList = new FileReader((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + ("/ScouterAppData/teamData/"+number+"templatesUsed.hi"));
                BufferedReader br = new BufferedReader(teamList);
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line).append("\n");
                    line = br.readLine();
                }

                String fileAsString = sb.toString();
                String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);
                for (String template : arrOfStr) {
                    int rowNumb=0;
                    templatesUsed.add(template);
                    InputStream XLfiles = new FileInputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/teamData/"+ number + "/" + template + ".xls"));
                    results.add(new HSSFWorkbook(XLfiles));
                    while (results.get(results.size()).getSheetAt(0).getRow(rowNumb) != null) {
                        rowNumb++;
                    }
                    numberOfGames.add(rowNumb-1);
                }


            } catch (IOException e){

            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        LinearLayout parent = findViewById(R.id.parent);
        try{
            FileReader teamList = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + ("/ScouterAppData/teamData/teams.hi"));
            BufferedReader br = new BufferedReader(teamList);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }

            String fileAsString = sb.toString();
            String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);





            for ( int numb = 0; numb<arrOfStr.length;numb++ ){
                frcTeam team = new frcTeam(Integer.parseInt(arrOfStr[numb]));
                final int number = numb;
                CardView layoutHolder = new CardView(this);
                CardView ref = findViewById(R.id.refCard);
                ImageView avatar = new ImageView(this);
                LinearLayout picAndOthers = new LinearLayout(this);
                picAndOthers.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout trashAndOthers = new LinearLayout(this);
                layoutHolder.setLayoutParams(ref.getLayoutParams());
                LinearLayout textHolder = new LinearLayout(this);
                textHolder.setOrientation(LinearLayout.VERTICAL);
                TextView name = new TextView(this);
                name.setTextSize(24);
                TextView description = new TextView(this);
                name.setText(arrOfStr[numb]);
                description.setMaxLines(2);
                description.setText(team.nickname);
                description.setTextSize(17);
                textHolder.addView(name);
                textHolder.addView(description);
                textHolder.setWeightSum(1);
                trashAndOthers.addView(textHolder);
                picAndOthers.addView(trashAndOthers);
                picAndOthers.addView(avatar);
                layoutHolder.addView(picAndOthers);
                parent.addView(layoutHolder);
                layoutHolder.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){


                        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
                        if (!scout.exists()){
                            createDir(scout);
                            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
                            createDir(scout);

                            File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
                            if(!toCheck.exists())saveDefault();}

                        File activityFile = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/teamData/cache"));

                        try{
                            FileWriter writer = new FileWriter(activityFile);
                            writer.append(arrOfStr[number]);
                            writer.flush();
                            writer.close();
                            Intent myIntent = new Intent(getApplicationContext(),
                                    viewTeams.class);
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
        setContentView(R.layout.activity_teams);

        LinearLayout parent = findViewById(R.id.parent);
        try{
            FileReader teamList = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + ("/ScouterAppData/teamData/teams.hi"));
            BufferedReader br = new BufferedReader(teamList);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }

            String fileAsString = sb.toString();
            String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);





            for ( int numb = 0; numb<arrOfStr.length;numb++ ){
                frcTeam team = new frcTeam(Integer.parseInt(arrOfStr[numb]));
                final int number = numb;
                CardView layoutHolder = new CardView(this);
                CardView ref = findViewById(R.id.refCard);
                LinearLayout trashAndOthers = new LinearLayout(this);
                layoutHolder.setLayoutParams(ref.getLayoutParams());
                LinearLayout textHolder = new LinearLayout(this);
                textHolder.setOrientation(LinearLayout.VERTICAL);
                TextView name = new TextView(this);
                name.setTextSize(24);
                TextView description = new TextView(this);
                name.setText(arrOfStr[numb]);
                description.setMaxLines(2);
                description.setText(team.nickname);
                description.setTextSize(17);
                textHolder.addView(name);
                textHolder.addView(description);
                textHolder.setWeightSum(1);
                trashAndOthers.addView(textHolder);
                layoutHolder.addView(trashAndOthers);
                parent.addView(layoutHolder);
                layoutHolder.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){


                        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
                        if (!scout.exists()){
                            createDir(scout);
                            scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
                            createDir(scout);

                            File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
                            if(!toCheck.exists())saveDefault();}

                        File activityFile = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/teamData/cache"));

                        try{
                            FileWriter writer = new FileWriter(activityFile);
                            writer.append(arrOfStr[number]);
                            writer.flush();
                            writer.close();
                            Intent myIntent = new Intent(getApplicationContext(),
                                    viewTeams.class);
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


    }


}

