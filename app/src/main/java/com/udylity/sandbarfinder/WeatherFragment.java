package com.udylity.sandbarfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.udylity.sandbarfinder.weather.DownloadWeather;
import com.udylity.sandbarfinder.weather.Utility;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    private String city = "Walled Lake";
    public static TextView mTextView, mSunsetTextView;
    private static Button mShowMapBtn;

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(WeatherActivity.mTwoPane){
            rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        }else{
            rootView = inflater.inflate(R.layout.fragment_weather_phone, container, false);
            mShowMapBtn = (Button)rootView.findViewById(R.id.map_button);
            mShowMapBtn.setOnClickListener(this);
        }

        mTextView = (TextView) rootView.findViewById(R.id.weatherTextView);
        mSunsetTextView = (TextView) rootView.findViewById(R.id.sunset_textview);

        return rootView;
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString("weather"));
        }else{
            this.city = Utility.getPreferredLocation(getActivity());
            updateWeather(this.city);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("weather", mTextView.getText().toString());
    }

    public void updateWeather(String city){
        this.city = city;
//        new DownloadWeather().execute(this.city);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.map_button:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.weatherContainer, WeatherActivity.mapFragment)
                        .commit();
                break;
        }
    }
}