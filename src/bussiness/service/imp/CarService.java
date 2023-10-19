package bussiness.service.imp;

import bussiness.config.IOFile;
import bussiness.entity.Car;
import bussiness.entity.Catalog;
import bussiness.service.ICarService;
import bussiness.service.ICatalogService;
import run.CarDealer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarService implements ICarService {
    List<Car> cars;

    public CarService() {
        this.cars = IOFile.readFromFile(IOFile.CAR_PATH);
    }

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public List<Car> findAllForUser() {
        return cars.stream().filter(o-> o.isStatus() && CarDealer.catalogService.findById(o.getCatalogId()).isStatus()).collect(Collectors.toList());
    }

    @Override
    public Car findById(Long id) {
        return cars.stream().filter(car -> car.getCarId() == id).findFirst().orElse(null);
    }

    @Override
    public Car findByIdForUser(long id) {
        return findAllForUser().stream().filter(car -> car.getCarId() == id).findFirst().orElse(null);
    }

    @Override
    public long getNewId() {
        return cars.stream().map(Car::getCarId).max(Comparator.naturalOrder()).orElse(0L) + 1;
    }

    @Override
    public List<Car> findCarByNameForAdmin(String name) {
        return cars.stream().filter(car -> car.getCarName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Car> getHotCar() {
        return findAllForUser().stream().sorted(Comparator.comparing(Car::getUnitPrice).reversed()).limit(10).collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarByNameForUser(String name) {
        return findAllForUser().stream().filter(car -> car.getCarName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public boolean save(Car car) {
        if (findById(car.getCarId()) != null) {
            // User is Exist
            cars.set(cars.indexOf(findById(car.getCarId())), car);
        } else {
            // Add new account
            cars.add(car);
        }
        //Add to file
        IOFile.writeToFile(IOFile.CAR_PATH, cars);
        return true;
    }

    @Override
    public void delete(Long id) {
        cars.remove(findById(id));
        IOFile.writeToFile(IOFile.CAR_PATH, cars);
    }
}
