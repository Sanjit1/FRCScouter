package io.github.sanjit1.frcscouter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class addTemplates extends AppCompatActivity {
    ArrayList<editTextObject> textInput = new ArrayList<>();
    ArrayList<spinnerObject> spinnerObjects = new ArrayList<>();
    ArrayList<String> paramName = new ArrayList<>();
    ArrayList<String> structure = new ArrayList<>();
    ArrayList<EditText> paramNameInput = new ArrayList<>();

    int numberOfTimer = 0;
    int numberOfText = 0 ;
    int numberOfEditText = 0;
    int numberOfCounter = 0;
    int numberOfSpinner = 0;
    int numberOfCheckBox = 0;
    boolean addOpen = false;

    LinearLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_templates);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parentView = findViewById(R.id.parent);


        findViewById(R.id.textFABHolder).setVisibility(View.GONE);
        findViewById(R.id.counterFABHolder).setVisibility(View.GONE);
        findViewById(R.id.spinnerFABHolder).setVisibility(View.GONE);
        findViewById(R.id.editTextFABHolder).setVisibility(View.GONE);
        findViewById(R.id.timerFABHolder).setVisibility(View.GONE);
        findViewById(R.id.checkFABHolder).setVisibility(View.GONE);

        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
        createDir(scout);
        scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
        createDir(scout);

        File toCheck = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
        if(!toCheck.exists())Save(scout,"activity.🚀🤖🚀");
    }






    public void onBackPressed(){
        Intent myIntent = new Intent(this,
                templates.class);
        startActivity(myIntent);
    }


    public class spinnerObject{
        public ArrayList<LinearLayout> optionHolder = new ArrayList<>();
        public ArrayList<EditText> optionInput = new ArrayList<>();
        public ArrayList<String> options = new ArrayList<>();
        public  spinnerObject(){
        }
        public LinearLayout addOptionListener(){
            optionHolder.add(new LinearLayout(getApplicationContext()));
            optionInput.add(new EditText(getApplicationContext()));
            optionInput.get(optionInput.size()-1).setHint("Option Name");
            optionHolder.get(optionHolder.size()-1).addView(optionInput.get(optionInput.size()-1));
            return optionHolder.get(optionHolder.size()-1);
        }
        public String getOption(int position){
            return options.get(position);
        }
        public ArrayList<String> getOptions(){
            return options;
        }
        public void convertEditTextToString(){
            for (int i = 0;i<optionInput.size();i++){
                options.add(optionInput.get(i).getText().toString());
            }
        }
    }

    public class editTextObject {
        public boolean inputType;
        Spinner inputSpinner = new Spinner(getApplicationContext());
        public editTextObject(){
            ArrayList<String> options = new ArrayList<>();
            options.add("Text");
            options.add("Numeric");
            inputSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,options));
        }
        public Spinner getInputSpinner(){
            return inputSpinner;
        }
        public void setInputFromSpinner(){
            inputType = (inputSpinner.getSelectedItem()=="Text");
        }
        public String getInputString(){
            return inputType?"Text":"Numeric";
        }
    }

    public void openCloseFab(View v){
        addOpen =!addOpen;
        if(addOpen) {
            findViewById(R.id.textFABHolder).setVisibility(View.VISIBLE);
            findViewById(R.id.counterFABHolder).setVisibility(View.VISIBLE);
            findViewById(R.id.spinnerFABHolder).setVisibility(View.VISIBLE);
            findViewById(R.id.editTextFABHolder).setVisibility(View.VISIBLE);
            findViewById(R.id.timerFABHolder).setVisibility(View.VISIBLE);
            findViewById(R.id.checkFABHolder).setVisibility(View.VISIBLE);
            findViewById(R.id.textFABHolder).animate().translationY(-getResources().getDimension(R.dimen.level_1));
            findViewById(R.id.counterFABHolder).animate().translationY(-getResources().getDimension(R.dimen.level_2));
            findViewById(R.id.spinnerFABHolder).animate().translationY(-getResources().getDimension(R.dimen.level_3));
            findViewById(R.id.editTextFABHolder).animate().translationY(-getResources().getDimension(R.dimen.level_4));
            findViewById(R.id.timerFABHolder).animate().translationY(-getResources().getDimension(R.dimen.level_5));
            findViewById(R.id.checkFABHolder).animate().translationY(-getResources().getDimension(R.dimen.level_6));
        }else{
            findViewById(R.id.textFABHolder).animate().translationY(-getResources().getDimension(R.dimen.naturalFabY)).translationX(-getResources().getDimension(R.dimen.naturalFabX));
            findViewById(R.id.counterFABHolder).animate().translationY(-getResources().getDimension(R.dimen.naturalFabY)).translationX(-getResources().getDimension(R.dimen.naturalFabX));
            findViewById(R.id.spinnerFABHolder).animate().translationY(-getResources().getDimension(R.dimen.naturalFabY)).translationX(-getResources().getDimension(R.dimen.naturalFabX));
            findViewById(R.id.editTextFABHolder).animate().translationY(-getResources().getDimension(R.dimen.naturalFabY)).translationX(-getResources().getDimension(R.dimen.naturalFabX));
            findViewById(R.id.timerFABHolder).animate().translationY(-getResources().getDimension(R.dimen.naturalFabY)).translationX(-getResources().getDimension(R.dimen.naturalFabX));
            findViewById(R.id.checkFABHolder).animate().translationY(-getResources().getDimension(R.dimen.naturalFabY)).translationX(-getResources().getDimension(R.dimen.naturalFabX));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.textFABHolder).setVisibility(View.GONE);
                    findViewById(R.id.counterFABHolder).setVisibility(View.GONE);
                    findViewById(R.id.spinnerFABHolder).setVisibility(View.GONE);
                    findViewById(R.id.editTextFABHolder).setVisibility(View.GONE);
                    findViewById(R.id.timerFABHolder).setVisibility(View.GONE);
                    findViewById(R.id.checkFABHolder).setVisibility(View.GONE);
                }
            }, 265);

        }
    }



    public void addView(View v){
        openCloseFab(v);
        switch (v.getId()){
            case  R.id.checkFAB:
                structure.add("Check");
                CardView checkLinearHolder = new CardView(this);
                LinearLayout checkViewHolder = new LinearLayout(this);
                paramNameInput.add(new EditText(this));
                paramNameInput.get(paramNameInput.size()-1).setHint("Parameter Name");
                numberOfCheckBox++;
                CheckBox check = new CheckBox(this);
                checkViewHolder.addView(check);
                checkViewHolder.addView(paramNameInput.get(paramNameInput.size()-1));
                checkLinearHolder.addView(checkViewHolder);
                parentView.addView(checkLinearHolder);
                break;

            case  R.id.timerFAB:
                structure.add("Time");
                CardView timerLinearHolder = new CardView(this);
                LinearLayout timerViewHolder = new LinearLayout(this);
                paramNameInput.add(new EditText(this));
                paramNameInput.get(paramNameInput.size()-1).setHint("Parameter Name");
                numberOfTimer++;
                timerViewHolder.addView(paramNameInput.get(paramNameInput.size()-1));

                TextView timer = new TextView(this);
                final Button startStop = new Button(this);
                startStop.setBackgroundTintList(findViewById(R.id.Save).getBackgroundTintList());
                timer.setText("0:00");
                timer.setTextSize(21);
                startStop.setText("Start");
                startStop.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        startStop.setText(startStop.getText()=="Start"?"Stop":"Start");
                        if(v==startStop) Toast.makeText(getApplicationContext(),"Functionality after saving",Toast.LENGTH_LONG).show();
                    }
                });
                timerViewHolder.addView(timer);
                timerViewHolder.addView(startStop);
                timerLinearHolder.addView(timerViewHolder);
                parentView.addView(timerLinearHolder);
            break;

            case  R.id.editTextFAB:
                numberOfEditText++;
                structure.add("EditText");
                CardView editTextLinearHolder = new CardView(this);
                LinearLayout editTextViewHolder = new LinearLayout(this);
                paramNameInput.add(new EditText(this));
                paramNameInput.get(paramNameInput.size()-1).setHint("Parameter Name");
                numberOfCheckBox++;
                textInput.add(new editTextObject());
                editTextViewHolder.addView(paramNameInput.get(paramNameInput.size()-1));
                editTextViewHolder.addView(textInput.get(textInput.size()-1).getInputSpinner());
                editTextLinearHolder.addView(editTextViewHolder);
                parentView.addView(editTextLinearHolder);
                break;

            case  R.id.spinnerFAB:
                numberOfSpinner++;
                structure.add("Spinner");
                spinnerObjects.add(new spinnerObject());
                CardView spinnerLinearHolder = new CardView(this);
                Button add = new Button(this);
                final LinearLayout spinnerViewHolder = new LinearLayout(this);
                spinnerViewHolder.setOrientation(LinearLayout.VERTICAL);
                LinearLayout spinnerNameHolder = new LinearLayout(this);
                paramNameInput.add(new EditText(this));
                paramNameInput.get(paramNameInput.size()-1).setHint("Parameter Name");
                add.setBackgroundResource(R.drawable.plus_fab);
                spinnerNameHolder.addView(paramNameInput.get(paramNameInput.size()-1));
                add.setLayoutParams(findViewById(R.id.referenceButton).getLayoutParams());
                add.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    spinnerViewHolder.addView(spinnerObjects.get(spinnerObjects.size()-1).addOptionListener());
                }
                });
                spinnerNameHolder.addView(add);
                spinnerViewHolder.addView(spinnerNameHolder);
                spinnerLinearHolder.addView(spinnerViewHolder);
                parentView.addView(spinnerLinearHolder);
                break;

            case  R.id.counterFAB:
                structure.add("Counter");
                CardView counterLinearHolder = new CardView(this);
                LinearLayout counterViewHolder = new LinearLayout(this);
                paramNameInput.add(new EditText(this));
                paramNameInput.get(paramNameInput.size()-1).setHint("Parameter Name");
                numberOfCounter++;
                counterViewHolder.addView(paramNameInput.get(paramNameInput.size()-1));
                Button increment = new Button(this);
                increment.setText("+");
                increment.setBackgroundTintList(findViewById(R.id.Save).getBackgroundTintList());
                increment.setLayoutParams(findViewById(R.id.referenceButton).getLayoutParams());
                Button decrement = new Button(this);
                decrement.setText("-");
                decrement.setBackgroundTintList(findViewById(R.id.Save).getBackgroundTintList());
                decrement.setLayoutParams(findViewById(R.id.referenceButton).getLayoutParams());
                TextView value = new TextView(this);
                value.setText("0");
                value.setTextSize(21);
                counterViewHolder.addView(increment);
                counterViewHolder.addView(value);
                counterViewHolder.addView(decrement);
                counterLinearHolder.addView(counterViewHolder);
                parentView.addView(counterLinearHolder);
                break;

            case  R.id.textFAB:
                numberOfText++;
                structure.add("Text");
                CardView textLinearHolder = new CardView(this);
                LinearLayout textViewHolder = new LinearLayout(this);
                paramNameInput.add(new EditText(this));
                paramNameInput.get(paramNameInput.size()-1).setHint("Parameter Name");
                numberOfCounter++;
                textViewHolder.addView(paramNameInput.get(paramNameInput.size()-1));
                textLinearHolder.addView(textViewHolder);
                parentView.addView(textLinearHolder);
                break;

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
            writer.append("");
            writer.flush();
            writer.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            Log.e("",e.getMessage());

        }
    }

    public void save(View v){

        try {





            File activityFile = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/activity.🚀🤖🚀"));
            String[] arrOfStr;
            FileReader activityList = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/activity.🚀🤖🚀");
            BufferedReader br = new BufferedReader(activityList);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            arrOfStr = fileAsString.split(System.lineSeparator(), 0);



            FileWriter activityAdder = new FileWriter(activityFile);
            for (int i = 0; i < arrOfStr.length; i++) {
                activityAdder.append(arrOfStr[i]);
                activityAdder.append(System.lineSeparator());
            }
            if(!Arrays.asList(arrOfStr).contains(((EditText) findViewById(R.id.activityName)).getText().toString())) activityAdder.append(((EditText) findViewById(R.id.activityName)).getText().toString());

            activityAdder.flush();
            activityAdder.close();








            int numOfEdit = 0;
            int numOfSpinner = 0;
            EditText name = findViewById(R.id.activityName);
            for (int paramCounter = 0; paramCounter<paramNameInput.size(); paramCounter++) paramName.add(paramNameInput.get(paramCounter).getText().toString());

            File activity = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/ScouterAppData/ActivityData/"+name.getText().toString()+".🚀🤖"));

            FileWriter writer = new FileWriter(activity);
            writer.append(((EditText) findViewById(R.id.activityName)).getText().toString());

            writer.append(System.lineSeparator());
            for(int counter = 0; counter<structure.size();counter++) {
                if(Objects.equals(structure.get(counter), "Check")) {
                    writer.append("Check");
                    writer.append(System.lineSeparator());
                    writer.append(paramName.get(counter));
                    writer.append(System.lineSeparator());
                }
                else
                    if(Objects.equals(structure.get(counter), "Time")) {
                        writer.append("Time");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(counter));
                        writer.append(System.lineSeparator());
                }
                else
                    if(Objects.equals(structure.get(counter), "EditText")) {
                        writer.append("EditText");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(counter));
                        writer.append(System.lineSeparator());
                        textInput.get(numOfEdit).setInputFromSpinner();
                        writer.append(textInput.get(numOfEdit).getInputString());
                        writer.append(System.lineSeparator());
                        numOfEdit++;
                }
                else
                    if(Objects.equals(structure.get(counter), "Spinner")) {
                        writer.append("Spinner");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(counter));
                        writer.append(System.lineSeparator());
                        spinnerObjects.get(numOfSpinner).convertEditTextToString();
                        for(int spinCount = 0; spinCount<spinnerObjects.get(numOfSpinner).getOptions().size();spinCount++){
                            writer.append(spinnerObjects.get(numOfSpinner).getOption(spinCount));
                            writer.append(System.lineSeparator());
                        }
                        numOfSpinner++;
                        writer.append("endSpinner🤖🚀🤖🚀");
                        writer.append(System.lineSeparator());
                }
                else
                    if(Objects.equals(structure.get(counter), "Counter")) {
                        writer.append("Counter");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(counter));
                        writer.append(System.lineSeparator());
                }
                else
                    if(Objects.equals(structure.get(counter), "Text")) {
                        writer.append("Text");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(counter));
                        writer.append(System.lineSeparator());
                }
            }
            writer.append("endActivity🤖🚀🤖🚀");
            EditText description = findViewById(R.id.description);
            writer.append(System.lineSeparator());
            writer.append(description.getText().toString());
            writer.flush();
            writer.close();

        } catch (IOException e){
            // You are toast!!
            Log.e("Log",e.getMessage());
            Toast.makeText(getApplicationContext(), "Fail try it again", Toast.LENGTH_LONG).show();
        }
    }


}
