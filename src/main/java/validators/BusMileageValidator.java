package validators;

import java.util.Scanner;

public class BusMileageValidator {

    public int validateMileage() {
        Scanner inputScanner = new Scanner(System.in);
        while(true) {
            if (!inputScanner.hasNextInt()) {
                System.out.println("ERROR: Not a number!\n");
                inputScanner.next();
            }
            else {
                int mileage = inputScanner.nextInt();
                if (mileage < 0) System.out.println("Mileage cannot be below zero!\n");
                else {
                    inputScanner.close();
                    return mileage;
                }
            }
        }
    }
}
