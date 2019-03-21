package io.github.sanjit1.frcscouter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class viewTeams extends AppCompatActivity {
String teamNumber ;
LinearLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teams);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parent = findViewById(R.id.parent);
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
            for(int tem = 0; tem<Robot.templatesUsed.size();tem++) {
                CardView tempName = new CardView(this);
                tempName.setElevation(10);
                LinearLayout tempCardChild = new LinearLayout(this);
                tempCardChild.setOrientation(LinearLayout.VERTICAL);
                TextView nameOfTemplate = new TextView(this);
                nameOfTemplate.setText(Robot.templatesUsed.get(tem));
                nameOfTemplate.setTextSize(27);
                tempCardChild.addView(nameOfTemplate);
                for (int not = 0; not<Robot.numberOfGames.get(tem); not++){
                    CardView txtHolder = new CardView(this);
                    txtHolder.setElevation(3);
                    TextView matchNumber = new TextView(this);
                    matchNumber.setTextSize(20);
                    matchNumber.setText("Match "+(not+1));
                    txtHolder.addView(matchNumber);
                    tempCardChild.addView(txtHolder);
                    int templ = tem;
                    int nt = not;
                    txtHolder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            File activityFile = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/teamData/viewerCache"));

                            try{
                                FileWriter writer = new FileWriter(activityFile);
                                writer.append(teamNumber);
                                writer.append(System.lineSeparator());
                                writer.append(Robot.templatesUsed.get(templ));
                                writer.append(System.lineSeparator());
                                writer.append((nt+1)+"");
                                writer.flush();
                                writer.close();
                                Intent myIntent = new Intent(getApplicationContext(),
                                        viewData.class);
                                startActivity(myIntent);
                            } catch (IOException e){}
                        }
                    });
                }
                tempName.addView(tempCardChild);
                parent.addView(tempName);
            }


        } catch (IOException e){}
    }

    class frcTeam{
        int number;
        blueAllianceStuff requester;
        String nickname;
        String website;
        ArrayList<String> templatesUsed = new ArrayList<>();
        ArrayList<Integer> numberOfGames = new ArrayList<>();
        ArrayList<HSSFWorkbook> results = new ArrayList<>();
        public frcTeam(int numb){
            requester = new blueAllianceStuff("KVyomvzVScCbzlVUxYiW7TECJrAyN7u6pzgGpiNQ92jFLu6amRviYxbJA2ORh5cc");
            number = numb;
            nickname = requester.getNickname(number);
            website = requester.getWebsite(number);
            try {
                FileReader teamList = new FileReader((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + ("/ScouterAppData/teamData/"+number+"/templatesUsed.hi"));
                BufferedReader br = new BufferedReader(teamList);
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line).append("\n");
                    line = br.readLine();
                }

                String fileAsString = sb.toString();
                String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);
                for (int n = 0; n<arrOfStr.length;n++) {
                    int rowNumb=0;
                    templatesUsed.add(arrOfStr[0]);
                    InputStream XLfiles = new FileInputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/teamData/"+ number + "/" + arrOfStr[n] + ".xls"));
                    results.add(new HSSFWorkbook(XLfiles));
                    while (results.get(results.size()-1).getSheetAt(0).getRow(rowNumb) != null) {
                        rowNumb++;
                    }
                    numberOfGames.add(rowNumb-1);
                }

            } catch (IOException e){

            }
        }


    }

}
