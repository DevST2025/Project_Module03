package bussiness.entity;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Car car;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Car car, int quantity) {
        this.car = car;
        this.quantity = quantity;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
