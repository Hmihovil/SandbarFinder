package com.udylity.sandbarfinder;

import com.google.android.gms.maps.model.LatLng;

public class Sandbar {

    public static Sandbar[] mSandbar = new Sandbar[5];

    private String title = "";
    private int direction;
    private LatLng position;
    private float color, rate;

    public Sandbar(int direction, LatLng position) {
        this.direction = direction;
        this.position = position;
    }

    public static void createSandbars() {
        mSandbar[0] = new Sandbar(Direction.WEST, new LatLng(42.5719118, -83.4968928));
        mSandbar[1] = new Sandbar(Direction.WEST, new LatLng(42.5728782,-83.4986952));
        mSandbar[2] = new Sandbar(Direction.EAST, new LatLng(42.5823718, -83.4983858));
        mSandbar[3] = new Sandbar(Direction.SOUTH, new LatLng(42.5832076,-83.495436));
        mSandbar[4] = new Sandbar(Direction.SOUTHEAST, new LatLng(42.5832753, -83.4922884));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getDirection(){
        return direction;
    }

    public LatLng getPosition(){
        return position;
    }

    public void setColor(float color) {
        this.color = color;
    }

    public float getColor() {
        return color;
    }

    public void setRate(float rate){
        this.rate = rate;
    }

    public float getRate(){
        return rate;
    }

}
