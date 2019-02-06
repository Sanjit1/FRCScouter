package io.github.sanjit1.frcscouter;

import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.round;

public class viewActivity extends AppCompatActivity {


int lineNumb = 1;
Workbook export;
    ArrayList<checkBoxObject> checks = new ArrayList<>();
    ArrayList<timerObject> timers = new ArrayList<>();
    ArrayList<editTextObject> edits = new ArrayList<>();
    ArrayList<spinnerObject> spins = new ArrayList<>();
    ArrayList<counterObject> counters = new ArrayList<>();
    ArrayList<textObject> texts = new ArrayList<>();
    ArrayList<String> structure = new ArrayList<>();
    ArrayList<String> paramName = new ArrayList<>();

    LinearLayout parent;
    Button  reff ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        parent = findViewById(R.id.parent);
        reff = findViewById(R.id.reference);
        try {
            FileReader cache = new FileReader((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/cache");
            BufferedReader buffR = new BufferedReader(cache);
            StringBuilder stringB = new StringBuilder();
            String l = buffR.readLine();
            while (l != null) {
                stringB.append(l).append("\n");
                l = buffR.readLine();
            }
            String fAsString = stringB.toString();
            String[] arrayOfStr = fAsString.split(System.lineSeparator(), 0);


            FileReader activityList = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/"+arrayOfStr[0]+".🚀🤖");
            BufferedReader br = new BufferedReader(activityList);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);
            getSupportActionBar().setTitle(arrOfStr[0]);
            configureActivity(arrOfStr);
        }catch(IOException e){
            Log.e("Log",e.getMessage());
            Toast.makeText(getApplicationContext(), "Fail try it again", Toast.LENGTH_LONG).show();
        }
    }

//                                        Object declaration begins here

    public class checkBoxObject{
        CardView holder = new CardView(getApplicationContext());
        LinearLayout checkAndText = new LinearLayout(getApplicationContext());
        TextView paramName = new TextView(getApplicationContext());
        CheckBox param = new CheckBox(getApplicationContext());

        public checkBoxObject(String name){
            paramName.setText(name);
            paramName.setTextSize(21);
            checkAndText.addView(param);
            checkAndText.addView(paramName);
            holder.addView(checkAndText);
            parent.addView(holder);
        }

        public String getCheckString(){
            return param.isChecked()?"Yes":"No";
        }

    }


    public class timerObject{
        CardView holder = new CardView(getApplicationContext());
        LinearLayout timerAndText = new LinearLayout(getApplicationContext());
        TextView paramName = new TextView(getApplicationContext());
        TextView value = new TextView(getApplicationContext());
        Button startStop = new Button(getApplicationContext());
        Chronometer chronometer = new Chronometer(getApplicationContext());

        long timeInSecs = 0;
        int intSeconds = 0;

