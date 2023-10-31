package run;

import bussiness.util.ConsoleColors;

public class Menu {
    public static void logRegister() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Welcome to THS Car Dealer          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Login");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Register");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Quit");
        System.out.println();
    }

    //For Admin
    public static void adminManager() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Admin Management          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  User Manager");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Brand Manager");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Car Manager");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Order Manager");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Log out");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 6 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Quit");
        System.out.println();
    }
    //User Manager
    public static void userManager() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          User Management          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list account");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Find account with user name");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Block/Unblock user account");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }
    //Catalog Manager
    public static void catalogManager() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Brand Management          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list brands");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Add new brand");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Find brand with brand's name");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Update brand's information");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Hidden brand with brand's id");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 6 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Hidden list brands with brand's id");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 7 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }
    //Car Manager
    public static void carManager() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Car Management          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list cars");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Add new car");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Update car's information");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Hidden car with car's id");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Hidden list cars with car's id");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 6 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Find car with car's name");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 7 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }

    //Order Manager
    public static void orderManager() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Order Management          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all unconfirmed orders");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all confirmed orders");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all orders that have been delivered");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all returned orders");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Confirm pending order");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 6 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Cancel order");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 7 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Update order's status");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 8 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }

    // For User
    public static void shop() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          THS's Showroom          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Homepage");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Cart");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Contact Us");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Profile");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Order history");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 6 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Log out");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 7 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Quit");
        System.out.println();
    }

    //Car Manager
    public static void homePage() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Home Page          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Search your favorite car");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show best selling car");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show all car in showroom");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Car Detail");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Add car to your cart");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 6 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }

    //Car Manager
    public static void cart() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Your cart          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show cart");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Update order's quantity");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Delete car into cart");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Checkout");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }

    //Profile Manager
    public static void profile() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Your profile          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  View account information");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Change password");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Update account information");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }

    //Order history
    public static void orderHistory() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          Your order history          ");
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.BOXING + " 1 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all unconfirmed orders");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 2 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all confirmed orders");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 3 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all orders that have been delivered");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 4 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Show list all returned orders");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 5 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Cancel order");
        System.out.println();
        System.out.print(ConsoleColors.BOXING + " 6 ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("  Back");
        System.out.println();
    }
}
