package com.example.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.RequestQueue;
import com.example.app.methods.DataBase;
import com.example.app.methods.MyRequest;
import com.example.app.models.MyAbstractDataBase;
import com.example.app.models.Settings;
import com.example.app.models.SettingsDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class DialogSearchCity extends AppCompatDialogFragment {
    SharedPreferences sPref;
    JSONArray jsonCities;
    public DialogSearchCity(SharedPreferences spref){
        sPref = spref;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        List<String> cities1 = new ArrayList<>();
        String[] cities2;
        try {
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(sPref.getString("jsondata", ""));
            jsonCities = jsonObject.getJSONArray("list");
            for(int i=0; i<jsonObject.getInt("count"); i++){
                JSONObject city = (JSONObject) jsonCities.get(i);
                cities1.add(city.getString("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        cities2 = new String[cities1.size()];
        for (int i = 0; i < cities1.size(); i++) {
            cities2[i] = cities1.get(i);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select City");
        builder.setItems(cities2, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    JSONObject json_city = (JSONObject) jsonCities.get(which);
                    Settings lat = new Settings();
                    Settings lon = new Settings();
                    Settings city = new Settings();
                    city.id = "city";
                    lat.id = "lat";
                    lon.id = "lon";
                    city.value = json_city.getString("name");
                    lat.value = json_city.getJSONObject("coord").getString("lat");
                    lon.value = json_city.getJSONObject("coord").getString("lon");
                    MyAbstractDataBase db = DataBase.getInstance(getContext()).getDatabase();
                    SettingsDAO settingsDAO = db.settingsDAO();
                    settingsDAO.insert(lat);
                    settingsDAO.insert(lon);
                    settingsDAO.insert(city);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                Toast.makeText(getActivity(),
                        "City: " + cities1.get(which),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }



}
