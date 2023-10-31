package bussiness.service.imp;

import bussiness.config.IOFile;
import bussiness.entity.*;
import bussiness.service.IOrderService;
import run.CarDealer;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {
    List<Order> orders;

    public OrderService() {
        this.orders = IOFile.readFromFile(IOFile.ORDER_PATH);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public List<Order> findAllForUser(long id) {
        return orders.stream().filter(order -> order.getOrderId() == id).collect(Collectors.toList());
    }

    @Override
    public Order findById(Long id) {
        return orders.stream().filter(order -> order.getOrderId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean save(Order order) {
        if (findById(order.getOrderId()) != null) {
            // Order isExist
            orders.set(orders.indexOf(findById(order.getOrderId())), order);
        } else {
            // Add new order
            orders.add(order);
        }
        //Add to file
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
        return true;
    }

    @Override
    public long getNewId() {
        return orders.stream().map(Order::getOrderId).max(Comparator.naturalOrder()).orElse(0L) + 1;
    }


    @Override
    public void delete(Long id) {
        orders.remove(findById(id));
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
    }

    @Override
    public List<Order> findOrderWaiting() {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.WAITING).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderConfirm() {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.CONFIRM).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderComplete() {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.COMPLETE).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderCancel() {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.CANCEL).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderWaitingForUser(long id) {
        return findAllForUser(id).stream().filter(order -> order.getStatus() == OrderStatus.WAITING).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderConfirmForUser(long id) {
        return findAllForUser(id).stream().filter(order -> order.getStatus() == OrderStatus.CONFIRM).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderCancelForUser(long id) {
        return findAllForUser(id).stream().filter(order -> order.getStatus() == OrderStatus.CANCEL).collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderCompleteForUser(long id) {
        return findAllForUser(id).stream().filter(order -> order.getStatus() == OrderStatus.COMPLETE).collect(Collectors.toList());
    }

    @Override
    public void confirmOrder(long id) {
        findById(id).setStatus(OrderStatus.CONFIRM);
        findById(id).setDeliverAt(LocalDateTime.now().plusDays(2));

        for (OrderDetail orderDetail : findById(id).getOrderDetails()) {
            for (Car car: CarDealer.carService.findAll()) {
                if (orderDetail.getCarId() == car.getCarId()) {
                    car.setStock(car.getStock() - orderDetail.getQuantity());
                }
            }
        }

        IOFile.writeToFile(IOFile.CAR_PATH, CarDealer.carService.findAll());
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
    }

    @Override
    public void cancelOrder(long id) {
        findById(id).setStatus(OrderStatus.CANCEL);

        for (OrderDetail orderDetail : findById(id).getOrderDetails()) {
            for (Car car: CarDealer.carService.findAll()) {
                if (orderDetail.getCarId() == car.getCarId()) {
                    car.setStock(car.getStock() + orderDetail.getQuantity());
                }
            }
        }

        IOFile.writeToFile(IOFile.CAR_PATH, CarDealer.carService.findAll());
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
    }

    @Override
    public void completeOrder(long id) {
        findById(id).setStatus(OrderStatus.COMPLETE);
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
    }

    @Override
    public Order findOrderConfirmById(long id) {
        return findOrderConfirm().stream().filter(order -> order.getOrderId() == id).findFirst().orElse(null);
    }

    @Override
    public Order findOrderWaitingById(long id) {
        return findOrderWaiting().stream().filter(order -> order.getOrderId() == id).findFirst().orElse(null);
    }

    @Override
    public Order findOrderWaitingForUserById(long id) {
        return findOrderWaitingForUser(id).stream().filter(order -> order.getOrderId() == id).findFirst().orElse(null);
    }

    //WAITING,CONFIRM,CANCEL,DELIVER,COMPLETE

    @Override
    public void display(List<Order> list) {
        if (list.isEmpty())
            System.out.println("No existing orders.");
        else {
            for (Order o: list) {
                switch (o.getStatus()) {
                    case WAITING:
                        System.out.println("List all unconfirmed orders");
                        break;
                    case CONFIRM:
                        System.out.println("List all confirmed orders");
                        break;
                    case COMPLETE:
                        System.out.println("List all completed orders");
                        break;
                    case CANCEL:
                        System.out.println("List all canceled orders");
                        break;
                    default:
                }
                System.out.printf("Order's ID: %d - User's ID: %d - Full name: %s\n" +
                                "Phone number: %s - Address: %s - Total Amount: %.2f\n" +
                                "Status: %s - Order At: %s - Deliver At: %s\n"
                        ,o.getOrderId(), o.getUserId(), o.getFullName(), o.getPhoneNumber()
                        ,o.getAddress(), o.getTotalAmount(), o.getStatus()
                        ,o.getOrderAt().format(CarDealer.formatter), (o.getDeliverAt()!=null?o.getDeliverAt().format(CarDealer.formatter): "Delivery date not confirmed yet."));
            }
        }
    }
}
