package validators;

import entity.Bus;

public class BusValidator {
    private final String busNumberPattern = "[A-Za-z]{2}-\\d{4}";
    private boolean isValid;

    public boolean validateBusNumber(String busNumber) {
        if (busNumber.isEmpty()) {
            System.out.println("Bus number cannot be empty!\n");
            return isValid = false;
        }
        if (!busNumber.matches(busNumberPattern)) {
            System.out.println("Input number doesn't match the example!\n");
            return isValid = false;
        }
        else {
            return isValid = true;
        }
    }

    public boolean validateBusModel(String busModel) {
        if (busModel.isEmpty()) {
            System.out.println("Bus model cannot be empty!\n");
            return isValid = false;
        }
        else {
            return isValid = true;
        }
    }

    public boolean validateMileage(int mileage) {
        if (mileage < 0) {
            System.out.println("Mileage cannot be below zero!\n");
            return isValid = false;
        }
        else {
            return isValid = true;
        }
    }

    public boolean validateBusObject(Bus bus) {
        if (!validateBusNumber(bus.getBusNumber()) || !validateBusModel(bus.getModel()) || !validateMileage(bus.getMileage())) {
            System.out.println("Invalid bus object!\n");
            return isValid = false;
        }
        else return isValid = true;
    }
}