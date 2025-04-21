package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GPSDataProcessor {

    public static ArrayList<GPSData> loadFromCSV(String filePath) {
        ArrayList<GPSData> dataList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Validación
                if (parts.length != 5) continue;

                String busId = parts[0];
                String timestamp = parts[1];
                double latitude = Double.parseDouble(parts[2]);
                double longitude = Double.parseDouble(parts[3]);
                double speed = Double.parseDouble(parts[4]);


                if (latitude < -90 || latitude > 90) continue;
                if (longitude < -180 || longitude > 180) continue;
                if (speed < 0 || speed > 150) continue;
                // Verifica el formato
                LocalDateTime.parse(timestamp, formatter);
                dataList.add(new GPSData(busId, LocalDateTime.parse(timestamp, formatter), latitude, longitude, speed));
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return dataList;
    }
}