        public timerObject(String name){
            paramName.setText(name);
            paramName.setTextSize(21);
            paramName.setGravity(Gravity.CENTER);
            timerAndText.addView(paramName);
            value.setTextSize(21);
            value.setGravity(Gravity.CENTER);
            value.setText("0:00");
            timerAndText.addView(value);
            startStop.setBackgroundTintList(reff.getBackgroundTintList());
            startStop.setText("Start");
            startStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(startStop.getText()=="Start") {
                        startStop.setText("Stop");
                        chronometer.setBase(SystemClock.elapsedRealtime() + timeInSecs);
                        chronometer.start();
                        intSeconds = round(-timeInSecs/1000);
                        value.setText((intSeconds-(intSeconds%60))/60+":"+((intSeconds%60<10)?"0"+intSeconds%60:intSeconds%60));
                    }else {
                        startStop.setText("Start");
                        timeInSecs  = chronometer.getBase() - SystemClock.elapsedRealtime();
                        chronometer.stop();
                        intSeconds = round(-timeInSecs/1000);
                        value.setText((intSeconds-(intSeconds%60))/60+":"+((intSeconds%60<10)?"0"+intSeconds%60:intSeconds%60));
                    }
                }
            });
            timerAndText.addView(startStop);
            holder.addView(timerAndText);
            parent.addView(holder);
        }

        public String getTimeString(){
            timeInSecs  = chronometer.getBase() - SystemClock.elapsedRealtime();
            chronometer.stop();
            intSeconds = round(-timeInSecs/1000);
            return ((intSeconds-(intSeconds%60))/60+":"+((intSeconds%60<10)?"0"+intSeconds%60:intSeconds%60));
    }

    }


    public class editTextObject{
        CardView holder = new CardView(getApplicationContext());
        LinearLayout text = new LinearLayout(getApplicationContext());
        EditText param = new EditText(getApplicationContext());

        public editTextObject(String name,String type){
            param.setHint(name);
            param.setInputType(Objects.equals(type,"Text")? InputType.TYPE_CLASS_TEXT:InputType.TYPE_CLASS_NUMBER);
            text.addView(param);
            holder.addView(text);
            parent.addView(holder);
        }

        public String getTextString(){
            return param.getText().toString();

        }

    }


    public class spinnerObject{
        CardView holder = new CardView(getApplicationContext());
        LinearLayout spinnerHolder = new LinearLayout(getApplicationContext());
        TextView paramName = new TextView(getApplicationContext());
        Spinner options = new Spinner(getApplicationContext());
        public spinnerObject(String name, ArrayList<String> listOfElements){
            paramName.setText(name);
            paramName.setTextSize(21);
            options.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,listOfElements));
            spinnerHolder.addView(paramName);
            spinnerHolder.addView(options);
            holder.addView(spinnerHolder);
            parent.addView(holder);
        }

        public String getChoiceString(){
            return options.getSelectedItem().toString();
        }

    }

    public class counterObject{
        CardView holder = new CardView(getApplicationContext());
        LinearLayout text = new LinearLayout(getApplicationContext());
        TextView paramName = new TextView(getApplicationContext());
        Button increment = new Button(getApplicationContext());
        TextView value = new TextView(getApplicationContext());
        Button decrement = new Button(getApplicationContext());
        int val = 0;
        public counterObject(String name){
            paramName.setText(name);
            paramName.setTextSize(24);
            paramName.setGravity(Gravity.CENTER);
            increment.setLayoutParams(reff.getLayoutParams());
            increment.setText("+");
            increment.setBackgroundTintList(reff.getBackgroundTintList());
            increment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                val++;
                value.setText(Integer.toString(val));
                }
            });
            value.setTextSize(24);
            value.setText("0");
            value.setGravity(Gravity.CENTER);
            decrement.setLayoutParams(reff.getLayoutParams());
            decrement.setText("-");
            decrement.setBackgroundTintList(reff.getBackgroundTintList());
            decrement.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    if(val>1) val--;
                    value.setText(Integer.toString(val));
                }
            });
            text.addView(paramName);
            text.addView(increment);
            text.addView(value);
            text.addView(decrement);
            holder.addView(text);
            parent.addView(holder);
        }

        public String getValString(){
            return val+"";
        }

    }

    public class textObject{
        CardView holder = new CardView(getApplicationContext());
        LinearLayout textHolder = new LinearLayout(getApplicationContext());
        TextView text = new TextView(getApplicationContext());

        public textObject(String name){
            text.setText(name);
            text.setTextSize(21);
            textHolder.addView(text);
            holder.addView(textHolder);
            parent.addView(holder);
        }

    }

