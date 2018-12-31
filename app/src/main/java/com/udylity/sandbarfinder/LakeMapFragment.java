package com.udylity.sandbarfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.udylity.sandbarfinder.weather.WeatherData;

public class LakeMapFragment extends Fragment implements GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    private View rootView;

    private SandbarFragment mSandbarFragment;

    int sandbarNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState == null){
            rootView = inflater.inflate(R.layout.fragment_map, container, false);
        }
        Sandbar.createSandbars();
        mapFragment = SupportMapFragment.newInstance();
//        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("TAG", "Ayyyyyyy");
        this.googleMap = googleMap;

        this.googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.getUiSettings().setRotateGesturesEnabled(false);
        this.googleMap.setOnMapLongClickListener(this);
        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setOnMapClickListener(this);

        setWindDirection();

        moveMap(Sandbar.mSandbar[0].getPosition(), 14, 1500);
    }


    public void setWindDirection(){
        int windDirection = WeatherData.getWindDirection();
        if(windDirection != -1) {
            sandbarNum = 0;
            for (Sandbar sandbar : Sandbar.mSandbar) {
                if (sandbar != null) {
                    setColor(sandbar, windDirection);
                    sandbar.setTitle(Integer.toString(sandbarNum));
//                    addMarkers(sandbar);
                    sandbarNum++;
                }

            }
        }
    }

    private void setColor(Sandbar mSandbar, int windDirection) {
        float color = Direction.getColorDirection(mSandbar, windDirection);
        mSandbar.setColor(color);
    }

    private void addMarkers(Sandbar mSandbar){
        googleMap.addMarker(new MarkerOptions()
                        .position(mSandbar.getPosition())
                        .title(mSandbar.getTitle())
                        .icon(BitmapDescriptorFactory.defaultMarker(mSandbar.getColor()))
        );
    }

    private void moveMap(LatLng location, int zoom, int duration){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)
                .zoom(zoom)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), duration, null);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(Integer.toString(++sandbarNum))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        );
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(mSandbarFragment != null){
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(mSandbarFragment)
                    .commit();
        }

        mSandbarFragment = new SandbarFragment();


        int index = 0;
        for(Sandbar sandbar : Sandbar.mSandbar){
            if(sandbar.getTitle().contentEquals(marker.getTitle())){
                Bundle mBundle = new Bundle();
                mBundle.putInt("index", index);
                mSandbarFragment.setArguments(mBundle);
            }
            index++;
        }

        getActivity().getSupportFragmentManager().beginTransaction()
            .add(R.id.mapContainer, mSandbarFragment)
            .commit();

        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(mSandbarFragment.isVisible()){
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(mSandbarFragment)
                    .commit();
        }
    }

}
