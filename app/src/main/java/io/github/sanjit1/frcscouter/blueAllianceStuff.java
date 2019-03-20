package io.github.sanjit1.frcscouter;

import android.os.AsyncTask;

import com.thebluealliance.api.v3.TBA;

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
        try {
            return key.teamRequest.getTeam(number).getNickname();
        }catch(IOException e){
            return "problem:"+e.getMessage();
        }
    }

}
