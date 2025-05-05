package proyecto;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GPSAnalyzer {

    public static Map<String, Double> calcularVelocidadMedia(List<GPSData> dataList) {
        Map<String, Double> sumaVelocidad = new HashMap<>();
        Map<String, Integer> conteo = new HashMap<>();

        for (GPSData data : dataList) {
            String busId = data.getBusId();
            double speed = data.getSpeed();

            sumaVelocidad.put(busId, sumaVelocidad.getOrDefault(busId, 0.0) + speed);
            conteo.put(busId, conteo.getOrDefault(busId, 0) + 1);
        }

        Map<String, Double> velocidadMedia = new HashMap<>();
        for (String busId : sumaVelocidad.keySet()) {
            double media = sumaVelocidad.get(busId) / conteo.get(busId);
            velocidadMedia.put(busId, media);
        }

        return velocidadMedia;
    }
}
