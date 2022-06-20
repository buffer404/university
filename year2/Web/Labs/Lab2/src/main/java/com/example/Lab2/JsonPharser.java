package com.example.Lab2;

public class JsonPharser {
    public static String toJSON(Coordinates coordinates) {
        System.out.println(coordinates.getY());
        return String.format("{\"x\":\"%s\",\"y\":\"%f\",\"r\":\"%s\","+
                        "\"currentTime\":\"%s\",\"scriptTime\":\"%s\",\"hit\":\"%s\",\"ID\":\"%d\"}",
                coordinates.getX().toString(), coordinates.getY(), coordinates.getR().toString(), coordinates.getCurrentTime(),
                coordinates.getScriptTime(),
                coordinates.getHit() ? "Hit" : "Miss", 1);
    }
}

