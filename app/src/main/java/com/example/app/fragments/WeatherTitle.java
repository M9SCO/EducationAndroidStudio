package com.example.app.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.methods.ParseWeather;

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Math.round;


public class WeatherTitle extends Fragment implements View.OnClickListener {
    Context context;
    JSONObject data;
    ParseWeather parsed;


    public WeatherTitle(JSONObject d) {
        data = d;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity().getApplicationContext();

        return inflater.inflate(R.layout.fragment_weather_title, container, false);
    }

    @SuppressLint({"WrongViewCast", "SimpleDateFormat"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView view_temp_fact = view.findViewById(R.id.FragmentWeatherTitleTempFact);
        TextView view_date = view.findViewById(R.id.FragmentWeatherTitleTempDay);
        TextView view_weather_title= view.findViewById(R.id.FragmentWeatherTitleTempTitle);
        ImageView view_image = view.findViewById(R.id.FragmentWeatherTitleImage);
        view.setOnClickListener(this);

        try {
            parsed = new ParseWeather(data);
            parsed.toDaily();

            view_temp_fact.setText(parsed.temp_day + "° ");
            view_date.setText(parsed.date);
            view_image.setImageResource(parsed.idIcon);
            view_weather_title.setText(parsed.weather_title);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        FragmentManager mFragmentManager = getFragmentManager();
        WeatherDescription fragmentWeatherContext = new WeatherDescription(parsed);
        fragmentWeatherContext.show(mFragmentManager, "description");

    }
}