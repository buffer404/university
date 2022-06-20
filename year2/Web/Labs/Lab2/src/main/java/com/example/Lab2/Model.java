package com.example.Lab2;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Model {
    public Coordinates getCoordinates(Integer x, Double y, Double r){
        Coordinates coordinates = new Coordinates();
        coordinates.setX(x);
        coordinates.setY(y);
        coordinates.setR(r);
        boolean hit = false;

        if (x >= 0 && y >= 0) {
            if (y<=(-2*x)+(r)){
                hit = true;
            }
        }
        else if(x <= 0 && y >= 0) {
            System.out.println(y+" "+ r+ " "+x);
            if (y<=r && Math.abs(r/2)>=Math.abs(x)){
                hit = true;
            }
        }
        else if(x <= 0 && y <= 0) {
            if (y*y<=r*r-x*x){
                hit = true;
            }
        }
        coordinates.setHit(hit);

        long endTime = System.currentTimeMillis();
        coordinates.setScriptTime((endTime-CheckServlet.startTime) + "ms");
        coordinates.setCurrentTime(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
        return coordinates;
        }

}
