package io.github.sanjit1.frcscouter;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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


        } catch (IOException e){}
    }

}
