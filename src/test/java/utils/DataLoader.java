package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class DataLoader {

    private static final String DATA_PATH = "src/main/resources/Data/Weatherdata.json";

    public static Map<String, String> loadTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(Paths.get(DATA_PATH).toFile(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load test data from " + DATA_PATH, e);
        }
    }
}
