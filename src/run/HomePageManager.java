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
        System.out.println("\u001B[34m----------Home Page----------");
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
                    showAllCart();
                    break;
                case 4:
                    addToCart();
                    break;
                case 5:
                    break;
                case 6:
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
            System.out.printf("ID: %d - Car's name: %s - Catalog: %s\n" +
                            "Descriptions: %s\n" +
                            "Price: %.2f - Quantity in stock: %d\n",
                    car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName(), car.getDesc(),
                    car.getUnitPrice(), car.getStock());
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
            System.out.printf("ID: %d - Car's name: %s - Catalog: %s\n" +
                            "Descriptions: %s\n" +
                            "Price: %.2f - Quantity in stock: %d\n",
                    car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName(), car.getDesc(),
                    car.getUnitPrice(), car.getStock());
            System.out.println("----------------------------------------");
        }
    }

    //Add new car
    public static void showAllCart() {
        List<Car> list = CarDealer.carService.findAllForUser();
        if (list.isEmpty()) {
            System.out.println("There are no cars in the showroom.");
            return;
        }
        System.out.println("----------THS's car list----------");
        for (Car car : list) {
            System.out.printf("ID: %d - Car's name: %s - Catalog: %s\n" +
                            "Descriptions: %s\n" +
                            "Price: %.2f - Quantity in stock: %d\n",
                    car.getCarId(), car.getCarName(), CarDealer.catalogService.findById(car.getCatalogId()).getCatalogName(), car.getDesc(),
                    car.getUnitPrice(), car.getStock());
            System.out.println("----------------------------------------");
        }
    }

    //Add to cart
    public static void addToCart() {
        boolean isExist = true;
        System.out.print("Enter ID of the car you want to add to the shopping list: ");
        long inputId = InputMethods.getLong();
        if (CarDealer.carService.findById(inputId) == null) {
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
        int inputQuantity = InputMethods.getInteger();
        if (inputQuantity > CarDealer.carService.findById(inputId).getStock()) {
            System.out.print("The quantity of cars exceeds the available stock, do you want to deplete it?(Y/N)");
            char key = InputMethods.getChar();
            if (key == 'Y') {
                inputQuantity = CarDealer.carService.findById(inputId).getStock();
                CarDealer.carService.findById(inputId).setStock(0);
            } else {
                while (true) {
                    System.out.print("Please re-enter the quantity of cars you want to purchase: ");
                    inputQuantity = InputMethods.getInteger();
                    if (inputQuantity <= CarDealer.carService.findById(inputId).getStock()) {
                        break;
                    }
                }
            }
        }
        CartItem cartItem = new CartItem();
        //Car
        cartItem.setCar(CarDealer.carService.findById(inputId));
        //Quantity
        cartItem.setQuantity(inputQuantity);
        //Save cart to user
        CarDealer.cartService.save(cartItem);
    }
}
