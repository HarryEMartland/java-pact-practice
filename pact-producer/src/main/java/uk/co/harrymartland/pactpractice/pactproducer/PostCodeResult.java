package uk.co.harrymartland.pactpractice.pactproducer;


public class PostCodeResult {
    private String longitude;
    private String latitude;
    private String parish;
    private String country;
    private String region;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getParish() {
        return parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }
}
