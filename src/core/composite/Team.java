package core.composite;

import core.model.Car;
import core.model.Driver;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private final List<Driver> drivers = new ArrayList<>();
    private final List<Car> cars = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getName() {
        return name;
    }

    public void showTeamDetails() {
        System.out.println("🏁 Takım: " + name);
        System.out.println("🧑‍✈️ Pilotlar:");
        for (Driver d : drivers) {
            System.out.println("  - " + d);
        }
        System.out.println("🚗 Araçlar:");
        for (Car c : cars) {
            System.out.println("  - " + c);
        }
    }
}
