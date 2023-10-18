package run;

import bussiness.entity.User;
import bussiness.service.ICartService;
import bussiness.service.imp.CartService;
import bussiness.util.InputMethods;

import java.time.LocalDateTime;
import java.util.Objects;

public class LoginRegisterManager {

    public static void showLoginRegister() {
        do {
            Menu.logRegister();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("\u001B[36m----------End----------");
                    System.exit(0);
                case 4:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
        } while (true);
    }
    public static void login() {
        System.out.println("\u001B[35m----------Login----------");
        System.out.print("Enter username or email: ");
        String username = InputMethods.getString().trim();
        System.out.print("Enter password: ");
        String password = InputMethods.getString().trim();

        if (Objects.equals(username, "admin") && Objects.equals(password, "admin"))
            AdminManager.showAdminManager();

        else {
            User account = CarDealer.userService.login(username, password);
            if (account == null)
                System.out.println("\u001B[31mThe account does not exist or the password is incorrect, please re-enter.");
            else {
                if (account.isStatus()) {
                    CarDealer.loginAccount = account;
                    CarDealer.cartService = new CartService(account);

                    System.out.println("\u001B[35mLogin successful");
                    System.out.println("\u001B[35mWelcome" + account.getFullName() +"to THS's showroom");
                    ShopManager.showShop();
                } else {
                    System.out.println("Your account has been blocked, please contact the admin for more detailed information.");
                }

            }
        }
    }
    public static void register() {
        System.out.println("\u001B[35m----------Register----------");
        User user = new User();
        //ID
        user.setUserId(CarDealer.userService.getNewId());
        //User Name
        System.out.print("\u001B[37mEnter username: ");
        String userName = InputMethods.getString();
        user.setUsername(userName);
        //Email
        System.out.print("\u001B[37mEnter email: ");
        String email = InputMethods.getEmail();
        user.setEmail(email);
        //Full Name
        System.out.print("\u001B[37mEnter Full Name: ");
        String fullName = InputMethods.getString();
        user.setFullName(fullName);
        //Password
        System.out.print("\u001B[37mEnter password: ");
        String password = InputMethods.getPassword();
        user.setPassword(password);
        //Confirm Password
        System.out.print("\u001B[37mEnter confirm password: ");
        String cpassword = InputMethods.checkConfirmPassword(password);
        //Role
        user.setRole(false);
        //Status
        user.setStatus(true);
        //Created Time
        LocalDateTime createdTime = LocalDateTime.now();
        user.setCreatedAt(createdTime);
        //Cart

        CarDealer.userService.save(user);
    }
}
