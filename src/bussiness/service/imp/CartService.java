package bussiness.service.imp;

import bussiness.config.IOFile;
import bussiness.entity.*;
import bussiness.service.ICartService;
import run.CarDealer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CartService implements ICartService {
    List<CartItem> cartItems;
    User userLogin;

    public CartService(User userLogin) {
        this.cartItems = userLogin.getCart();
        this.userLogin = userLogin;
    }

    @Override
    public List<CartItem> findAll() {
        return cartItems;
    }

    @Override
    public CartItem findById(Long id) {
        return cartItems.stream().filter(o-> o.getCar().getCarId() == id).findFirst().orElse(null);
    }

    @Override
    public void changeQuantity(long id, int quantity) {
        findById(id).setQuantity(quantity);
        CarDealer.userService.save(userLogin);
    }

    @Override
    public boolean save(CartItem cartItem) {
        boolean checked = true;
        if (userLogin.getCart() == null) {
            checked = true;
        } else {
            for (CartItem c: userLogin.getCart()) {
                if (c.getCar().getCarId() == cartItem.getCar().getCarId()) {
                    c.setQuantity(c.getQuantity() + cartItem.getQuantity());
                    checked = false;
                    break;
                }
            }
        }
        if (checked) {
            userLogin.getCart().add(cartItem);
        }
        CarDealer.userService.save(userLogin);
        return true;
    }

    @Override
    public void delete(Long id) {
        cartItems.remove(findById(id));
        CarDealer.userService.save(userLogin);
    }

    @Override
    public void display() {
        for (CartItem c: userLogin.getCart()) {
            System.out.println("ID: " + c.getCar().getCarId() +" - car's name: " + c.getCar().getCarName() + " - quantity: " +c.getQuantity());
        }
    }

    @Override
    public void checkOut(String phoneNumber, String address) {
        Order order = new Order();
        order.setOrderId(CarDealer.orderService.getNewId());

        List<OrderDetail> orderDetails = cartItems.stream().map(cartItem -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCarId(cartItem.getCar().getCarId());
            orderDetail.setOrderId(order.getOrderId());
            orderDetail.setCarName(cartItem.getCar().getCarName());
            orderDetail.setUnitPrice(cartItem.getCar().getUnitPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            return orderDetail;
        }).toList();

        order.setUserId(userLogin.getUserId());
        order.setFullName(userLogin.getFullName());
        order.setPhoneNumber(phoneNumber);
        order.setAddress(address);
        double total = cartItems.stream().map(c->c.getCar().getUnitPrice()*c.getQuantity()).reduce(0D, Double::sum);
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.WAITING);
        order.setOrderAt(LocalDateTime.now());
        order.setOrderDetails(orderDetails);

        CarDealer.orderService.save(order);
    }

    @Override
    public void addCarToListCart(long id, int quantity) {
        CartItem cartItem = new CartItem();
        //Car
        cartItem.setCar(CarDealer.carService.findByIdForUser(id));
        //Quantity
        cartItem.setQuantity(quantity);
        //Save cart to user
        save(cartItem);
    }
}
