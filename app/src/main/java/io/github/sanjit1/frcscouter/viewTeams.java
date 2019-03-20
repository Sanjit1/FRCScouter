package io.github.sanjit1.frcscouter;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class viewTeams extends AppCompatActivity {
String teamNumber ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teams);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            FileReader cache = new FileReader((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/teamData/cache");
            BufferedReader buffR = new BufferedReader(cache);
            StringBuilder stringB = new StringBuilder();
            String l = buffR.readLine();
            while (l != null) {
                stringB.append(l).append("\n");
                l = buffR.readLine();
            }
            String fAsString = stringB.toString();
            String[] arrayOfStr = fAsString.split(System.lineSeparator(), 0);
            teamNumber = arrayOfStr[0];
            frcTeam Robot = new frcTeam(Integer.parseInt(teamNumber));
            getSupportActionBar().setTitle(teamNumber);
            TextView textViewName = findViewById(R.id.teamName);
            textViewName.setText(Robot.nickname);


        } catch (IOException e){}
    }

    class frcTeam{
        int number;
        blueAllianceStuff requester;
        String nickname;
        String website;
        ArrayList<String> templatesUsed;
        ArrayList<Integer> numberOfGames;
        ArrayList<HSSFWorkbook> results;
        public frcTeam(int numb){
            requester = new blueAllianceStuff("");
            number = numb;
            nickname = requester.getNickname(number);
            website = requester.getWebsite(number);
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

}
