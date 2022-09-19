package uz.epam.kafkastream.task1.dto;

public class TaxiCoordinate {
    private Long taxiId;
    private Double latitude;
    private Double longitude;

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "TaxiCoordinate{" +
                "taxiId=" + taxiId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
