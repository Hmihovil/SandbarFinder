package com.udylity.sandbarfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LakeMapActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mapContainer, new LakeMapFragment())
                    .commit();
        }

    }
}
