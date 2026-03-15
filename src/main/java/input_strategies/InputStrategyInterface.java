package input_strategies;

import entity.Bus;
import java.util.stream.Stream;

public interface InputStrategyInterface {
    Stream<Bus> busWrite();
    void setBusNumber(String busNumber);
    void setModel(String model);
    void setMileage(int mileage);
    void busBuilderReset();
    void setFilepath(String filepath);
}
