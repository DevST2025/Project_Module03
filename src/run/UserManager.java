package run;

import bussiness.entity.User;
import bussiness.util.ConsoleColors;
import bussiness.util.InputMethods;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserManager {
    public static void showUserManager() {
        do {
            Menu.userManager();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    showListAccount();
                    break;
                case 2:
                    findUserByName();
                    break;
                case 3:
                    changeStatusUser();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 4) {
                break;
            }
        } while (true);
    }
    //Show List Account
    public static void showListAccount() {
        List<User> listUsers = CarDealer.userService.findAll();
        System.out.println(ConsoleColors.BOXING);
        System.out.println("\033[38;2;225;153;0m          User list          ");
        System.out.println(ConsoleColors.RESET);
        for (User user : listUsers) {
            System.out.println("+----------------------------------------+");
            System.out.println("ID: " + user.getUserId() + "\nUsername: " + user.getUsername());
            System.out.println("Email: " + user.getEmail() + "\nFull name: " + user.getFullName());
            System.out.println("Role: " + (user.isRole() ? "Admin" : "User") + "\nStatus: " + (user.isStatus() ? "Active" : "Blocked"));
            System.out.println("Created At: " + user.getCreatedAt().format(CarDealer.formatter) + "\nUpdated At: " + (user.getUpdatedAt() != null ? user.getUpdatedAt().format(CarDealer.formatter) : "Not yet edited"));
            System.out.println("+----------------------------------------+");
        }
    }

    // Find User By Name
    public static void findUserByName() {
        System.out.print("Enter name: ");
        String inputName = InputMethods.getString().trim();

        List<User> list = CarDealer.userService.findUserByName(inputName);
        if (list.isEmpty()) {
            System.out.printf(ConsoleColors.RED_ITALIC + "No search results for the word '%s'.\n", inputName);
            return;
        }
        System.out.println(ConsoleColors.BOXING);
        System.out.printf("\033[38;2;225;153;0m          Search result for '%s'          \n", inputName);
        System.out.println(ConsoleColors.RESET);
        for (User user : list) {
            System.out.println("+----------------------------------------+");
            System.out.printf("| ID: %d\n", user.getUserId());
            System.out.printf("| Username: %s\n", user.getUsername());
            System.out.printf("| Email: %s\n", user.getEmail());
            System.out.printf("| Full name: %s\n", user.getFullName());
            System.out.printf("| Role: %s\n", (user.isRole() ? "Admin" : "User"));
            System.out.printf("| Status: %s\n", (user.isStatus() ? "Active" : "Blocked"));
            System.out.printf("| Created At: %s\n", user.getCreatedAt().format(CarDealer.formatter));
            System.out.printf("| Updated At: %s\n", (user.getUpdatedAt() != null ? user.getUpdatedAt().format(CarDealer.formatter) : "Not yet edited")); // Màu chữ trắng
            System.out.print("+----------------------------------------+\u001B[0m\n");
        }
    }

    // Change status user
    public static void changeStatusUser() {
        System.out.print("Enter id user: ");
        long inputId = InputMethods.getLong();
        User user = CarDealer.userService.findById(inputId);
        if (user == null) {
            while (true) {
                System.out.printf(ConsoleColors.RED_ITALIC + "No user with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    changeStatusUser();
                } else {
                    return;
                }
            }
        }
        user.setStatus(!user.isStatus());
        CarDealer.userService.save(user);
        System.out.printf(ConsoleColors.GREEN_ITALIC + "User with ID %d has been %s.\n", inputId, (user.isStatus()?"unblocked":"blocked"));
    }
}
