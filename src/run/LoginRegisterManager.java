package run;


import bussiness.entity.User;
import bussiness.service.ICartService;
import bussiness.service.imp.CartService;
import bussiness.util.BCrypt;
import bussiness.util.ConsoleColors;
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
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
        } while (true);
    }
    public static void login() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m                    Login                    ");
        System.out.print(ConsoleColors.RESET);
        System.out.println();
        System.out.print("Enter username or email: ");
        String username = InputMethods.getString().trim();
        System.out.print("Enter password: ");
        String password = InputMethods.getString().trim();

        if (Objects.equals(username, "admin") && Objects.equals(password, "admin")) {
            System.out.println(ConsoleColors.PURPLE + "Welcome to Administration");
            System.out.println(ConsoleColors.RESET);
            AdminManager.showAdminManager();
        }
        else {
            User account = CarDealer.userService.login(username, password);
            if (account == null)
                System.out.println(ConsoleColors.RED_ITALIC + "The account does not exist or the password is incorrect, please re-enter.");
            else {
                if (account.isStatus()) {
                    CarDealer.loginAccount = account;
                    CarDealer.cartService = new CartService(account);
                    System.out.println(ConsoleColors.GREEN_ITALIC + "Login successful");
                    System.out.println(ConsoleColors.PURPLE + "Welcome " + ConsoleColors.ORANGE + account.getFullName() + ConsoleColors.PURPLE + " to THS's showroom");
                    System.out.println(ConsoleColors.RESET);
                    ShopManager.showShop();
                } else {
                    System.out.println(ConsoleColors.RED_ITALIC + "Your account has been blocked, please contact the admin for more detailed information.");
                }

            }
        }
    }
    public static void register() {
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m                  Register                  ");
        System.out.print(ConsoleColors.RESET);
        System.out.println();
        User user = new User();
        //ID
        user.setUserId(CarDealer.userService.getNewId());
        //User Name
        System.out.print("Enter username: ");
        String userName = InputMethods.getString();
        user.setUsername(userName);
        //Email
        System.out.print("Enter email: ");
        String email = InputMethods.getEmail();
        user.setEmail(email);
        //Full Name
        System.out.print("Enter Full Name: ");
        String fullName = InputMethods.getString();
        user.setFullName(fullName);
        //Password
        System.out.print("Enter password: ");
        String password = InputMethods.getPassword();
        //Confirm Password
        System.out.print("Enter confirm password: ");
        String cpassword = InputMethods.checkConfirmPassword(password);

        //Tiến hành mã hoá và lưu vào file
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashedPassword);

        //Role
        user.setRole(false);
        //Status
        user.setStatus(true);
        //Created Time
        LocalDateTime createdTime = LocalDateTime.now();
        user.setCreatedAt(createdTime);
        //Cart

        CarDealer.userService.save(user);
        System.out.println(ConsoleColors.GREEN_ITALIC + "Register successful");
    }

}
