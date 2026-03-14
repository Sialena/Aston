package input_strategies;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Bus;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class FileInputStrategy implements InputStrategyInterface {
    private final ObjectMapper objectMapper;
    private String filepath;

    public FileInputStrategy() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Stream<Bus> busWrite() {
        File file = new File(filepath);
        try {
            List<Bus> buses = objectMapper.readValue(file, new TypeReference<>() {});
            return buses.stream();
        }
        catch (IOException e) {
            throw new RuntimeException("Can't read from this file " + filepath + '!', e);
        }
    }

    @Override
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void setBusNumber(String busNumber) {}

    @Override
    public void setModel(String model) {}

    @Override
    public void setMileage(int mileage) {}

    @Override
    public void busBuilderReset() {}
}
