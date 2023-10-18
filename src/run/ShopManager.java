package run;

import bussiness.util.InputMethods;

public class ShopManager {
    public static void showShop() {
        System.out.println("\u001B[34m----------Car Dealer----------");
        do {
            Menu.shop();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    HomePageManager.showHomePage();
                    break;
                case 2:
                    CartManager.showCart();
                    break;
                case 3:
                    CarDealer.loginAccount = null;
                    LoginRegisterManager.showLoginRegister();
                    break;
                case 4:
                    System.out.println("\u001B[36m----------End----------");
                    System.exit(0);
                case 5:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
        } while (true);
    }
}
