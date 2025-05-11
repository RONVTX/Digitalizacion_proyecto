package proyecto;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

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
    public static List<GPSData> detectarParadas(List<GPSData> dataList) {
        return dataList.stream()
                .filter(data -> data.getSpeed() == 0.0)
                .collect(Collectors.toList());
    }
    public static Map<String, Integer> contarParadasPorBus(List<GPSData> dataList) {
        Map<String, Integer> paradasPorBus = new HashMap<>();

        for (GPSData data : dataList) {
            if (data.getSpeed() == 0.0) {
                String busId = data.getBusId();
                paradasPorBus.put(busId, paradasPorBus.getOrDefault(busId, 0) + 1);
            }
        }

        return paradasPorBus;
    }


}
