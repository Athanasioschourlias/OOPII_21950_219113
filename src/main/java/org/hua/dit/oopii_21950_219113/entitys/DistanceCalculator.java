package org.hua.dit.oopii_21950_219113.entitys;

//TODO: Add documentation
public class DistanceCalculator {

//    public static void main(String[] args) throws java.lang.Exception {
//        float[] cityAthensGR = {37.98f, 23.72f};
//        float[] cityRomeIT = {41.89f, 12.48f};  //Rome US 34.26 -85.16
//        float[] cityMadridES = {40.42f, -3.7f};
//        //System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
//        System.out.println("Distance Athens to Rome: " + distance(cityAthensGR[0], cityAthensGR[1], cityRomeIT[0], cityRomeIT[1], "K") + " Kilometers\n");
//        System.out.println("Distance Athens to Madrid: " + distance(cityAthensGR[0], cityAthensGR[1], cityMadridES[0], cityMadridES[1], "K") + " Kilometers\n");
//        //System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
//    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

}
