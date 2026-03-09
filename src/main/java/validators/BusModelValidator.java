package validators;

import entity.Models;
import java.util.Scanner;

public class BusModelValidator {

    public Models validateBusModel() {
        Scanner inputScanner = new Scanner(System.in);
        int selectedModel;
        Models[] models = Models.values();
        while(true) {
            selectedModel = inputScanner.nextInt();
            if (selectedModel <= 0) System.out.println("Selected number cannot be 0 or below!\n");
            if (selectedModel > models.length) System.out.println("Selected number out of choosing range!\n");
            else {
                inputScanner.close();
                return (models[selectedModel - 1]);
            }
        }
    }
}
