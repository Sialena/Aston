package input_strategies;

import entity.Bus;
import entity.Models;
import java.util.Random;
import java.util.stream.Stream;

public class RandomInputStrategy implements InputStrategyInterface {
    private String busNumber;
    private String model;
    private int mileage;
    private final Bus.Builder busBuilder;
    private final Random random;

    public RandomInputStrategy() {
        busBuilder = new Bus.Builder();
        random = new Random();
    }

    @Override
    public Stream<Bus> write() {
        char firstChar = (char) (random.nextInt(26) + 'A');
        char secondChar = (char) (random.nextInt(26) + 'A');
        int randomBusNumber = random.nextInt(1000, 9999);
        busNumber = firstChar + secondChar + '-' + String.valueOf(randomBusNumber);
        busBuilder.busNumber(this.busNumber);

        int randomModelSelection = random.nextInt(Models.values().length);
        model = Models.values()[randomModelSelection].getValue();
        busBuilder.model(this.model);

        mileage = random.nextInt(0, 500000);
        busBuilder.mileage(this.mileage);

        return Stream.of(busBuilder.build());
    }

    @Override
    public void busBuilderReset() {
        busNumber = null;
        model = null;
        mileage = -1;
    }

    @Override
    public void setBusNumber(String busNumber) {}

    @Override
    public void setModel(String model) {}

    @Override
    public void setMileage(int mileage) {}

    @Override
    public void setFilepath(String filepath) {}
}
