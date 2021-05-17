package com.example.app.methods;

import com.example.app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.round;

public class ParseWeather {

    JSONObject json;
    Date thisDate;

    SimpleDateFormat clock = new SimpleDateFormat("HH:mm");
    SimpleDateFormat calendar = new SimpleDateFormat("dd.MM");

    public String date, sunrise, sunset, pressure, humidity, visibility, wind_speed, win_deg, uvi, clouds, weather_title, weather_desc;
    public String temp, feels_like;
    public String temp_morn, temp_day, temp_eve, temp_night, feel_morn, feel_day, feel_eve, feel_night;
    public int idIcon;

    private Date getDateTime(long ts){
        return new Date(new Timestamp(ts*1000).getTime());
    }


    public ParseWeather (JSONObject j) throws JSONException {
        json = j;


        thisDate = getDateTime((long) json.getInt("dt"));
        sunrise = clock.format(getDateTime((long) json.getInt("sunrise")));
        sunset = clock.format(getDateTime((long) json.getInt("sunset")));
        pressure = String.valueOf(round(json.getInt("pressure") * 100 / 133.334));
        humidity = json.getString("humidity");
        uvi = json.getString("uvi");
        clouds = json.getString("clouds");
        wind_speed =  String.valueOf(round(json.getDouble("wind_speed")));
        int deg = json.getInt("wind_deg");
        if (deg < 22) win_deg = "N";
        else if (deg < 66) win_deg = "NE";
        else if (deg < 110) win_deg = "E";
        else if (deg < 154) win_deg = "SE";
        else if (deg < 200) win_deg = "S";
        else if (deg <244) win_deg = "WS";
        else if (deg <288) win_deg = "W";
        else if (deg <332) win_deg = "NW";
        else win_deg = "N";



        JSONObject wi = (JSONObject)  json.getJSONArray("weather").get(0);

        weather_title = wi.getString("main");
        weather_desc = wi.getString("description");
        switch (wi.getString("icon")){
            case ("01d"): idIcon = R.drawable._01d; break;
            case ("02d"): idIcon = R.drawable._02d; break;
            case ("03d"): idIcon = R.drawable._03d; break;
            case ("04d"): idIcon = R.drawable._04d; break;
            case ("09d"): idIcon = R.drawable._09d; break;
            case ("10d"): idIcon = R.drawable._10d; break;
            case ("11d"): idIcon = R.drawable._11d; break;
            case ("13d"): idIcon = R.drawable._13d; break;
            case ("50d"): idIcon = R.drawable._50d; break;
            case ("01n"): idIcon = R.drawable._01n; break;
            case ("02n"): idIcon = R.drawable._02n; break;
            case ("03n"): idIcon = R.drawable._03n; break;
            case ("04n"): idIcon = R.drawable._04n; break;
            case ("09n"): idIcon = R.drawable._09n; break;
            case ("10n"): idIcon = R.drawable._10n; break;
            case ("11n"): idIcon = R.drawable._11n; break;
            case ("13n"): idIcon = R.drawable._13n; break;
            case ("50n"): idIcon = R.drawable._50n; break;

            default: idIcon = R.drawable._01d; break;
        }

    }

    public ParseWeather toDaily() throws JSONException {
        JSONObject JSONTemp = (JSONObject)  json.getJSONObject("temp");
        JSONObject JSONFeelLike = (JSONObject)  json.getJSONObject("feels_like");

        temp_morn = String.valueOf(round(JSONTemp.getDouble("morn")));
        feel_morn = String.valueOf(round(JSONFeelLike.getDouble("morn")));
        temp_day = String.valueOf(round(JSONTemp.getDouble("day")));
        feel_day = String.valueOf(round(JSONFeelLike.getDouble("day")));
        temp_eve = String.valueOf(round(JSONTemp.getDouble("eve")));
        feel_eve = String.valueOf(round(JSONFeelLike.getDouble("eve")));
        temp_night = String.valueOf(round(JSONTemp.getDouble("night")));
        feel_night = String.valueOf(round(JSONFeelLike.getDouble("night")));

        date = calendar.format(thisDate);

        return this;
    }

    public void toCurrent() throws JSONException {
        temp = String.valueOf(json.getDouble("temp"));
        feels_like = String.valueOf(json.getDouble("feels_like"));

    }



}
