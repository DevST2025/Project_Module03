package bussiness.service.imp;

import bussiness.config.IOFile;
import bussiness.entity.CartItem;
import bussiness.entity.User;
import bussiness.service.ICartService;
import run.CarDealer;

import java.util.List;

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
        return null;
    }

    @Override
    public void changeQuantity(long id, int quantity) {
        for (CartItem cartItem : userLogin.getCart()) {
            if (cartItem.getCar().getCarId() == id) {
                cartItem.setQuantity(quantity);
                break;
            }
        }
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

    }

    @Override
    public void display() {
        for (CartItem c: userLogin.getCart()) {
            System.out.println("ID: " + c.getCar().getCarId() +" - car's name: " + c.getCar().getCarName() + " - quantity: " +c.getQuantity());
        }
    }
}
