package run;

import bussiness.util.InputMethods;

public class ShopManager {
    public static void showShop() {
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
                    ContactManager.showContact();
                    break;
                case 4:
                    ProfileManager.showProfileManager();
                    break;
                case 5:
                    OrderHistoryManager.showOrderHistoryManager();
                    break;
                case 6:
                    CarDealer.loginAccount = null;
                    LoginRegisterManager.showLoginRegister();
                    break;
                case 7:
                    System.out.println("\u001B[36m----------End----------");
                    System.exit(0);
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
        } while (true);
    }
}
