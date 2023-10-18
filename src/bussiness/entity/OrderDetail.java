package bussiness.entity;

import java.util.List;

public class OrderDetail {
    private long carId;
    private long orderId;
    private String carName;
    private double unitPrice;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(long carId, long orderId, String carName, double unitPrice, int quantity) {
        this.carId = carId;
        this.orderId = orderId;
        this.carName = carName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
