package run;

import bussiness.entity.Car;
import bussiness.entity.CartItem;
import bussiness.entity.Catalog;
import bussiness.util.InputMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    public static void showCart() {
        do {
            Menu.cart();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    showCarInCart();
                    break;
                case 2:
                    changeQuantity();
                    break;
                case 3:
                    removeCartInCart();
                    break;
                case 4:
                    checkOut();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 5) {
                break;
            }
        } while (true);
    }

    //Show car in cart
    public static void showCarInCart() {
        List<CartItem> list = CarDealer.cartService.findAll();
        System.out.printf("Your cart has %d %s\n", list.size(), (list.size() > 1?"items":"item"));
        CarDealer.cartService.display();
    }

    //Change quantity car in cart
    public static void changeQuantity() {
        boolean isExist = true;
        System.out.print("Enter ID of the car you want to change quantity to the shopping list: ");
        long inputId = InputMethods.getLong();
        if (CarDealer.cartService.findById(inputId) == null) {
            while (isExist) {
                System.out.printf("No car with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    changeQuantity();
                } else {
                    return;
                }
            }
        }
        System.out.print("Enter the quantity of cars you want to change: ");
        int inputQuantity = InputMethods.getInteger();
        if (inputQuantity > CarDealer.carService.findById(inputId).getStock()) {
            System.out.print("The quantity of cars exceeds the available stock, do you want to deplete it?(Y/N)");
            char key = InputMethods.getChar();
            if (key == 'Y') {
                inputQuantity = CarDealer.carService.findById(inputId).getStock();
            } else {
                while (true) {
                    System.out.print("Please re-enter the quantity of cars you want to change: ");
                    inputQuantity = InputMethods.getInteger();
                    if (inputQuantity <= CarDealer.carService.findById(inputId).getStock()) {
                        break;
                    } else {
                        System.out.println("The quantity you want to purchase exceeds the stock quantity, please re-enter.");
                    }
                }
            }
        }
        CarDealer.cartService.changeQuantity(inputId, inputQuantity);
    }

    // Remove car in cart
    public static void removeCartInCart() {
        boolean isExist = true;
        System.out.print("Enter ID of the car you want to change quantity to the shopping list: ");
        long inputId = InputMethods.getLong();
        if (CarDealer.cartService.findById(inputId) == null) {
            while (isExist) {
                System.out.printf("No car with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    changeQuantity();
                } else {
                    return;
                }
            }
        }
        CarDealer.cartService.delete(inputId);
    }
    public static void checkOut() {
        System.out.print("Enter your phone number: ");
        String inputPhone = InputMethods.getPhoneNumber();
        System.out.print("Enter your address: ");
        String inputAddress = InputMethods.getString();

        CarDealer.cartService.checkOut(inputPhone, inputAddress);
        CarDealer.loginAccount.setCart(new ArrayList<>());
    }
}
