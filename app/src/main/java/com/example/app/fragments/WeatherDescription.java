package com.example.app.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.methods.ParseWeather;

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Math.round;

public class WeatherDescription extends DialogFragment {
    ParseWeather weather;


    ImageView icon;



    public WeatherDescription(ParseWeather w){
        weather = w;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_weather_description, null);

        TextView tempMorning = view.findViewById(R.id.FragmentWeatherDescriptionTempMorn);
        TextView tempDay = view.findViewById(R.id.FragmentWeatherDescriptionTempDay);
        TextView tempEve = view.findViewById(R.id.FragmentWeatherDescriptionTempEve);
        TextView tempNight = view.findViewById(R.id.FragmentWeatherDescriptionTempNight);
        TextView feelMorning = view.findViewById(R.id.FragmentWeatherDescriptionFeelMorning);
        TextView feelDay = view.findViewById(R.id.FragmentWeatherDescriptionFeelDay);
        TextView feelEve = view.findViewById(R.id.FragmentWeatherDescriptionFeelEvening);
        TextView feelNight = view.findViewById(R.id.FragmentWeatherDescriptionFeelNight);
        TextView date = view.findViewById(R.id.FragmentWeatherDescriptionDate);
        TextView weatherDescription = view.findViewById(R.id.FragmentWeatherDescriptionWeatherdescription);
        TextView sunrise = view.findViewById(R.id.textView13);
        TextView sunset = view.findViewById(R.id.textView14);
        TextView wind = view.findViewById(R.id.textView5);
        TextView uvi = view.findViewById(R.id.textView10);
        TextView pressure = view.findViewById(R.id.textView11);
        TextView humidity = view.findViewById(R.id.textView12);
        icon = view.findViewById(R.id.FragmentWeatherDescriptionImageIcon);

        tempMorning.setText(weather.temp_morn + "°");
        tempDay.setText(weather.temp_day + "°");
        tempEve.setText(weather.temp_eve + "°");
        tempNight.setText(weather.temp_night + "°");
        feelMorning.setText(weather.feel_morn + "°");
        feelDay.setText(weather.feel_day + "°");
        feelEve.setText(weather.feel_eve + "°");
        feelNight.setText(weather.feel_night + "°");
        sunrise.setText("Sunrise: "+ weather.sunrise);
        sunset.setText("Sunset: "+weather.sunset);
        wind.setText("Wind: "+weather.win_deg + ", "+weather.wind_speed+"m/s");
        uvi.setText("UVI: "+weather.uvi);
        pressure.setText("Pressure: " + weather.pressure + " mm Hg");
        humidity.setText("Humidity: "+ weather.humidity + "%");
        date.setText(weather.date);
        weatherDescription.setText(weather.weather_desc);
        icon.setImageResource(weather.idIcon);
        builder.setView(view);

        return builder.create();
    }

}
