package proyecto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DataFilter {

    public static List<GPSData> filterByBusAndTime(List<GPSData> dataList, String busId, LocalDateTime start, LocalDateTime end) {
        return dataList.stream()
                .filter(data -> data.getBusId().equals(busId))
                .filter(data -> {
                    LocalDateTime ts = LocalDateTime.parse(data.getTimestamp());
                    return !ts.isBefore(start) && !ts.isAfter(end);
                })
                .collect(Collectors.toList());
    }
}

