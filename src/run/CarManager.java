package run;

import bussiness.config.IOFile;
import bussiness.entity.Car;
import bussiness.entity.Catalog;
import bussiness.util.ConsoleColors;
import bussiness.util.InputMethods;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class CarManager {
    public static void showCarManager() {
        do {
            Menu.carManager();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    showListCar();
                    break;
                case 2:
                    addNewCar();
                    break;
                case 3:
                    updateCar();
                    break;
                case 4:
                    hiddenCar();
                    break;
                case 5:
                    hiddenCars();
                    break;
                case 6:
                    findCarByName();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 7) {
                break;
            }
        } while (true);
    }
    //Show List Car
    public static void showListCar() {
        List<Car> listCars = CarDealer.carService.findAll();
        if (listCars.isEmpty()) {
            System.out.println(ConsoleColors.RED_ITALIC + "There are no cars in the showroom.");
            return;
        }
        System.out.println("----------Car list----------");
        for (Car car : listCars) {
            System.out.printf("ID: %d - Car's name: %s - Brand: %s\n" +
            "Descriptions: %s\n" +
            "Price: $%.2f - Quantity in stock: %d - Created At: %s - Updated At: %s\n" +
            "Status: %s\n",
            car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName() , car.getDesc(),
            car.getUnitPrice(), car.getStock(), car.getCreatedAt().format(CarDealer.formatter), (car.getUpdatedAt() != null ? car.getUpdatedAt().format(CarDealer.formatter) : "Not yet edited"), (car.isStatus()?"Active":"Hidden"));
            System.out.println("----------------------------------------");
        }
    }

    //Add new car
    public static void addNewCar() {
        System.out.print("Enter the quantity of cars you want to bring into the showroom: ");
        int size = InputMethods.getInteger();
        for (int i = 0; i < size; i++) {
            Car car = new Car();
            //ID
            car.setCarId(CarDealer.carService.getNewId());
            System.out.printf("Add a new car with id: %d\n", car.getCarId());
            //Car Name
            System.out.print("\u001B[37mEnter car's name: ");
            String carName = InputMethods.getString();
            car.setCarName(carName);
            //Catalog
            List<Catalog> brands = CarDealer.catalogService.findAll();
            System.out.println("List of car brands currently available in the showroom.");
            for (Catalog brand: brands) {
                System.out.printf("ID: %d - Brand: %s\n", brand.getCatalogId(), brand.getCatalogName() );
            }
            System.out.print("Please enter the brand's ID you want this car to belong to: ");
            long inputId;
            while (true) {
                inputId = InputMethods.getLong();
                if (inputId >0 && inputId <= brands.size()){
                    car.setCatalogId(inputId);
                    break;
                } else {
                    System.out.println("Please enter ID of the car available in the showroom.");
                }
            }
            //Descriptions
            System.out.print("\u001B[37mEnter descriptions: ");
            String desc = InputMethods.getString();
            car.setDesc(desc);
            //Price
            System.out.print("\u001B[37mEnter car's price: ");
            double price = InputMethods.getDouble();
            car.setUnitPrice(price);
            //Stock
            System.out.print("\u001B[37mEnter car's quantity in stock: ");
            int stock = InputMethods.getInteger();
            car.setStock(stock);
            //Created At
            car.setCreatedAt(LocalDateTime.now());
            //Status
            car.setStatus(true);
            CarDealer.carService.save(car);
            System.out.printf("%s added successfully.\n", car.getCarName());
        }
    }



    //Update car
    public static void updateCar() {
        System.out.print("Enter car's id: ");
        long inputId = InputMethods.getLong();
        Car car = CarDealer.carService.findById(inputId);
        if (car == null) {
            while (true) {
                System.out.printf("No car with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    updateCar();
                } else {
                    return;
                }
            }
        }
        //Car Name
        System.out.print("\u001B[37mEnter car's name: ");
        String carName = InputMethods.getString();
        car.setCarName(carName);
        //Catalog
        List<Catalog> brands = CarDealer.catalogService.findAll();
        System.out.println("List of car brands currently available in the showroom.");
        for (Catalog brand: brands) {
            System.out.printf("ID: %d - Brand: %s\n", brand.getCatalogId(), brand.getCatalogName() );
        }
        System.out.print("Enter car's ID you want to update information: ");
        while (true) {
            long id = InputMethods.getLong();
            if (id >0 && id < brands.size()){
                car.setCatalogId(id);
                break;
            } else {
                System.out.println("Please enter ID of the car available in the showroom.");
            }
        }
        //Descriptions
        System.out.print("\u001B[37mEnter descriptions: ");
        String desc = InputMethods.getString();
        car.setDesc(desc);
        //Price
        System.out.print("\u001B[37mEnter car's price: ");
        double price = InputMethods.getDouble();
        car.setUnitPrice(price);
        //Stock
        System.out.print("\u001B[37mEnter car's quantity in stock: ");
        int stock = InputMethods.getInteger();
        car.setStock(stock);
        //Update At
        LocalDateTime newDate = LocalDateTime.now();
        car.setUpdatedAt(newDate);
        //Status
        System.out.printf("Car is %s, Do you want to change it?(Y/N)\n", (car.isStatus()?"actice": "hidden"));
        char key = InputMethods.getChar();
        if (key == 'Y') {
            car.setStatus(!car.isStatus());
        }
        CarDealer.carService.save(car);
        System.out.printf("%s updated successfully.\n", car.getCarName());

    }

    // Change status car
    public static void hiddenCar() {
        System.out.print("Enter car's id: ");
        long inputId = InputMethods.getLong();
        Car car = CarDealer.carService.findById(inputId);
        if (car == null) {
            while (true) {
                System.out.printf("No car with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    hiddenCar();
                } else {
                    break;
                }
            }
        }
        if (!car.isStatus()) {
            System.out.println("This car has been hidden.");
            return;
        }
        //Update status
        car.setStatus(false);
        //Update time
        LocalDateTime newDate = LocalDateTime.now();
        car.setUpdatedAt(newDate);
        CarDealer.carService.save(car);
        System.out.printf("Car with ID %d has been hidden.\n", inputId);
    }

    // Change status cars
    public static void hiddenCars() {
        System.out.print("Enter list car's id: ");
        String inputListId = InputMethods.getString();
        if (Pattern.matches("\\d+(,\\d+)*", inputListId)) {
            String[] listId = inputListId.split(",");
            for (String id: listId) {
                long inputId = Long.parseLong(id);
                if (CarDealer.carService.findById(inputId) != null) {
                    if (!CarDealer.carService.findById(inputId).isStatus()) {
                        System.out.printf("This car with id %d has been hidden.\n", inputId);
                        continue;
                    }
                    CarDealer.carService.findById(inputId).setStatus(false);
                    //Update time
                    LocalDateTime newDate = LocalDateTime.now();
                    CarDealer.carService.findById(inputId).setUpdatedAt(newDate);
                    System.out.printf("Car with ID %d has been hidden.\n", inputId);
                }
                else {
                    System.out.printf("Car with ID %d is not exist.\n", inputId);
                }
            }
        } else {
            System.out.println("Invalid format, please re-enter.");
        }
        IOFile.writeToFile(IOFile.CAR_PATH, CarDealer.carService.findAll());
    }

    // Find User By Name
    public static void findCarByName() {
        System.out.print("Enter name: ");
        String inputName = InputMethods.getString().trim();

        List<Car> list = CarDealer.carService.findCarByNameForAdmin(inputName);
        if (list.isEmpty()) {
            System.out.printf("No search results for the word '%s'.\n", inputName);
            return;
        }
        System.out.printf("----------Search result for '%s': ----------\n", inputName);
        for (Car car : list) {
            System.out.printf("ID: %d - Car's name: %s - Brand: %s\n" +
                            "Descriptions: %s\n" +
                            "Price: %.2f - Quantity in stock: %d - Created At: %s - Updated At: %s\n" +
                            "Status: %s\n",
                    car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName() , car.getDesc(),
                    car.getUnitPrice(), car.getStock(), car.getCreatedAt().format(CarDealer.formatter), (car.getUpdatedAt() != null ? car.getUpdatedAt().format(CarDealer.formatter) : "Not yet edited"), (car.isStatus()?"Active":"Hidden"));
            System.out.println("----------------------------------------");
        }
    }
}

//Car detail information
//System.out.println("----------Car list----------");
//        for (Car car : listCars) {
//        System.out.printf("ID: %d - Car's name: %s - Catalog: %s\n" +
//        "Descriptions: %s\n" +
//        "Price: %.2f - Quantity in stock: %d - Created At: %s - Updated At: %s\n" +
//        "Status: %s\n",
//        car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName() , car.getDesc(),
//        car.getUnitPrice(), car.getStock(), car.getCreatedAt().format(CarDealer.formatter), (car.getUpdatedAt() != null ? car.getUpdatedAt().format(CarDealer.formatter) : "Not yet edited"), (car.isStatus()?"Active":"Hidden"));
//        System.out.println("----------------------------------------");
//        }

//Car information
//System.out.println("----------Car list----------");
//        for (Car car : listCars) {
//        System.out.printf("ID: %d - Car's name: %s - Brand: %s\n" +
//        "Price: %.2f\n" +
//        car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName(),
//        car.getUnitPrice());
//        System.out.println("----------------------------------------");
//        }