package bussiness.service;

import bussiness.entity.CartItem;

public interface ICartService extends IGeneric<CartItem, Long> {
    void changeQuantity(long id, int quantity);
    void display();
    void checkOut(String phoneNumber, String address);
    void addCarToListCart(long id, int quantity);
}
