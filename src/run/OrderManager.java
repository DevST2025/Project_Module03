package run;

import bussiness.entity.Car;
import bussiness.entity.Catalog;
import bussiness.entity.Order;
import bussiness.util.InputMethods;

import java.time.LocalDateTime;
import java.util.List;

public class OrderManager {
    public static void showOrderManager() {
        do {
            Menu.orderManager();
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
                    confirmOrder();
                    break;
                case 6:
                    cancelOrder();
                    break;
                case 7:
                    updateStatusOder();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("\u001B[31mPlease select the options above.");
            }
            if (choice == 8) {
                break;
            }
        } while (true);
    }

    //Show waiting order
    public static void showWaitingOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderWaiting());
    }

    //Show confirmed order
    public static void showConfirmedOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderConfirm());
    }

    //Show completed order
    public static void showCompletedOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderComplete());
    }

    //Show cancel order
    public static void showCancelOrders() {
        CarDealer.orderService.display(CarDealer.orderService.findOrderCancel());
    }


    //Confirm order
    public static void confirmOrder() {
        boolean isExist = true;
        showWaitingOrders();
        System.out.print("Please enter the ID of the order you want to confirm: ");
        long inputId = InputMethods.getLong();
        Order order = CarDealer.orderService.findOrderWaitingById(inputId);
        if (order == null) {
            while (isExist) {
                System.out.printf("No order with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    confirmOrder();
                } else {
                    return;
                }
            }
        }
        CarDealer.orderService.confirmOrder(inputId);
        System.out.printf("Order with ID %d has been confirmed.\n", inputId);
    }

    //Cancel order
    public static void cancelOrder() {
        boolean isExist = true;
        showWaitingOrders();
        System.out.print("Please enter the ID of the order you want to cancel: ");
        long inputId = InputMethods.getLong();
        Order order = CarDealer.orderService.findOrderWaitingById(inputId);
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

    //Cancel order
    public static void updateStatusOder() {
        boolean isExist = true;
        showConfirmedOrders();
        System.out.print("Please enter the ID of the order you want to update status: ");
        long inputId = InputMethods.getLong();
        Order order = CarDealer.orderService.findOrderConfirmById(inputId);
        if (order == null) {
            while (isExist) {
                System.out.printf("No order with the ID %d was found. Do you want to re-enter?(Y/N)\n", inputId);
                char key = InputMethods.getChar();
                if (key == 'Y') {
                    confirmOrder();
                } else {
                    return;
                }
            }
        }
        System.out.printf("1. The order with id %d has been successfully delivered.\n", inputId);
        System.out.printf("2. The order with id %d has been canceled..\n", inputId);
        System.out.print("Please enter your choice: ");
        byte choice = InputMethods.getByte();
        while (true) {
            if (choice == 1) {
                CarDealer.orderService.completeOrder(inputId);
                System.out.println("The order status has been successfully updated.");
                break;
            } else if (choice == 2) {
                CarDealer.orderService.cancelOrder(inputId);
                System.out.println("The order status has been successfully updated.");
                break;
            } else {
                System.out.println("Please re-enter your choice");
            }
        }
    }


}
