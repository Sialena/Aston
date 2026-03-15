package validators;

import entity.Bus;
import entity.Models;
import java.util.Objects;

public class BusValidator {

    public boolean validateBusNumber(String busNumber) {
        if (busNumber == null || busNumber.trim().isEmpty()) {
            System.out.println("Bus number cannot be empty!");
            return false;
        }
        if (!busNumber.matches("[A-Za-z]{2}-\\d{4}")) {
            System.out.println("Input number doesn't match the example!");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean validateBusModel(int selection) {
        if (selection < 1) {
            System.out.println("Selected number cannot be less than 1!");
            return false;
        }
        if (selection > Models.values().length) {
            System.out.println("Please, select models from 1 to " + Models.values().length + "!");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean validateMileage(int mileage) {
        if (mileage < 0) {
            System.out.println("Mileage cannot be below zero!");
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateModelForCreatedObject(Bus bus) {
        String busModel = bus.getModel();
        for(Models model : Models.values()) {
            if(Objects.equals(busModel, model.getValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean validateBusObject(Bus bus) {
        if (!validateBusNumber(bus.getBusNumber()) || !validateModelForCreatedObject(bus) || !validateMileage(bus.getMileage())) {
            System.out.println("Invalid bus object!");
            return false;
        }
        else return true;
    }
}