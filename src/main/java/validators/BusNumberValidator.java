package validators;

import java.util.Scanner;

public class BusNumberValidator {
    private final String busNumberPattern = "[A-Za-z]{2}-\\d{4}";

    public String validateBusNumber() {
        Scanner inputScanner = new Scanner(System.in);
        while(true) {
            String busNumber = inputScanner.nextLine().trim();
            if (busNumber.isEmpty()) System.out.println("Bus number cannot be empty!\n");
            if (!busNumber.matches(busNumberPattern)) System.out.println("Input number doesn't match the example!\n");
            else {
                inputScanner.close();
                return busNumber;
            }
        }
    }
}
