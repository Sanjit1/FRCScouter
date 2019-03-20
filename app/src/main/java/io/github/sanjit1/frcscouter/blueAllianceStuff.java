package io.github.sanjit1.frcscouter;

import android.os.AsyncTask;
import android.os.StrictMode;

import com.thebluealliance.api.v3.TBA;
import com.thebluealliance.api.v3.models.Team;
import com.thebluealliance.api.v3.requests.TeamRequest;

import java.io.IOException;

public class blueAllianceStuff extends AsyncTask<Void, Void, String> {
    static TBA key;

    public blueAllianceStuff(String k){
        key = new TBA(k);
    }
    @Override
    protected String doInBackground(Void... voids) {
        return "hi";
    }

    public static String getNickname(int number){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            TeamRequest c = key.teamRequest;
            if(c!=null){
                Team t = c.getTeam(number);
                return t.getNickname();}
            else{return "null";}
        }catch(IOException e){
            return "problem:"+e.getMessage();
        }
    }

    public static String getWebsite(int number){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            TeamRequest c = key.teamRequest;
            if(c!=null){
                Team t = c.getTeam(number);
                return t.getWebsite();}
            else{return "null";}
        }catch(IOException e){
            return "problem:"+e.getMessage();
        }
    }

}
