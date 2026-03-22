import entity.Bus;
import entity.SortField;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import service.Service;
import sorting.BusSortingStrategy;
import sorting.Sorter;
import sorting.StrategyFactory;
import sorting.StrategyType;

public abstract class MenuEntryExemple {
    private String title;

    public MenuEntryExemple(String title) {
        this.title = title;
    }

    // чтение
    public String getTitle() {
        return title;
    }

    // изменяем
    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void run();

    private static int readCount(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
              //  int count = readCount("Сколько автобусов ввести? ", scanner);
                int count = Integer.parseInt(input);
                if (count <= 0) {
                    System.out.println("\nThe quantity must be a positive number."); //Количество должно быть положительным числом
                } else if (count > 50) {
                    System.out.println("\nThe quantity cannot exceed 50. Please enter a number between 1 and 50.");
                } else {
                    return count;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter an integer."); //Ошибка: введите целое число
            }   
        }
    }

    private static void handleDataMenu(List<Bus> buses, Scanner scanner, Service service) {
        while(true) {
        System.out.println("\nWhat to do with the data?"); //  Что сделать с данными?
        System.out.println("\n1. Show list"); // Показать список
        System.out.println("\n2. Sort"); // Сортировать
        System.out.println("\n3. Save to file"); // Сохранить в файл
        System.out.println("\n4. Count occurrences of an element"); // Подсчитать вхождения элемента
        System.out.println("\n5. Return to the main menu"); // Вернуться в главное меню
        System.out.print("\nYour choice: "); // Ваш выбор:

        String input = scanner.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\nError: Please enter a number between 1 and 5."); // Ошибка: введите число от 1 до 5.
            continue;
        }

        switch (choice) {
            case 1:
                System.out.println("\nList of buses:"); // List of buses: Список автобусов:
                for (int i = 0; i < buses.size(); i++) {
                    System.out.println((i + 1) + ". " + buses.get(i));
                }
                break;
            case 2:
                System.out.println("\nSelect a field to sort by:");//Выберите поле для сортировки:
                System.out.println("\n1. Number"); // Номер
                System.out.println("\n2. Model"); // Модель
                System.out.println("\n3. Mileage"); // Пробег
                int fieldChoice;
                while (true) {
                    String fieldInput = scanner.nextLine();
                    if (fieldInput.isEmpty()) {
                        System.out.println("\nThe input cannot be empty. Please select the field again:"); // Ввод не может быть пустым. Повторите выбор поля:
                        continue;
                    }
                    try {
                        fieldChoice = Integer.parseInt(fieldInput);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\nError: Please enter a number between 1 and 3."); // Ошибка: введите число от 1 до 3.
                    }
                }
                SortField selectedField = null;
                switch (fieldChoice) {
                    case 1: selectedField = SortField.NUMBER; break;
                    case 2: selectedField = SortField.MODEL; break;
                    case 3: selectedField = SortField.MILEAGE; break;
                    default:
                        System.out.println("\nInvalid field selection. Cancel sorting."); //Неверный выбор поля. Отмена сортировки.
                        break;
                }
                if (selectedField == null) {
                    break; // в меню данных
                }

                System.out.println("\nSelect sorting algorithm:"); //Выберите алгоритм сортировки:
                System.out.println("\n1. Bubble"); //Пузырьковая
                System.out.println("\n2. Fast"); //Быстрая
                System.out.println("\n3. Inserts"); //Вставками
                System.out.println("\n4. By the parity of the mileage"); //По чётности пробега
                int algoChoice;
                while (true) {
                    String algoInput = scanner.nextLine();
                    if (algoInput.isEmpty()) {
                        System.out.println("\nThe input cannot be empty. Please repeat the sort selection:"); //Ввод не может быть пустым. Повторите выбор сортировки:
                        continue;
                    }
                    try {
                        algoChoice = Integer.parseInt(algoInput);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\nError: Please enter a number between 1 and 4."); //Ошибка: введите число от 1 до 4.
                    }
                }
                StrategyType algoType = null;
                switch (algoChoice) {
                    case 1: algoType = StrategyType.BUBBLE; break;
                    case 2: algoType = StrategyType.QUICK; break;
                    case 3: algoType = StrategyType.INSERTION; break;
                    case 4: algoType = StrategyType.EVEN_ODD; break;
                    default:
                        System.out.println("\nIncorrect algorithm selection. Sorting cancelled."); //Неверный выбор алгоритма. Отмена сортировки.
                        break;
                }
                if (selectedField == null) {
                    break; // возврат в меню данных
                }


                //  фабрика
                StrategyFactory factory = new StrategyFactory();
                BusSortingStrategy strategy = factory.createBusStrategy(algoType);

                // Получаем компаратор
                Comparator<Bus> comparator;
                switch (selectedField) {
                    case NUMBER:
                        comparator = Comparator.comparing(Bus::getBusNumber); 
                        break;
                    case MODEL:
                        comparator = Comparator.comparing(Bus::getModel);
                        break;
                    case MILEAGE:
                        comparator = Comparator.comparingInt(Bus::getMileage);
                        break;
                    default:
                        comparator = Comparator.naturalOrder(); 
                }


                Sorter sorter = new Sorter();
                sorter.setStrategy(strategy);
                sorter.sort(buses, comparator);


                System.out.println("\nList after sorting:"); // Список после сортировки:
                for (Bus bus : buses) {
                    System.out.println(bus);
                }
                break;
            case 3:
                service.writeToJson(buses);
                System.out.println("\nData is stored in JSONL."); //Данные сохранены в JSONL.
                break;
            case 4:
                // Подсчёт вхождений
                System.out.println("\nSelect a field to search:"); // Выберите поле для поиска:
                System.out.println("\n1. Number");//Номер
                System.out.println("\n2. Model");//Модель
                System.out.println("\n3. Mileage");//Пробег
                int searchFieldChoice;
                while (true) {
                    String fieldInput = scanner.nextLine();
                    if (fieldInput.isEmpty()) {
                        System.out.println("\nThe input cannot be empty. Please select the field again:"); //Ввод не может быть пустым. Повторите выбор поля:
                        continue;
                    }
                    try {
                        searchFieldChoice = Integer.parseInt(fieldInput);
                        if (searchFieldChoice < 1 || searchFieldChoice > 3) {
                            System.out.println("\nThe number must be between 1 and 3. Please re-enter:"); //Число должно быть от 1 до 3. Повторите ввод:
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\nError: Please enter an integer between 1 and 3.");//Ошибка: введите целое число от 1 до 3.
                    }
                }
                boolean found = false;
                while (!found) {
                    Object searchValue = null;
                    Function<Bus, Object> extractor = null;

                    switch (searchFieldChoice) {
                        case 1: // Номер
                            System.out.print("\nEnter bus number (or press Enter to cancel): ");
                            String numberInput = scanner.nextLine().trim();
                            if (numberInput.isEmpty()) {
                                System.out.println("\nOperation cancelled.");
                                found = true;
                                break;
                            }
                            searchValue = numberInput;
                            extractor = Bus::getBusNumber;
                            break;
                        case 2: // Модель
                            System.out.print("\nEnter bus model (or press Enter to cancel): ");
                            String modelInput = scanner.nextLine().trim();
                            if (modelInput.isEmpty()) {
                                System.out.println("\nOperation cancelled.");
                                found = true;
                                break;
                            }
                            searchValue = modelInput;
                            extractor = Bus::getModel;
                            break;
                        case 3: // Пробег
                            System.out.print("\nEnter mileage (integer, or press Enter to cancel): ");
                            String mileageInput = scanner.nextLine().trim();
                            if (mileageInput.isEmpty()) {
                                System.out.println("\nOperation cancelled.");
                                found = true;
                                break;
                            }
                            try {
                                int mileage = Integer.parseInt(mileageInput);
                                if (mileage < 0) {
                                    System.out.println("\nMileage must be non-negative. Try again.");
                                    continue;
                                }
                                searchValue = mileage;
                                extractor = Bus::getMileage;
                            } catch (NumberFormatException e) {
                                System.out.println("\nError: Please enter an integer. Try again.");
                                continue;
                            }
                            break;
                    }

                    if (extractor != null && searchValue != null) {
                        int count = ParallelCounter.countByField(buses, extractor, searchValue);
                        if (count == 0) {
                            System.out.println("\nNo buses found with the given " +
                                    (searchFieldChoice == 1 ? "number" : searchFieldChoice == 2 ? "model" : "mileage") +
                                    " '" + searchValue + "'.");
                            System.out.println("\nHere is the current list of buses:");
                            for (int i = 0; i < buses.size(); i++) {
                                System.out.println((i + 1) + ". " + buses.get(i));
                            }
                            System.out.println("\nPlease try again.");
                        } else {
                            System.out.println("\nNumber of buses with " +
                                    (searchFieldChoice == 1 ? "number" : searchFieldChoice == 2 ? "model" : "mileage") +
                                    " '" + searchValue + "': " + count);
                            found = true;
                        }
                    } else if (searchValue == null) {
                        // отменили ввод, выходим из цикла
                        found = true;
                    }
                }
                break;
            case 5:
                System.out.println("\nReturn to the main menu.");//Возврат в главное меню.
                return;
            default:
                System.out.println("\nInvalid number. Try again."); //Неверный номер. Попробуйте снова.
        }
    }
}


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Service service = new Service();

        while (true) {
            System.out.println("\nWelcome! Please select a method for filling out your data.:"); //Добро пожаловать! Выберите способ заполнения данных.
            System.out.println("\n1. Manual input."); 
            System.out.println("\n2. Reading a file."); 
            System.out.println("\n3. Random generation."); 
            System.out.println("\n4. Exit."); 
            System.out.print("\nYour choice: "); // Ваш выбор

        
        String input = scanner.nextLine();
        int choice;
        try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nError: Please enter a number."); 
                continue;
            }
        if (choice == 4) {
                System.out.println("\nThe program is completed."); //Программа завершена.
                break; 
            }
        MenuEntryExemple entry = null;
            switch (choice) {
                case 1:
                    entry = new MenuEntryExemple("\nManual input.") { 
                        @Override
                        public void run() {
                            System.out.println("\nYou have selected manual input."); // Вы выбрали ручной ввод
                            int count = readCount("\nHow many buses should I enter? Enter a value from 1 to 50.", scanner);
                            System.out.println("\nSelect your next steps."); // Выберете дальнейшие действия.
                            List<Bus> buses = service.busListManualInput(count, scanner);
                            buses = service.listToCustomArrayList(buses); 
                            handleDataMenu(buses, scanner, service);
                        }
                    };
                    break;
                case 2:
                    entry = new MenuEntryExemple("\n2. Reading a file.") {
                        @Override
                        public void run() {
                            System.out.println("\nYou have selected reading a file.");
                            String filepath;
                            File file;
                            while (true) { 
                                System.out.print("\nEnter the path to the file: "); //Введите путь к файлу:
                                filepath = scanner.nextLine().trim();
                                if (filepath.isEmpty()) {
                                    System.out.println("\nThe path cannot be empty. Please try again."); //Путь не может быть пустым
                                    continue;
                                }
                                file = new File(filepath);
                                if (!file.exists()) {
                                    System.out.println("\nThe file does not exist. Try again."); //Файл не существует. Попробуйте снова.
                                } else if (file.isDirectory()) {
                                    System.out.println("\nThe specified path is a folder, not a file. Try again."); //Указанный путь является папкой, а не файлом. Попробуйте снова.
                                } else {
                                    break;
                                }
                            }
                            List<Bus> buses;
                            try {
                                buses = service.busListFileInput(filepath);
                            } catch (RuntimeException e) {
                                System.out.println("\nError reading file: " + e.getMessage());
                                return;
                            }
                            if (buses.isEmpty()) {
                                System.out.println("\nThere are no valid bus objects in the file."); // В файле нет корректных объектов автобусов.
                                return;
                            }
                            buses = service.listToCustomArrayList(buses);
                            System.out.println("\nNumber of buses loaded: " + buses.size() + ".");
                            handleDataMenu(buses, scanner, service);
                        }
                    };
                    break;
                case 3:
                    entry = new MenuEntryExemple("\nRandom generation.") {
                        @Override
                        public void run() {
                            System.out.println("\nYou have selected generation");
                            int count = readCount("\nHow many buses should be generated? Enter a value from 1 to 50.", scanner); // сколько автобусов сгенерировать
                            List<Bus> buses = service.busListRandomInput(count);
                            buses = service.listToCustomArrayList(buses);
                            System.out.println("\nBuses generated: " + buses.size() + "."); 
                            
                            handleDataMenu(buses, scanner, service);
                        }
                    };
                    break;
                default:
                    System.out.println("\nInvalid number. Try again."); 
                    continue;

            }


            if (entry != null) {
                entry.run();
            }
        }
        
        scanner.close();
    }
}