import entity.Bus;
import entity.Models;
import input_strategies.*;
import output.WriteToJson;
import validators.BusValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Service {
    private final StrategySelector strategySelector;
    private final BusValidator busValidator;
    private final InputStrategyInterface manualInputStrategy;
    private final InputStrategyInterface randomInputStrategy;
    private final InputStrategyInterface fileInputStrategy;
    private final WriteToJson jsonWriter;

    public Service() {
        strategySelector = new StrategySelector();
        busValidator = new BusValidator();
        manualInputStrategy = new ManualInputStrategy();
        randomInputStrategy = new RandomInputStrategy();
        fileInputStrategy = new FileInputStrategy();
        jsonWriter = new WriteToJson();
    }

    public List<Bus> busListManualInput(int busCount) {
        List<Bus> busList = new ArrayList<>();
        strategySelector.setStrategy(manualInputStrategy);
        Scanner inputScanner = new Scanner(System.in);
        for (int step = 1; step <= busCount; ++step) {
            System.out.println("Please, enter bus number, consisting of 2 letters, a dash symbol and 4 numbers (EXAMPLE: XX-1234):\n");
            while(true) {
                String busNumber = inputScanner.next();
                if (busValidator.validateBusNumber(busNumber)) {
                    manualInputStrategy.setBusNumber(busNumber);
                    break;
                }
            }
            System.out.println("Please select bus model from the list below:\n");
            for (int listStep = 1; listStep <= Models.values().length; ++listStep) {
                for(Models model : Models.values()) {
                    System.out.println(listStep + " - " + model.getValue());
                }
                System.out.println('\n');
            }
            while(true) {
                int selectedBusModel = inputScanner.nextInt();
                if (busValidator.validateBusModel(selectedBusModel)) {
                    manualInputStrategy.setModel(Models.values()[selectedBusModel].getValue());
                    break;
                }
            }
            System.out.println("Please, assign a mileage from 0 to 500000:\n");
            while(true) {
                int mileage = inputScanner.nextInt();
                if (busValidator.validateMileage(mileage)) {
                    manualInputStrategy.setMileage(mileage);
                    break;
                }
            }
            Stream<Bus> busStream = strategySelector.getBuses();
            busStream.forEach(busList::add);
            manualInputStrategy.busBuilderReset();
        }
        return busList;
    }

    public List<Bus> busListRandomInput(int busCount) {
        List<Bus> busList = new ArrayList<>();
        strategySelector.setStrategy(randomInputStrategy);
        for (int step = 1; step <= busCount; ++step) {
            Stream<Bus> busStream = strategySelector.getBuses();
            busStream.filter(busValidator::validateBusObject).forEach(busList::add);
            randomInputStrategy.busBuilderReset();
        }
        return busList;
    }

    public List<Bus> busListFileInput(String filepath) {
        List<Bus> busList = new ArrayList<>();
        strategySelector.setStrategy(fileInputStrategy);
        fileInputStrategy.setFilepath(filepath);
        Stream<Bus> busStream = strategySelector.getBuses();
        busStream.filter(busValidator::validateBusObject).forEach(busList::add);
        return busList;
    }

    public CustomArrayList<Bus> listToCustomArrayList(List<Bus> busList) {
        CustomArrayList<Bus> busCustomList;
        busCustomList = busList.stream().collect(Collectors.toCollection(CustomArrayList::new));
        return busCustomList;
    }

    public void writeToJson(String filepath, List<Bus> busList) {
        jsonWriter.writeToJson(filepath, busList);
    }
}
