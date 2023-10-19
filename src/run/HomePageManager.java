package run;

import bussiness.entity.Car;
import bussiness.entity.CartItem;
import bussiness.entity.Catalog;
import bussiness.util.InputMethods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class HomePageManager {
    public static void showHomePage() {
        do {
            Menu.homePage();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    findCarByName();
                    break;
                case 2:
                    showHotCar();
                    break;
                case 3:
                    showAllCar();
                    break;
                case 4:
                    showCarDetail();
                    break;
                case 5:
                    addToCart();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 5) {
                break;
            }
        } while (true);
    }


    // Find Car By Name
    public static void findCarByName() {
        System.out.print("Enter car's name: ");
        String inputName = InputMethods.getString().trim();

        List<Car> list = CarDealer.carService.findCarByNameForUser(inputName);
        if (list.isEmpty()) {
            System.out.printf("No search results for the word '%s'.\n", inputName);
            return;
        }
        System.out.printf("----------Search result for '%s': ----------\n", inputName);
        for (Car car : list) {
            System.out.printf("ID: %d - Car's name: %s - Brand: %s\n" +
            "Price: %.2f\n" +
            car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName(),
            car.getUnitPrice());
            System.out.println("----------------------------------------");
        }
    }
    //Show List Car
    public static void showHotCar() {
        List<Car> list = CarDealer.carService.getHotCar();
        Month month = LocalDate.now().getMonth();
        System.out.println(month.getDisplayName(TextStyle.FULL, Locale.US));
        if (list.isEmpty()) {
            System.out.println("There are no cars in the showroom.");
            return;
        }
        System.out.printf("----------Featured car models in %s----------\n", month);
        for (Car car : list) {
            System.out.printf("ID: %d - Car's name: %s - Brand: %s\n" +
            "Price: %.2f\n" +
            car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName(),
            car.getUnitPrice());
            System.out.println("----------------------------------------");
        }
    }

    //Add new car
    public static void showAllCar() {
        List<Car> list = CarDealer.carService.findAllForUser();
        if (list.isEmpty()) {
            System.out.println("There are no cars in the showroom.");
            return;
        }
        System.out.println("----------THS's car list----------");
        for (Car car : list) {
            System.out.printf("ID: %d - Car's name: %s - Brand: %s\n" +
            "Price: %.2f\n" +
            car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName(),
            car.getUnitPrice());
            System.out.println("----------------------------------------");
        }
    }
    //Show car detail
    public static void showCarDetail() {
        boolean isExist = true;
        System.out.print("Enter ID of the car you want to see detail: ");
        long inputId = InputMethods.getLong();
        Car car = CarDealer.carService.findByIdForUser(inputId);
        if (car == null) {
            while (isExist) {
                System.out.printf("No car with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    showCarDetail();
                } else {
                    return;
                }
            }
        }
        System.out.printf("----------%s----------\n", car.getCarName());
        System.out.printf("ID: %d - Car's name: %s - Brand: %s\n" +
                        "Descriptions: %s\n" +
                        "Price: %.2f - Quantity in stock: %d\n",
                car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName() , car.getDesc(),
                car.getUnitPrice(), car.getStock());
        System.out.println("----------------------------------------");
        System.out.printf("Do you want add this car to add the shopping list?(Y/N)\n", inputId);
        char key = InputMethods.getChar();
        if (key == 'Y') {
            int inputQuantity = getInputQuantity(inputId);
            CarDealer.cartService.addCarToListCart(inputId, inputQuantity);
        }
    }

    //Add to cart
    public static void addToCart() {
        boolean isExist = true;
        System.out.print("Enter ID of the car you want to add to the shopping list: ");
        long inputId = InputMethods.getLong();
        if (CarDealer.carService.findByIdForUser(inputId) == null) {
            while (isExist) {
                System.out.printf("No car with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    addToCart();
                } else {
                    return;
                }
            }
        }
        System.out.print("Enter the quantity of cars you want to purchase: ");
        int inputQuantity = getInputQuantity(inputId);
        CarDealer.cartService.addCarToListCart(inputId, inputQuantity);
    }

    private static int getInputQuantity(long inputId) {
        char key;
        System.out.print("Enter the quantity of cars you want to purchase: ");
        int inputQuantity = InputMethods.getInteger();
        if (inputQuantity + CarDealer.cartService.findById(inputId).getQuantity() > CarDealer.carService.findById(inputId).getStock()) {
            System.out.print("The quantity of cars exceeds the available stock, do you want to deplete it?(Y/N)");
            key = InputMethods.getChar();
            if (key == 'Y') {
                inputQuantity = CarDealer.carService.findById(inputId).getStock() - CarDealer.cartService.findById(inputId).getQuantity();
            } else {
                while (true) {
                    System.out.print("Please re-enter the quantity of cars you want to purchase: ");
                    inputQuantity = InputMethods.getInteger();
                    if (inputQuantity + CarDealer.cartService.findById(inputId).getQuantity() <= CarDealer.carService.findById(inputId).getStock()) {
                        break;
                    } else {
                        System.out.println("The quantity you want to purchase exceeds the stock quantity, please re-enter.");
                    }
                }
            }
        }
        return inputQuantity;
    }

}
