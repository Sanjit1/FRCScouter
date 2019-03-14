package io.github.sanjit1.frcscouter;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class search extends AppCompatActivity {

    ArrayList<String> teams = new ArrayList<>();
    LinearLayout parent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        try {
            parent = findViewById(R.id.parent);
            FileReader activityList = new FileReader((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
            BufferedReader br = new BufferedReader(activityList);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }

            String fileAsString = sb.toString();
            String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);
            for (int numb = 1; numb<arrOfStr.length;numb++ ){
                InputStream ExcelFileToRead = new FileInputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))  +"/"+ (arrOfStr[numb]+".xls"));
                Workbook export = new HSSFWorkbook(ExcelFileToRead);
                int rowNumb = 1;
                while (export.getSheetAt(0).getRow(rowNumb) != null) {
                    if (!teams.contains(export.getSheetAt(0).getRow(rowNumb).getCell(0).toString())){teams.add(export.getSheetAt(0).getRow(rowNumb).getCell(0).toString());}
                    rowNumb++;
                }



            }

            for(int c = 0; c<teams.size(); c++){
                CardView layoutHolder = new CardView(this);
                CardView ref = findViewById(R.id.refCard);
                LinearLayout textHolder = new LinearLayout(this);
                layoutHolder.setLayoutParams(ref.getLayoutParams());
                textHolder.setOrientation(LinearLayout.VERTICAL);
                TextView teamTextView = new TextView(this);
                teamTextView.setTextSize(24);
                teamTextView.setText(teams.get(c));
                textHolder.addView(teamTextView);
                layoutHolder.addView(textHolder);
                parent.addView(layoutHolder);
            }

        } catch (IOException e ){
            Log.e("Shitier",e.getMessage());
        } catch (Exception e) {
            Log.e("Shit",e.getMessage());
        }


    }
}
