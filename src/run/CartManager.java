package run;

import bussiness.entity.Car;
import bussiness.entity.CartItem;
import bussiness.entity.Catalog;
import bussiness.util.InputMethods;

import java.time.LocalDateTime;
import java.util.List;

public class CartManager {
    public static void showCart() {
        System.out.println("\u001B[34m----------Your cart----------");
        do {
            Menu.cart();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    showCarInCart();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
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

    //Show car in cart
    public static void showCarInCart() {
        List<CartItem> list = CarDealer.cartService.findAll();
        System.out.printf("Your cart has %d %s\n", list.size(), (list.size() > 1?"items":"item"));
        CarDealer.cartService.display();
    }

    //Change quantity car in cart
    public static void changeQuantity() {

    }
}
