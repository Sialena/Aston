package input_strategies;

import entity.Bus;
import entity.Models;
import java.util.Random;

public class RandomInputStrategy implements InputStrategyInterface {
    private String busNumber;
    private String model;
    private int mileage;
    Bus.Builder busBuilder = new Bus.Builder();

    @Override
    public Bus write() {
        Random random = new Random();
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

        return busBuilder.build();
    }
}
