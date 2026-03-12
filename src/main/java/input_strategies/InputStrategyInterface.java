package input_strategies;

import entity.Bus;
import java.util.stream.Stream;

public interface InputStrategyInterface {
    Stream<Bus> write();
    public void setBusNumber(String busNumber);
    public void setModel(String model);
    public void setMileage(int mileage);
    public void busBuilderReset();
    public void setFilepath(String filepath);
}
