package model.haversine;

public class Haversine
{
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
    
    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) 
    {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
    
    public static void main(String[] args)
    {
    	double a = distance(41.991178, -87.683593, 41.774074, -87.663815);
    	System.out.println(a);
    }
}