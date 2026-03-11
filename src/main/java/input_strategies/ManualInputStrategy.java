package input_strategies;

import entity.Bus;
import java.util.stream.Stream;

public class ManualInputStrategy implements  InputStrategyInterface {
    private String busNumber;
    private String model;
    private int mileage;
    Bus.Builder busBuilder = new Bus.Builder();

    @Override
    public Stream<Bus> write() {
        busBuilder.busNumber(this.busNumber);
        busBuilder.model(this.model);
        busBuilder.mileage(this.mileage);
        return Stream.of(busBuilder.build());
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
