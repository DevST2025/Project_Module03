package bussiness.service;

import bussiness.entity.Car;

import java.util.List;

public interface ICarService extends IGeneric<Car, Long>{
    long getNewId();
    List<Car> findCarByNameForAdmin(String name);
    List<Car> findAllForUser();
    Car findByIdForUser(long id);
    List<Car> findCarByNameForUser(String name);
    List<Car> getHotCar();
}
