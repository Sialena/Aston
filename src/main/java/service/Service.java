package service;

import entity.Bus;
import entity.Models;
import input_strategies.*;
import output.WriteToJson;
import resources.CustomArrayList;
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
            System.out.println("Please, type bus number, consisting of 2 letters, a dash symbol and 4 numbers (EXAMPLE: XX-1234), then press 'enter':");
            while(true) {
                String busNumber = inputScanner.nextLine();
                if (busValidator.validateBusNumber(busNumber)) {
                    manualInputStrategy.setBusNumber(busNumber);
                    break;
                }
            }
            System.out.println("Please select bus model from the list below, then press 'enter':");
            int listNumber = 1;
            for(Models model : Models.values()) {
                System.out.println(listNumber + " - " + model.getValue());
                ++listNumber;
            }
            System.out.println('\n');
            while(true) {
                int selectedBusModel = inputScanner.nextInt();
                if (busValidator.validateBusModel(selectedBusModel)) {
                    manualInputStrategy.setModel(Models.values()[selectedBusModel - 1].getValue());
                    break;
                }
            }
            System.out.println("Please, assign a mileage from 0 to 500000, then press 'enter':");
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
        inputScanner.close();
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

    public void writeToJson(List<Bus> busList) {
        jsonWriter.writeToJson(busList);
    }
}