//                                        And It Ended here



    public void configureActivity(String[] array){

            while (lineNumb!=array.length) {
            if (Objects.equals(array[lineNumb], "Check")) {
                lineNumb++;
                paramName.add(array[lineNumb]);
                checks.add(new checkBoxObject(array[lineNumb]));
                structure.add("Check");
            }else if (Objects.equals(array[lineNumb], "Time")) {
                lineNumb++;
                paramName.add(array[lineNumb]);
                timers.add(new timerObject(array[lineNumb]));
                structure.add("Time");
            }else if (Objects.equals(array[lineNumb], "EditText")) {
                lineNumb++;
                paramName.add(array[lineNumb]);
                edits.add(new editTextObject(array[lineNumb],(array[lineNumb+1])));
                lineNumb++;
                structure.add("Edit");
            }else if (Objects.equals(array[lineNumb], "Spinner")) {
                lineNumb++;
                paramName.add(array[lineNumb]);
                structure.add("Spinner");
                String name = array[lineNumb];
                lineNumb++;
                ArrayList<String> options = new ArrayList<>();
                while (!Objects.equals(array[lineNumb],"endSpinner🤖🚀🤖🚀")) {
                    options.add(array[lineNumb]);
                    lineNumb++;
                }
                spins.add(new spinnerObject(name,options));
            }else if (Objects.equals(array[lineNumb], "Counter")) {
                lineNumb++;
                paramName.add(array[lineNumb]);
                structure.add("Counter");
                counters.add(new counterObject(array[lineNumb]));
            }else if (Objects.equals(array[lineNumb], "Text")) {
                lineNumb++;
                paramName.add(array[lineNumb]);
                structure.add("Text");
                texts.add(new textObject(array[lineNumb]));
            }
            lineNumb++;
        }
    }



    public void export(View v) {
        try {
            int checkCounter = 0;
            int timerCounter = 0;
            int editCounter = 0;
            int spinnerCounter = 0;
            int counterCounter = 0;

            File xls = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).getAbsoluteFile(), ("/" + getSupportActionBar().getTitle() + ".xls"));
            int rowNumb = 0;

            if (!xls.exists()) {
                export = new HSSFWorkbook();
                export.createSheet("🚀").createRow(0);

                for (int i = 0; i < paramName.size(); i++) {
                    export.getSheetAt(0).getRow(0).createCell(i).setCellValue(paramName.get(i));
                }
            } else {
                InputStream ExcelFileToRead = new FileInputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/" + getSupportActionBar().getTitle() + ".xls"));
                export = new HSSFWorkbook(ExcelFileToRead);
            }

            while (export.getSheetAt(0).getRow(rowNumb) != null) {
                rowNumb++;
            }
            export.getSheetAt(0).createRow(rowNumb);

        boolean textEmpty = false;
        for (int texCheck = 0; texCheck < edits.size(); texCheck++) {
            if (TextUtils.isEmpty(edits.get(texCheck).getTextString())) {
                textEmpty = true;
                Toast.makeText(getApplicationContext(), "At least one of the text inputs are empty", Toast.LENGTH_LONG).show();
            }
        }
        if (textEmpty) {
        } else {
            for (int i = 0; i < structure.size(); i++) {
                if (Objects.equals(structure.get(i),"Check")){
                    export.getSheet("🚀").getRow(rowNumb).createCell(i).setCellValue(checks.get(checkCounter).getCheckString());
                    checkCounter++;
                } else if (Objects.equals(structure.get(i),"Time")){
                    export.getSheet("🚀").getRow(rowNumb).createCell(i).setCellValue(timers.get(timerCounter).getTimeString());
                    timerCounter++;
                } else if (Objects.equals(structure.get(i),"Edit")){
                    export.getSheet("🚀").getRow(rowNumb).createCell(i).setCellValue(edits.get(editCounter).getTextString());
                    editCounter++;
                } else if (Objects.equals(structure.get(i),"Spinner")){
                    export.getSheet("🚀").getRow(rowNumb).createCell(i).setCellValue(spins.get(spinnerCounter).getChoiceString());
                    spinnerCounter++;
                } else if (Objects.equals(structure.get(i),"Counter")){
                    export.getSheet("🚀").getRow(rowNumb).createCell(i).setCellValue(counters.get(counterCounter).getValString());
                    counterCounter++;
                } else if (Objects.equals(structure.get(i),"Text")){
                }
            }

                FileWriter writer = new FileWriter((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/" + getSupportActionBar().getTitle() + ".xls"));
                writer.flush();
                writer.close();
                FileOutputStream out = new FileOutputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/" + getSupportActionBar().getTitle() + ".xls"));
                export.write(out);
                // now we have to save the file to data.xls phew more work.
                out.close();
                // now that we have successfully finished the conversion, we toast a message to the user telling them about our success
                Toast.makeText(getApplicationContext(), "Exported to "+getSupportActionBar().getTitle()+".xls", Toast.LENGTH_SHORT).show();





        }
        }
            catch (IOException e){

        }
    }
 }


