package input_strategies;

import entity.Bus;
import entity.Models;
import validators.BusMileageValidator;
import validators.BusModelValidator;
import validators.BusNumberValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualInputStrategy implements  InputStrategyInterface {
    private String busNumber;
    private Models model;
    private int mileage;
    private boolean endInput = true;
    BusNumberValidator busNumberValidator = new BusNumberValidator();
    BusModelValidator busModelValidator = new BusModelValidator();
    BusMileageValidator busMileageValidator = new BusMileageValidator();
    Bus.Builder busBuilder = new Bus.Builder();

    @Override
    public List<Bus> write() {
        List<Bus> busList = new ArrayList<>();
        while (!endInput) {
            System.out.println("=".repeat(50) + '\n' +
                    "To create a new object, type the info requested below.\n");
            System.out.println("Enter the bus number, consisting of 2 numbers, a dash symbol and 4 digits (example: AD-3412):\n");
            busNumber = busNumberValidator.validateBusNumber();
            System.out.println("Enter the bus model from the selected list:\n");
            int modelNumber = 1;
            for (Models model : Models.values()) {
                System.out.print('\n' + modelNumber + ". " + model);
                ++modelNumber;
            }
            model = busModelValidator.validateBusModel();
            System.out.println("Enter the mileage number for the bus:\n");
            mileage = busMileageValidator.validateMileage();
            if (!busNumber.isEmpty() && model != null && mileage > 0) {
                busBuilder.busNumber(this.busNumber);
                busBuilder.model(this.model);
                busBuilder.mileage(this.mileage);
                busList.add(busBuilder.build());
            }
            else throw new IllegalArgumentException("ERROR: Failed to create a bus object! Check your input parameters!\n");
            System.out.println("Bus object has been created!\n" +
                    "Do you want to create another bus object? Y/N\n");
            Scanner inputScanner = new Scanner(System.in);
            char input;
            while(true) {
                input = inputScanner.next().charAt(0);
                if(input == 'Y' || input == 'y') {
                    System.out.println("Preparing to create another bus...\n");
                    endInput = true;
                }
                if (input == 'N' || input == 'n') {
                    System.out.println("Exiting current input session...\n");
                    endInput = false;
                }
                else System.out.println("ERROR: Wrong input! Please, choose Y to continue, or N to exit!\n");
            }
        }
        return busList;
    }
}
