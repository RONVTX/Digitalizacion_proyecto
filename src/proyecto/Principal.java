package proyecto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Principal {

        public static void main(String[] args) {
            ArrayList<GPSData> allData = GPSDataProcessor.loadFromCSV("gps_data.csv");


           LocalDateTime inicio = LocalDateTime.of(2025, 3, 25, 8, 10);
            LocalDateTime fin = LocalDateTime.of(2025, 3, 25, 8, 30);

            List<GPSData> filtered = DataFilter.filterByBusAndTime(allData, "BUS01", inicio, fin);

            System.out.println("Registros filtrados:");
            for (GPSData d : filtered) {
                System.out.println(d.toCSV());
            }
        }
}
