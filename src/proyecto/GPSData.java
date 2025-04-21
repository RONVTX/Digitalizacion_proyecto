package proyecto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GPSData {
    private String busId;
    private String timestamp;
    private double latitude;
    private double longitude;
    private double speed;

    public GPSData(String busId, LocalDateTime timestamp, double latitude, double longitude, double speed) {
        this.busId = busId;
        this.timestamp = timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }

    public String toCSV() {
        return String.join(",", busId, timestamp, String.valueOf(latitude), String.valueOf(longitude), String.valueOf(speed));
    }

    // Getters
    public String getBusId() { return busId; }
    public String getTimestamp() { return timestamp; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public double getSpeed() { return speed; }
}
