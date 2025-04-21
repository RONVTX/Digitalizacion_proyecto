package proyecto;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class GPSDataGenerator {
    private static final int DURACION_MINUTOS = 60;
    private static final int NUM_BUSES = 3;
    private static final String[] BUS_IDS = {"BUS01", "BUS02", "BUS03"};

    public static void main(String[] args) {
        ArrayList<GPSData> gpsDataList = new ArrayList<>();
        Random random = new Random();
        LocalDateTime startTime = LocalDateTime.of(2025, 3, 25, 8, 0);

        for (String busId : BUS_IDS) {
            // Coordenadas base
            double lat = 40.0 + random.nextDouble();
            double lon = -3.7 + random.nextDouble();

            for (int i = 0; i < DURACION_MINUTOS; i++) {
                LocalDateTime timestamp = startTime.plusMinutes(i);
                double speed = random.nextDouble() * 60;


                lat += (random.nextDouble() - 0.5) / 100;
                lon += (random.nextDouble() - 0.5) / 100;

                gpsDataList.add(new GPSData(busId, timestamp, lat, lon, speed));
            }
        }

        // Guardar en CSV
        try (FileWriter writer = new FileWriter("gps_data.csv")) {
            writer.write("busId,timestamp,latitude,longitude,speed\n");
            for (GPSData data : gpsDataList) {
                writer.write(data.toCSV() + "\n");
            }
            System.out.println("Archivo gps_data.csv generado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}

