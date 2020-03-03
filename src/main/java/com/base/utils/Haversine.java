package com.base.utils;

public class Haversine {
    private static final double EARTH_RADIUS = 6371.001; // Raio da terra em KM

    /**
     * Calcula a distancia entre as distancias
     * @param startLat latitude 1
     * @param startLong longitude 1
     * @param endLat latitude 2
     * @param endLong longitude 2
     * @return
     */
    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
