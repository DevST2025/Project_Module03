package bussiness.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Car implements Serializable {
    private long carId;
    private String carName;
    private long catalogId;
    private String desc;
    private double unitPrice;
    private int stock;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Car() {
    }

    public Car(long carId, String carName, long catalogId, String desc, double unitPrice, int stock, boolean status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.carId = carId;
        this.carName = carName;
        this.catalogId = catalogId;
        this.desc = desc;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
