package run;

import bussiness.entity.User;
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
        System.out.println("----------User list----------");
        for (User user : listUsers) {
            System.out.printf("ID: %d - Username: %s - Email: %s - Full name: %s\n" +
                    "Role: %s - Status: %s - Created At: %s - Updated At: %s\n",
                    user.getUserId(), user.getUsername(), user.getEmail(), user.getFullName(),
                    (user.isRole() ? "Admin" : "User"), (user.isStatus() ? "Active" : "Blocked"), user.getCreatedAt().format(CarDealer.formatter), (user.getUpdatedAt() != null ? user.getCreatedAt().format(CarDealer.formatter) : "Not yet edited"));
            System.out.println("----------------------------------------");
        }
    }

    // Find User By Name
    public static void findUserByName() {
        System.out.print("Enter name: ");
        String inputName = InputMethods.getString().trim();

        List<User> list = CarDealer.userService.findUserByName(inputName);
        if (list.isEmpty()) {
            System.out.printf("No search results for the word '%s'.\n", inputName);
            return;
        }
        System.out.printf("----------Search result for '%s': ----------\n", inputName);
        for (User user : list) {
            System.out.printf("ID: %d - Username: %s - Email: %s - Full name: %s\n" +
                            "Role: %s - Status: %s - Created At: %s - Updated At: %s\n",
                    user.getUserId(), user.getUsername(), user.getEmail(), user.getFullName(),
                    (user.isRole() ? "Admin" : "User"), (user.isStatus() ? "Active" : "Blocked"), user.getCreatedAt().format(CarDealer.formatter), (user.getUpdatedAt() != null ? user.getCreatedAt().format(CarDealer.formatter) : "Not yet edited"));
            System.out.println("----------------------------------------");
        }
    }

    // Change status user
    public static void changeStatusUser() {
        System.out.print("Enter id user: ");
        long inputId = InputMethods.getLong();
        User user = CarDealer.userService.findById(inputId);
        if (user == null) {
            while (true) {
                System.out.printf("No user with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
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
        System.out.printf("User with ID %d has been %s.\n", inputId, (user.isStatus()?"unblocked":"blocked"));
    }
}
