package proyecto;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONExporter {

    public static void exportarUltimaPosicion(List<GPSData> dataList) {
        Map<String, GPSData> ultimaPosicion = new HashMap<>();

        for (GPSData data : dataList) {
            String busId = data.getBusId();
            if (!ultimaPosicion.containsKey(busId) ||
                    LocalDateTime.parse(data.getTimestamp()).isAfter(LocalDateTime.parse(ultimaPosicion.get(busId).getTimestamp()))) {
                ultimaPosicion.put(busId, data);
            }
        }

        for (Map.Entry<String, GPSData> entry : ultimaPosicion.entrySet()) {
            GPSData data = entry.getValue();
            JSONObject json = new JSONObject();
            json.put("busId", data.getBusId());
            json.put("latitude", data.getLatitude());
            json.put("longitude", data.getLongitude());
            json.put("timestamp", data.getTimestamp());

            String fileName = data.getBusId().toLowerCase() + "_status.json";
            try (FileWriter file = new FileWriter(fileName)) {
                file.write(json.toString(4)); // Pretty print
                System.out.println("Exportado: " + fileName);
            } catch (IOException e) {
                System.out.println("Error exportando JSON para " + data.getBusId() + ": " + e.getMessage());
            }
        }
    }
}

