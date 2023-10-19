package run;

import bussiness.entity.Order;
import bussiness.util.InputMethods;

public class OrderHistoryManager {
    public static void showOrderHistoryManager() {
        do {
            Menu.orderHistory();
            System.out.print("\u001B[34mEnter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    showWaitingOrders();
                    break;
                case 2:
                    showConfirmedOrders();
                    break;
                case 3:
                    showCompletedOrders();
                    break;
                case 4:
                    showCancelOrders();
                    break;
                case 5:
                    cancelOrder();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 6) {
                break;
            }
        } while (true);
    }

    //Show waiting order
    public static void showWaitingOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderWaitingForUser(CarDealer.loginAccount.getUserId()));
    }

    //Show confirmed order
    public static void showConfirmedOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderConfirmForUser(CarDealer.loginAccount.getUserId()));
    }

    //Show completed order
    public static void showCompletedOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderCompleteForUser(CarDealer.loginAccount.getUserId()));
    }

    //Show cancel order
    public static void showCancelOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderCancelForUser(CarDealer.loginAccount.getUserId()));
    }


    //Cancel order
    public static void cancelOrder() {
        boolean isExist = true;
        showWaitingOrders();
        System.out.print("Please enter the ID of the order you want to cancel: ");
        long inputId = InputMethods.getLong();
        Order order = CarDealer.orderService.findOrderWaitingForUserById(inputId);
        if (order == null) {
            while (isExist) {
                System.out.printf("No order with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    cancelOrder();
                } else {
                    return;
                }
            }
        }

        CarDealer.orderService.delete(inputId);
        System.out.printf("Order with ID %d has been cancel.\n", inputId);
    }
}
