package input_strategies;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Bus;
import java.io.File;
import java.io.IOException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FileInputStrategy implements InputStrategyInterface {
    private final ObjectMapper objectMapper;
    private String filepath;

    public FileInputStrategy() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Stream<Bus> write() {
        File file = new File(filepath);
        try (MappingIterator<Bus> iterator = objectMapper.readerFor(Bus.class).readValues(file)) {
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
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
