package com.udylity.sandbarfinder;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class Direction{
    public static final int NORTH = 0;
    public static final int NORTHEAST = 45;
    public static final int EAST = 90;
    public static final int SOUTHEAST = 135;
    public static final int SOUTH = 180;
    public static final int SOUTHWEST = 225;
    public static final int WEST = 270;
    public static final int NORTHWEST = 315;

    public static float getColorDirection(Sandbar mSandbar, int windDirection){
        int yellowDirection, yellowDirection2, greenDirection, greenDirection2, greenDirection3;
        switch (mSandbar.getDirection()){
            case NORTH:
                yellowDirection = Direction.NORTHWEST;
                yellowDirection2 = Direction.NORTHEAST;
                greenDirection = Direction.SOUTHWEST;
                greenDirection2 = Direction.SOUTHEAST;
                greenDirection3 = SOUTH;
                break;
            case NORTHEAST:
                yellowDirection = Direction.NORTH;
                yellowDirection2 = Direction.EAST;
                greenDirection = Direction.SOUTH;
                greenDirection2 = Direction.WEST;
                greenDirection3 = Direction.SOUTHWEST;
                break;
            case EAST:
                yellowDirection = Direction.SOUTHEAST;
                yellowDirection2 = Direction.NORTHEAST;
                greenDirection = Direction.SOUTHWEST;
                greenDirection2 = Direction.NORTHWEST;
                greenDirection3 = Direction.WEST;
                break;
            case SOUTHEAST:
                yellowDirection = Direction.SOUTH;
                yellowDirection2 = Direction.EAST;
                greenDirection = Direction.NORTH;
                greenDirection2 = Direction.WEST;
                greenDirection3 = Direction.NORTHWEST;
                break;
            case SOUTH:
                yellowDirection = Direction.SOUTHWEST;
                yellowDirection2 = Direction.SOUTHEAST;
                greenDirection = Direction.NORTHWEST;
                greenDirection2 = Direction.NORTHEAST;
                greenDirection3 = Direction.NORTH;
                break;
            case SOUTHWEST:
                yellowDirection = Direction.WEST;
                yellowDirection2 = Direction.SOUTH;
                greenDirection = Direction.EAST;
                greenDirection2 = Direction.NORTH;
                greenDirection3 = Direction.NORTHEAST;
                break;
            case WEST:
                yellowDirection = Direction.NORTHWEST;
                yellowDirection2 = Direction.SOUTHWEST;
                greenDirection = Direction.SOUTHEAST;
                greenDirection2 = Direction.NORTHEAST;
                greenDirection3 = Direction.EAST;
                break;
            case NORTHWEST:
                yellowDirection = Direction.WEST;
                yellowDirection2 = Direction.NORTH;
                greenDirection = Direction.SOUTH;
                greenDirection2 = Direction.EAST;
                greenDirection3 = Direction.SOUTHEAST;
                break;
            default:
                yellowDirection = -1;
                yellowDirection2 = -1;
                greenDirection = -1;
                greenDirection2 = -1;
                greenDirection3 = -1;
        }
        if (windDirection == mSandbar.getDirection()){
            return BitmapDescriptorFactory.HUE_RED;
        }else if(windDirection == yellowDirection || windDirection == yellowDirection2){
            return BitmapDescriptorFactory.HUE_YELLOW;
        }else if (windDirection == greenDirection || windDirection == greenDirection2 || windDirection == greenDirection3){
            return BitmapDescriptorFactory.HUE_GREEN;
        }
        return BitmapDescriptorFactory.HUE_MAGENTA;
    }
}
