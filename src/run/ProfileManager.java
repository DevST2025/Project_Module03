package run;

import bussiness.entity.Car;
import bussiness.entity.User;
import bussiness.util.BCrypt;
import bussiness.util.InputMethods;

import java.time.LocalDateTime;

public class ProfileManager {
    public static void showProfileManager() {
        do {
            Menu.profile();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    viewAccInfor();
                    break;
                case 2:
                    changePassword();
                    break;
                case 3:
                    updateAccInfor();
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

    // View account information
    public static void viewAccInfor() {
        User user = CarDealer.loginAccount;
        System.out.println("Your account:");
        System.out.printf("ID: %d - Username: %s - Email: %s - Full name: %s\n" +
                        "Creation date: %s - Last update date: %s\n",
                user.getUserId(), user.getUsername(), user.getEmail(), user.getFullName(),
                user.getCreatedAt().format(CarDealer.formatter), (user.getUpdatedAt() != null ? user.getCreatedAt().format(CarDealer.formatter) : "Not yet edited"));
        System.out.println("----------------------------------------");
    }

    // Change password
    public static void changePassword() {
        System.out.print("Enter old password: ");
        String inputPass = InputMethods.getPassword();

        if (BCrypt.checkpw(inputPass, CarDealer.loginAccount.getPassword())) {
            System.out.print("Enter new password: ");
            String newPass = InputMethods.getPassword();
            String hashedPassword = BCrypt.hashpw(newPass, BCrypt.gensalt());
            CarDealer.loginAccount.setPassword(hashedPassword);
            CarDealer.userService.save(CarDealer.loginAccount);
        } else {
            System.out.println("Password does not match, please re-enter.");
        }
    }

    //Update information
    public static void updateAccInfor() {
        System.out.print("Enter old password: ");
        String inputPass = InputMethods.getPassword();
        if (BCrypt.checkpw(inputPass, CarDealer.loginAccount.getPassword())) {
            //Full Name
            System.out.print("\u001B[37mEnter Full Name: ");
            String fullName = InputMethods.getString();
            CarDealer.loginAccount.setFullName(fullName);
            //Update Time
            LocalDateTime updateTime = LocalDateTime.now();
            CarDealer.loginAccount.setUpdatedAt(updateTime);
            CarDealer.userService.save(CarDealer.loginAccount);
        } else {
            System.out.println("Password does not match, please re-enter.");
        }

    }
}
