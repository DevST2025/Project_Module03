package run;

public class Menu {
    public static void logRegister() {
        System.out.println("\u001B[35m----------Welcome to THS Car Dealer----------");
        System.out.println("\u001B[37m1. Login");
        System.out.println("\u001B[37m2. Register");
        System.out.println("\u001B[37m3. Quit");
    }

    //For Admin
    public static void adminManager() {
        System.out.println("\u001B[35m----------Admin Management----------");
        System.out.println("\u001B[37m1. User Manager");
        System.out.println("\u001B[37m2. Brand Manager");
        System.out.println("\u001B[37m3. Car Manager");
        System.out.println("\u001B[37m4. Order Manager");
        System.out.println("\u001B[37m5. Log out");
        System.out.println("\u001B[37m6. Quit");
    }
    //User Manager
    public static void userManager() {
        System.out.println("\u001B[35m----------User Management----------");
        System.out.println("\u001B[37m1. Show list account");
        System.out.println("\u001B[37m2. Find account with user name");
        System.out.println("\u001B[37m3. Block/Unblock user account");
        System.out.println("\u001B[37m4. Back");
    }
    //Catalog Manager
    public static void catalogManager() {
        System.out.println("\u001B[35m----------Brand Management----------");
        System.out.println("\u001B[37m1. Show list brands");
        System.out.println("\u001B[37m2. Add new brand");
        System.out.println("\u001B[37m3. Find brand with brand's name");
        System.out.println("\u001B[37m4. Update brand's information");
        System.out.println("\u001B[37m5. Hidden brand with brand's id");
        System.out.println("\u001B[37m6. Back");
    }
    //Car Manager
    public static void carManager() {
        System.out.println("\u001B[35m----------Car Management----------");
        System.out.println("\u001B[37m1. Show list cars");
        System.out.println("\u001B[37m2. Add new car");
        System.out.println("\u001B[37m3. Update car's information");
        System.out.println("\u001B[37m4. Hidden car with car's id");
        System.out.println("\u001B[37m5. Find car with car's name");
        System.out.println("\u001B[37m6. Back");
    }

    //Order Manager
    public static void orderManager() {
        //WAITING,CONFIRM,CANCEL,DELIVER,COMPLETE
        System.out.println("\u001B[35m----------Order Management----------");
        System.out.println("\u001B[37m1. Show list all unconfirmed orders");
        System.out.println("\u001B[37m2. Show list all confirmed orders");
        System.out.println("\u001B[37m3. Show list all orders that have been delivered");
        System.out.println("\u001B[37m4. Show list all returned orders");
        System.out.println("\u001B[37m5. Confirm pending order");
        System.out.println("\u001B[37m6. Cancel order");
        System.out.println("\u001B[37m7. Update order's status");
        System.out.println("\u001B[37m8. Back");
    }

    // For User
    public static void shop() {
        System.out.println("\u001B[35m----------THS's Showroom----------");
        System.out.println("\u001B[37m1. Homepage");
        System.out.println("\u001B[37m2. Cart");
        System.out.println("\u001B[37m3. Contact Us");
        System.out.println("\u001B[37m4. Profile");
        System.out.println("\u001B[37m5. Order history");
        System.out.println("\u001B[37m6. Log out");
        System.out.println("\u001B[37m7. Quit");
    }

    //Car Manager
    public static void homePage() {
        System.out.println("\u001B[35m----------Home Page----------");
        System.out.println("\u001B[37m1. Search your favorite car");
        System.out.println("\u001B[37m2. Show best selling car");
        System.out.println("\u001B[37m3. Show all car in showroom");
        System.out.println("\u001B[37m4. Car Detail");
        System.out.println("\u001B[37m5. Add car to your cart");
        System.out.println("\u001B[37m6. Back");
    }

    //Car Manager
    public static void cart() {
        System.out.println("\u001B[35m----------Your cart----------");
        System.out.println("\u001B[37m1. Show cart");
        System.out.println("\u001B[37m2. Update order's quantity");
        System.out.println("\u001B[37m3. Delete car into cart");
        System.out.println("\u001B[37m4. Checkout");
        System.out.println("\u001B[37m5. Back");
    }

    //Profile Manager
    public static void profile() {
        System.out.println("\u001B[35m----------Your profile----------");
        System.out.println("\u001B[37m1. View account information");
        System.out.println("\u001B[37m2. Change password");
        System.out.println("\u001B[37m3. Update account information");
        System.out.println("\u001B[37m4. Back");
    }

    //Order history
    public static void orderHistory() {
        System.out.println("\u001B[35m----------Your order history----------");
        System.out.println("\u001B[37m1. Show list all unconfirmed orders");
        System.out.println("\u001B[37m2. Show list all confirmed orders");
        System.out.println("\u001B[37m3. Show list all orders that have been delivered");
        System.out.println("\u001B[37m4. Show list all returned orders");
        System.out.println("\u001B[37m5. Cancel order");
        System.out.println("\u001B[37m6. Back");
    }
}
