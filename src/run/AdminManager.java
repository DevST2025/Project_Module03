package run;

import bussiness.util.InputMethods;

public class AdminManager {
    public static void showAdminManager() {
        do {
            Menu.adminManager();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    UserManager.showUserManager();
                    break;
                case 2:
                    CatalogManager.showCatalogManager();
                    break;
                case 3:
                    CarManager.showCarManager();
                    break;
                case 4:
                    OrderManager.showOrderManager();
                    break;
                case 5:
                    CarDealer.loginAccount = null;
                    LoginRegisterManager.showLoginRegister();
                    break;
                case 6:
                    System.out.println("\u001B[36m----------End----------");
                    System.exit(0);
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
        } while (true);
    }
}
