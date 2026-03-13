package input_strategies;

import entity.Bus;
import java.util.stream.Stream;

public class ManualInputStrategy implements  InputStrategyInterface {
    private String busNumber;
    private String model;
    private int mileage;
    private final Bus.Builder busBuilder;

    public ManualInputStrategy() {
        busBuilder = new Bus.Builder();
    }

    @Override
    public Stream<Bus> write() {
        busBuilder.busNumber(this.busNumber);
        busBuilder.model(this.model);
        busBuilder.mileage(this.mileage);
        return Stream.of(busBuilder.build());
    }

    @Override
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public void busBuilderReset() {
        busNumber = null;
        model = null;
        mileage = -1;
    }

    @Override
    public void setFilepath(String filepath) {}
}
