package uz.epam.kafkastream.task1.dto;


public class VehicleSession {
    private Long id;
    private double lastLat;
    private double lastLon;
    private double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLastLat() {
        return lastLat;
    }

    public void setLastLat(double lastLat) {
        this.lastLat = lastLat;
    }

    public double getLastLon() {
        return lastLon;
    }

    public void setLastLon(double lastLon) {
        this.lastLon = lastLon;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void changeDistance(double lat2, double lon2) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.

        double lon1 = Math.toRadians(this.lastLon);
        lon2 = Math.toRadians(lon2);
        double lat1 = Math.toRadians(this.lastLat);
        lat2 = Math.toRadians(lat2);
        this.setLastLat(lat2);
        this.setLastLon(lon2);


        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        double dis = (c * r);


        this.distance += dis;
    }

    @Override
    public String toString() {
        return "VehicleSession{" +
                "id=" + id +
                ", lastLat=" + lastLat +
                ", lastLon=" + lastLon +
                ", distance=" + distance +
                '}';
    }
}
